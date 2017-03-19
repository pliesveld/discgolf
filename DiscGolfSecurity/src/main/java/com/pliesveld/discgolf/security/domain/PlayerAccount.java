package com.pliesveld.discgolf.security.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pliesveld.discgolf.persistence.Constants;
import com.pliesveld.discgolf.persistence.domain.converter.AccountRoleConverter;
import com.pliesveld.discgolf.persistence.domain.converter.InstantConverter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import static com.pliesveld.discgolf.persistence.Constants.*;

@Entity
@Table(name = "PLAYER_ACCOUNT",
        uniqueConstraints = {
                @UniqueConstraint(name = "UNIQUE_ACCOUNT_EMAIL", columnNames = "PLAYER_EMAIL"),
                @UniqueConstraint(name = "UNIQUE_ACCOUNT_NAME", columnNames = "PLAYER_NAME")
        })
public class PlayerAccount implements Serializable {

    private static final long serialVersionUID = -4487277214521177813L;
    private Integer id;
    private String name;
    private String email;
    private String password;
    private AccountRole role;
    private boolean temporaryPassword;
    private Instant lastPasswordResetDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PLAYER_ID")
    public Integer getId() {
        return id;
    }

    @NotNull
    @Size(min = Constants.MIN_ACCOUNT_NAME_LENGTH, max = Constants.MAX_ACCOUNT_NAME_LENGTH)
    @Column(name = "PLAYER_NAME", length = Constants.MAX_ACCOUNT_NAME_LENGTH, nullable = false)
    public String getName() {
        return name;
    }

    @NotNull
    @Size(min = MIN_ACCOUNT_EMAIL_LENGTH, max = MAX_ACCOUNT_EMAIL_LENGTH)
    @Email
    @Column(name = "PLAYER_EMAIL", length = MAX_ACCOUNT_EMAIL_LENGTH, nullable = false)
    public String getEmail() {
        return email;
    }

    @NotNull
    @Size(min = MIN_ACCOUNT_PASSWORD_LENGTH, max = MAX_ACCOUNT_PASSWORD_LENGTH)
    @Column(name = "PLAYER_PASSWORD", length = MAX_ACCOUNT_PASSWORD_LENGTH, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    @NotNull
    @Column(name = "PLAYER_ROLE", nullable = false)
    @Convert(converter = AccountRoleConverter.class)
    @Basic(fetch = FetchType.EAGER)
    public AccountRole getRole() {
        return role;
    }

    @NotNull
    @Column(name = "MUST_CHANGE_PASSWORD", nullable = false)
    public boolean isTemporaryPassword() {
        return temporaryPassword;
    }

    @NotNull
    @Column(name = "LAST_PASSWORD_RESET", nullable = false)
    @Convert(converter = InstantConverter.class)
    public Instant getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public PlayerAccount() {
        super();
    }

    @PrePersist
    public void prePersist() {
        if (role == null)
            role = AccountRole.ROLE_ACCOUNT;
        temporaryPassword = false;
        lastPasswordResetDate = Instant.now();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }

    public void setTemporaryPassword(boolean temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }

    public void setLastPasswordResetDate(Instant lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PlayerAccount)) {
            return false;
        }
        final PlayerAccount other = (PlayerAccount) obj;
        return Objects.equals(getEmail(), other.getEmail());
    }
}
