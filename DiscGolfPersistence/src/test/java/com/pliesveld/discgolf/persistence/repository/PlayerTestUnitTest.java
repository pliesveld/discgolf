package com.pliesveld.discgolf.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import com.pliesveld.discgolf.common.domain.test.PlayerTest;
import com.pliesveld.discgolf.persistence.AbstractHibernateTest;
import com.pliesveld.discgolf.persistence.repository.sql.PlayerTestRepository;
import org.junit.Test;

public class PlayerTestUnitTest extends AbstractHibernateTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlayerTestRepository playerTestRepository;


    @Test
    public void whenContext_shouldLoad() {}

    @Test
    public void whenRepositorySave_shouldPersist() throws Exception {
        PlayerTest playerTest = new PlayerTest();
        playerTest.setName("Some name");
        playerTestRepository.save(playerTest);
    }
}
