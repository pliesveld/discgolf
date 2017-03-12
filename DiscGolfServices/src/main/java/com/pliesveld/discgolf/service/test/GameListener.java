package com.pliesveld.discgolf.service.test;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pliesveld.discgolf.service.events.GameEvent;
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
