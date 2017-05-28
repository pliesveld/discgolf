package com.pliesveld.discgolf.persistence.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientOptions;

import com.fasterxml.jackson.databind.Module;
import com.pliesveld.discgolf.persistence.repository.mongo.PlayerRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = PlayerRepository.class)
//@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class MongoConfig {
    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder().socketTimeout(2000).connectTimeout(2000).heartbeatSocketTimeout(2000).build();
    }

    @Bean
    public Module registerGeoJsonModule(){ return new GeoJsonModule(); }

    @Bean
    @Autowired
    public ValidatingMongoEventListener validatingMongoEventListener(javax.validation.Validator validator) { return new ValidatingMongoEventListener(validator); }

}
