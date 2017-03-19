package com.pliesveld.discgolf.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.security.domain.PlayerAccount;

import java.util.stream.Stream;

@Repository
public interface PlayerAccountRepository extends JpaRepository<PlayerAccount, Integer> {
    PlayerAccount findOneByEmail(final String email);

    @Query("select s from PlayerAccount s")
    Stream<PlayerAccount> findAllAsStream();

    PlayerAccount findByName(final String name);
}
