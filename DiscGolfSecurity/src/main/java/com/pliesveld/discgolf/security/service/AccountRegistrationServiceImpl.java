package com.pliesveld.discgolf.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pliesveld.discgolf.common.exception.PlayerException;
import com.pliesveld.discgolf.common.exception.PlayerNotFoundException;
import com.pliesveld.discgolf.common.exception.ResourceLimitException;
import com.pliesveld.discgolf.security.domain.AccountPasswordResetToken;
import com.pliesveld.discgolf.security.domain.AccountRegistrationToken;
import com.pliesveld.discgolf.security.domain.AccountRole;
import com.pliesveld.discgolf.security.domain.PlayerAccount;
import com.pliesveld.discgolf.security.repository.PasswordResetRepository;
import com.pliesveld.discgolf.security.repository.RegistrationRepository;
import com.pliesveld.discgolf.security.repository.PlayerAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service("accountRegistrationService")
public class AccountRegistrationServiceImpl implements AccountRegistrationService {
    private static final Logger LOG = LogManager.getLogger();
    private static final Logger taskLogger =
            LogManager.getLogger(LOG.getName() + ".[task]");


    @Autowired
    private MailProvider mailProvider;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    @Autowired
    private PlayerAccountRepository playerAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountRegistrationToken createAccountRegistration(@Valid final PlayerAccount playerAccount) {
        Integer student_id = playerAccount.getId();

        if (!playerAccountRepository.exists(playerAccount.getId())) {
            throw new PlayerNotFoundException(""+student_id);
        }

        final String token = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        final AccountRegistrationToken registration = new AccountRegistrationToken(playerAccount, token);
        registrationRepository.save(registration);
        return registration;
    }

    @Override
    public AccountPasswordResetToken findOrCreatePasswordResetToken(@Valid final PlayerAccount playerAccount) {
        final Integer student_id = playerAccount.getId();

        if (!playerAccountRepository.exists(student_id)) {
            throw new PlayerNotFoundException(""+student_id);
        }

        AccountPasswordResetToken resetToken = passwordResetRepository.findOne(playerAccount.getId());

        final String token = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        if (resetToken == null) {
            resetToken = new AccountPasswordResetToken(playerAccount, token);
            passwordResetRepository.save(resetToken);
        } else {
            resetToken.getEmailSentOn();
            resetToken.getToken();
        }

        return resetToken;
    }

    @Override
    public AccountRegistrationToken deleteAccountRegistration(final int id) {
        final AccountRegistrationToken registration = registrationRepository.findOne(id);
        if (registration != null) {
            registrationRepository.delete(registration);
        }
        return registration;
    }

    @Override
    public PlayerAccount createStudent(final String name, final String email, final String password) throws PlayerException {

        final PlayerAccount other = playerAccountRepository.findOneByEmail(email);

        if (other != null)
            throw new PlayerException(email);

        final PlayerAccount playerAccount = new PlayerAccount();
        playerAccount.setName(name);
        playerAccount.setEmail(email);
        playerAccount.setPassword(passwordEncoder.encode(password));
        playerAccount.setRole(AccountRole.ROLE_ACCOUNT);

        try {
            playerAccountRepository.save(playerAccount);
        } catch (DataAccessException cdae) {
            throw new PlayerException("Could not create account");
        }

        return playerAccount;
    }


    @Override
    public PlayerAccount processRegistrationConfirmation(String token) {
        AccountRegistrationToken registration = registrationRepository.findByToken(token);
        PlayerAccount playerAccount;
        if (registration == null || (playerAccount = registration.getPlayerAccount()) == null)
            return null;

        if (playerAccount.getRole() == AccountRole.ROLE_ACCOUNT) {
            LOG.info("User registrated: {}", playerAccount.getEmail());
            playerAccount.setRole(AccountRole.ROLE_USER);
            registrationRepository.delete(registration);
            return playerAccountRepository.save(playerAccount);
        } else {
            LOG.warn("User registration: User {} has role {} != ROLE_ACCOUNT", playerAccount.getEmail(), playerAccount.getRole());
            registrationRepository.delete(registration);
            return playerAccount;
        }
    }

    @Override
    public PlayerAccount processPasswordResetConfirmation(String token) {
        AccountPasswordResetToken resetToken = passwordResetRepository.findByToken(token);
        PlayerAccount playerAccount;

        if (resetToken == null || (playerAccount = resetToken.getPlayerAccount()) == null)
            return null;

        LOG.info("Setting temporary password for {}", playerAccount.getEmail());

        String temp_password = "";

        for (int i = 0; i < 8; i++) {
            String source = UUID.randomUUID().toString();
            int rnd_idx = ThreadLocalRandom.current().nextInt(source.length());
            char c = source.charAt(rnd_idx);
            temp_password += c;
        }

        temp_password = temp_password.toUpperCase();

        playerAccount.setPassword(temp_password);
        playerAccount.setTemporaryPassword(true);
        playerAccount.setLastPasswordResetDate(Instant.now());
        playerAccountRepository.save(playerAccount);

        passwordResetRepository.delete(resetToken);

        return playerAccount;
    }

    /* 5am every day */
    @Scheduled(cron = "0 0 5 * * ?")
    @Override
    public void taskPurgeExpiredAccounts() {
        taskLogger.info("Checking accounts to be purged.");


        List<AccountRegistrationToken> list_expired =
                registrationRepository.findAllByExpirationLessThan(Instant.now())
                        .collect(Collectors.toList());

        List<PlayerAccount> list_playerAccount =
                list_expired.stream().map(AccountRegistrationToken::getPlayerAccount)
                        .filter((student) -> student.getRole() == AccountRole.ROLE_ACCOUNT)
                        .collect(Collectors.toList());

        taskLogger.info("Purging {} registration tokens", list_expired.size());
        registrationRepository.delete(list_expired);

        StringBuilder sb = new StringBuilder();
        sb.append("Purging accounts (id, email): ");
        list_playerAccount.forEach((student) -> {
            sb.append("(")
                    .append(student.getId()).append(',')
                    .append(student.getEmail()).append(") ");
        });

        taskLogger.info(sb.toString());
        playerAccountRepository.delete(list_playerAccount);

    }

    @Override
    public long countAccountRegistration() {
        return registrationRepository.count();
    }

    @Override
    public long countStudent() {
        return playerAccountRepository.count();
    }

    @Override
    public void emailVerificationConfirmationURLtoAccountHolder(PlayerAccount playerAccount, String confirmURL) {
        AccountRegistrationToken registration = registrationRepository.findOne(playerAccount.getId());

        if (registration == null) {
            throw new PlayerNotFoundException("No registration found for student id: " + playerAccount.getId());
        }


        Instant last_email = registration.getEmailSentOn();
        Instant email_threshold = Instant.now().plus(3L, ChronoUnit.HOURS);

        if (last_email != null) {
            if (last_email.isBefore(email_threshold)) {
                LOG.info("refusing to send email before {}", email_threshold);
                throw new ResourceLimitException("Cannot resend email until " + email_threshold);
            }
        }

        if (mailProvider.emailAccountRegistrationConfirmationLink(playerAccount.getEmail(), confirmURL)) {
            registration.setEmailSentOn(Instant.now());
        }
    }

    @Override
    public void emailTemporaryPasswordToAccountHolder(String email, String tempPassword) {
        mailProvider.emailAccountTemporaryPassword(email, tempPassword);
    }

    @Override
    public void emailPasswordResetToAccountHolder(String email, String confirmURL) {
        mailProvider.emailAccountPasswordResetConfirmationLink(email, confirmURL);
    }
}
