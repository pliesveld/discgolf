package com.pliesveld.discgolf.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class GameListener {
    private static final Logger LOG = LogManager.getLogger();

    @EventListener
    public void handleNewGame(GameEvent event) {
        LOG.info("Game event: {}", event);
    }

}
