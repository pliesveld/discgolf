package com.pliesveld.discgolf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.databind.Module;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.pliesveld.discgolf.repository.PlayerRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = PlayerRepository.class)
//@EnableMongoAuditing(auditorAwareRef = "springSecurityAuditorAware")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        return new MongoClient(mongoClientURI);
    }

    @Override
    protected String getDatabaseName() { return database; }

    @Bean
    public Module registerGeoJsonModule(){ return new GeoJsonModule(); }

//    @Bean
//    public ValidatingMongoEventListener validatingMongoEventListener() { return new ValidatingMongoEventListener(validator()); }
////    @Bean
//    public LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }

}

