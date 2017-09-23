package com.pliesveld.discgolf.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import com.pliesveld.discgolf.common.domain.DiscPlayer;
import com.pliesveld.discgolf.persistence.AbstractHibernateTest;
import com.pliesveld.discgolf.persistence.repository.sql.DiscPlayerRepository;
import org.junit.Test;

public class DiscPlayerTestUnit extends AbstractHibernateTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DiscPlayerRepository discPlayerRepository;


    @Test
    public void whenContext_shouldLoad() {}

    @Test
    public void whenRepositorySave_shouldPersist() throws Exception {
        DiscPlayer discPlayer = new DiscPlayer();
        discPlayer.setName("Some name");
        discPlayerRepository.save(discPlayer);
    }
}
