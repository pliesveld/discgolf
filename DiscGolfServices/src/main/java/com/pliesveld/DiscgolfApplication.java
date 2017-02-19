package com.pliesveld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootConfiguration
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan(excludeFilters = {
		@ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public class DiscgolfApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscgolfApplication.class, args);
	}
}
