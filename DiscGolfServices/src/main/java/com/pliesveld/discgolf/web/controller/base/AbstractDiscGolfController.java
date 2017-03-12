package com.pliesveld.discgolf.web.controller.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.pliesveld.discgolf.repository.CourseRepository;
import com.pliesveld.discgolf.repository.GameRepository;
import com.pliesveld.discgolf.repository.PlayerRepository;

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

