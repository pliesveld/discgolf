package com.pliesveld.discgolf.security.domain;


import com.pliesveld.discgolf.persistence.Constants;
import com.pliesveld.discgolf.persistence.domain.converter.InstantConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "ACCOUNT_RESET_TOKEN",
        indexes = {@Index(name = "IDX_RESET_TOKEN", columnList = "TOKEN")}
)
public class AccountPasswordResetToken {

    @Id
    private Integer id;

    @NotNull
    @MapsId
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "PLAYER_ID", foreignKey = @ForeignKey(name = "FK_PLAYER_ACCOUNT_RESET"), nullable = false)
    private PlayerAccount playerAccount;

    @NotNull
    @Column(name = "TOKEN", nullable = false, length = Constants.MAX_ACCOUNT_TOKEN_LENGTH, unique = true)
    private String token;

    @Convert(converter = InstantConverter.class)
    @Column(name = "EXPIRATION", nullable = false)
    private Instant expiration;

    @Convert(converter = InstantConverter.class)
    @Column(name = "LAST_EMAIL_SENT")
    private Instant emailSentOn;

    public AccountPasswordResetToken() {
        super();
    }

    public AccountPasswordResetToken(@NotNull PlayerAccount playerAccount, String reset_token) {
        this.playerAccount = playerAccount;
        this.id = playerAccount.getId();
        this.token = reset_token;
    }

    @PrePersist
    public void prePersist() {
        if (expiration == null)
            expiration = Instant.now().plus(Duration.ofDays(Constants.PASSWORD_RESET_TOKEN_DURATION_DAYS));
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public PlayerAccount getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(PlayerAccount playerAccount) {
        this.playerAccount = playerAccount;
        this.id = playerAccount.getId();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(Instant expiration) {
        this.expiration = expiration;
    }

    public Instant getEmailSentOn() {
        return emailSentOn;
    }

    public void setEmailSentOn(Instant emailSentOn) {
        this.emailSentOn = emailSentOn;
    }

    @Override
    public String toString() {
        return "AccountPasswordResetToken [id=" + id + ", token=" + token
                + ", expiration=" + expiration + ", emailSentOn=" + emailSentOn + "]";
    }

}
