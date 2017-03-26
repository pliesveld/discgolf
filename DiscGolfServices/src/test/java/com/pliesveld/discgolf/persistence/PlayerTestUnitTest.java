package com.pliesveld.discgolf.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.pliesveld.discgolf.config.BaseSQLTest;
import com.pliesveld.discgolf.domain.test.PlayerTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestExecutionListeners
public class PlayerTestUnitTest extends BaseSQLTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ISimpleDao simpleDao;

    @Test
    public void whenContext_shouldLoad() {}

    @Test
    public void whenSave_shouldPersist() {
        PlayerTest playerTest = new PlayerTest();
        playerTest.setName("TEST PLAYER");
        simpleDao.doSave(entityManager, playerTest);
    }

}

interface ISimpleDao {
    void doSave(EntityManager entityManager, Object object);
}

@Repository
@Transactional
class SimpleDao implements ISimpleDao {

    @Override
    public void doSave(EntityManager entityManager, Object object) {
       entityManager.persist(object);
    }
}
