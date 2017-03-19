package com.pliesveld.discgolf.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pliesveld.discgolf.security.domain.AccountRegistrationToken;

import java.time.Instant;
import java.util.stream.Stream;

@Repository
public interface RegistrationRepository extends JpaRepository<AccountRegistrationToken, Integer> {
    AccountRegistrationToken findByToken(String token);

    @Query("select a from AccountRegistrationToken a")
    Stream<AccountRegistrationToken> findAllAsStream();

    Stream<AccountRegistrationToken> findAllByExpirationLessThan(Instant now);
}
