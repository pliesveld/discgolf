package com.pliesveld.discgolf.persistence.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pliesveld.discgolf.common.domain.test.PlayerTest;

@Repository
@Transactional
public interface PlayerTestRepository extends JpaRepository<PlayerTest, Long> {

}
