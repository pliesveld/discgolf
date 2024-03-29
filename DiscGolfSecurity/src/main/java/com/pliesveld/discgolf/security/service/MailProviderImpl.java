package com.pliesveld.discgolf.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service("mailProvider")
@Controller
public class MailProviderImpl implements MailProvider {
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private MailSender mailSender;

    @Autowired
    @Qualifier("accountVerificationMessage")
    private SimpleMailMessage templateMessageVerificationConfirmation;

    @Autowired
    @Qualifier("accountPasswordResetConfirmationMessage")
    private SimpleMailMessage templateMessagePasswordResetConfirmation;

    @Autowired
    @Qualifier("accountTemporaryPasswordMessage")
    private SimpleMailMessage templateMessageTemporaryPassword;

    @Override
    public boolean emailAccountRegistrationConfirmationLink(final String email, final String confirmURL) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessageVerificationConfirmation);
        msg.setTo(email);
        msg.setText(msg.getText() + confirmURL);

        try {
            this.mailSender.send(msg);
        } catch (MailException ex) {
            LOG.error("Email send failure", ex);
            return false;
        }


        return true;
    }

    @Override
    public boolean emailAccountPasswordResetConfirmationLink(final String email, final String confirmURL) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessagePasswordResetConfirmation);
        msg.setTo(email);
        msg.setText(msg.getText() + confirmURL);

        try {
            this.mailSender.send(msg);
        } catch (MailException ex) {
            LOG.error("Email send failure", ex);
            return false;
        }

        return true;
    }


    @Override
    public boolean emailAccountTemporaryPassword(final String email, final String temp_password) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessageTemporaryPassword);
        msg.setTo(email);
        msg.setText(msg.getText() + temp_password);

        try {
            this.mailSender.send(msg);
        } catch (MailException ex) {
            LOG.error("Email send failure", ex);
            return false;
        }

        return true;
    }

}
