package com.pliesveld.discgolf.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import com.pliesveld.discgolf.DiscgolfApplication;
import com.pliesveld.discgolf.persistence.config.PersistenceContext;
import com.pliesveld.discgolf.persistence.repository.sql.PlayerTestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {DiscgolfApplication.class, PersistenceContext.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class AbstractHibernateTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlayerTestRepository playerTestRepository;

    @Test
    public void givenContext_shouldLoad() throws Exception {
        assertNotNull(entityManager);
        assertNotNull(playerTestRepository);
    }
}
