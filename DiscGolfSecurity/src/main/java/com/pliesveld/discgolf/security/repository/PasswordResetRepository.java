package com.pliesveld.discgolf.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pliesveld.discgolf.security.domain.AccountPasswordResetToken;

import java.time.Instant;
import java.util.stream.Stream;

@Repository
public interface PasswordResetRepository extends JpaRepository<AccountPasswordResetToken, Integer> {
    AccountPasswordResetToken findByToken(String token);

    AccountPasswordResetToken findByPlayerAccount_id(Integer id);

    Stream<AccountPasswordResetToken> findAllByExpirationLessThan(Instant now);
}
