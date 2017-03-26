package com.pliesveld.discgolf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fasterxml.jackson.databind.Module;
import com.pliesveld.discgolf.repository.PlayerRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = PlayerRepository.class)
//@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class MongoConfig {

    @Bean
    public Module registerGeoJsonModule(){ return new GeoJsonModule(); }

    @Bean
    @Autowired
    public ValidatingMongoEventListener validatingMongoEventListener(javax.validation.Validator validator) { return new ValidatingMongoEventListener(validator); }

}

