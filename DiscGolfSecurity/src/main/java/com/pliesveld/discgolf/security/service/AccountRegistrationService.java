package com.pliesveld.discgolf.security.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.pliesveld.discgolf.common.exception.PlayerException;
import com.pliesveld.discgolf.security.domain.AccountPasswordResetToken;
import com.pliesveld.discgolf.security.domain.AccountRegistrationToken;
import com.pliesveld.discgolf.security.domain.PlayerAccount;

import javax.validation.Valid;

@Validated
@Transactional(readOnly = true)
public interface AccountRegistrationService {

    long countAccountRegistration();

    long countStudent();

    @Transactional
    void taskPurgeExpiredAccounts();

    @Transactional
    AccountRegistrationToken deleteAccountRegistration(final int id);

    @Transactional
    PlayerAccount createStudent(final String name, final String email, final String password) throws PlayerException;

    @Transactional
    AccountRegistrationToken createAccountRegistration(@Valid final PlayerAccount playerAccount);

    @Transactional
    AccountPasswordResetToken findOrCreatePasswordResetToken(@Valid final PlayerAccount playerAccount);

    @Transactional
    PlayerAccount processRegistrationConfirmation(final String token);

    @Transactional
    PlayerAccount processPasswordResetConfirmation(final String token);

    void emailVerificationConfirmationURLtoAccountHolder(final PlayerAccount playerAccount, final String confirmURL);

    void emailTemporaryPasswordToAccountHolder(final String email, final String tempPassword);

    void emailPasswordResetToAccountHolder(final String email, final String confirmURL);
}
