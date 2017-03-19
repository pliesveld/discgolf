package com.pliesveld.discgolf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.pliesveld.discgolf.audit.AuditingDateTimeProvider;
import com.pliesveld.discgolf.audit.DateTimeService;
import com.pliesveld.discgolf.security.service.UsernameAuditorAware;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider", auditorAwareRef = "auditorAware")
@ComponentScan(basePackageClasses = {AuditingDateTimeProvider.class})
public class SpringAuditConfig {

    @Bean
    protected DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
        return new AuditingDateTimeProvider(dateTimeService);
    }

    @Bean(name = "auditorAware")
    protected AuditorAware<String> auditorAware() {
        return new UsernameAuditorAware();
    }
}
