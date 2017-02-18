package com.pliesveld.discgolf.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.pliesveld.discgolf.config.MongoConfig;

public class AbstractDomainUnitTest {
    @Configuration
    @Import(MongoConfig.class)
    public static class Confg {
    }
}
