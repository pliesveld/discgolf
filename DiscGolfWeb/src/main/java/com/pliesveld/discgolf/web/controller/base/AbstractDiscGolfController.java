package com.pliesveld.discgolf.web.controller.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.pliesveld.discgolf.persistence.repository.mongo.CourseRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.GameRepository;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;

public abstract class AbstractDiscGolfController {

    @Autowired
    protected PlayerRepository playerRepository;

    @Autowired
    protected GameRepository gameRepository;

    @Autowired
    protected CourseRepository courseRepository;

    @Autowired
    protected ApplicationEventPublisher publisher;
}

