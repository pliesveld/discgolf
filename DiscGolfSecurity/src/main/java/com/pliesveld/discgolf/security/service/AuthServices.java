package com.pliesveld.discgolf.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pliesveld.discgolf.persistence.domain.Player;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;
import com.pliesveld.discgolf.security.domain.DiscUser;
import com.pliesveld.discgolf.security.domain.PlayerAccount;
import com.pliesveld.discgolf.security.repository.PlayerAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthServices implements UserDetailsService {
    final static private Logger LOG = LogManager.getLogger();

    @Autowired
    private PlayerAccountRepository playerAccountRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        LOG.debug("loadingUser: {}", username);
        final PlayerAccount player = playerAccountRepository.findByName(username);

        if (player == null) {
            throw new UsernameNotFoundException(String.format("User %s not found.", username));
        }
        player.getName();
        player.getEmail();
        player.getPassword();

        final List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("USER");
        final DiscUser user = new DiscUser(player, authorities);
        return user;
    }

}

