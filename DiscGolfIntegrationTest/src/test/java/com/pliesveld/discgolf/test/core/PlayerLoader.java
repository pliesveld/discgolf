package com.pliesveld.discgolf.test.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.pliesveld.discgolf.common.domain.CourseHoleEntry;
import com.pliesveld.discgolf.persistence.domain.Course;
import com.pliesveld.discgolf.persistence.domain.Player;
import com.pliesveld.discgolf.persistence.repository.mongo.CourseRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayerLoader implements ApplicationListener<ApplicationReadyEvent> {
    final static private Logger LOG = LogManager.getLogger();

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Player player = new Player();
        player.setName("patrick");
        playerRepository.save(player);
    }
}
