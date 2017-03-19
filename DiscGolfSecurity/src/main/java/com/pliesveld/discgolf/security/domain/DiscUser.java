package com.pliesveld.discgolf.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pliesveld.discgolf.persistence.domain.Player;

import java.time.Instant;
import java.util.Collection;

@JsonIgnoreProperties(value = {"password", "accountNonExpired", "accountNonLocked", "enabled"})
final public class DiscUser extends User implements UserDetails {
    private static final long serialVersionUID = 5639683223516504866L;

    private String id;
    private Instant lastPasswordResetDate;

    public DiscUser(final PlayerAccount player, final Collection<GrantedAuthority> authorities) {
        super(player.getName(), player.getPassword(), authorities);
//        lastPasswordResetDate = student.getLastPasswordResetDate();
        id = player.getEmail();
    }

    public Instant getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public String getId() { return id; }

}
