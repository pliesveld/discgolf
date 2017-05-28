package com.pliesveld.discgolf.persistence.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.pliesveld.discgolf.persistence.repository.sql.PlayerTestRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

import static com.pliesveld.discgolf.common.logging.Markers.SQL_INIT;

@Configuration
@PropertySource(value = {"classpath:dev-datasource.properties"})
@EnableJpaRepositories(basePackageClasses = PlayerTestRepository.class)
public class DefaultDataSource {
    private static final Logger LOG = LogManager.getLogger();
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        if (LOG.isDebugEnabled()) {
            LOG.debug(SQL_INIT, "DataSource properties");
            LOG.debug(SQL_INIT, "jdbc.driverClassName = {}", environment.getRequiredProperty("jdbc.driverClassName"));
            LOG.debug(SQL_INIT, "jdbc.url = {}", dataSource.getUrl());
            LOG.debug(SQL_INIT, "jdbc.username = {}", dataSource.getUsername());

            /*
            dataSource.getConnectionProperties().entrySet().iterator().forEachRemaining((entry) ->            {
                LOG.debug(SQL_INIT, "Initializing DataSource with property {}={}", entry.getKey(), entry.getValue());
            });
            */
        }

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setLogWriter(new PrintWriter(new OutputStreamWriter(System.out, Charset.forName("UTF-8")), true));
        hikariDataSource.setAutoCommit(false);
        hikariDataSource.setLoginTimeout(2);
        hikariDataSource.setInitializationFailTimeout(2);
        hikariDataSource.setDataSource(dataSource);
        return hikariDataSource;
    }

    // TODO: http://www.jpab.org/Hibernate.html
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.use_sql_comments", environment.getRequiredProperty("hibernate.use_sql_comments"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

        /*
        properties.put("hibernate.cache.use_second_level_cache",    environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
        properties.put("hibernate.cache.use_query_cache",           environment.getRequiredProperty("hibernate.cache.use_query_cache"));
        properties.put("hibernate.cache.region.factory_class",      environment.getRequiredProperty("hibernate.cache.region.factory_class"));
        */

        if (LOG.isDebugEnabled()) {
            LOG.debug(SQL_INIT, "Hibernate Properties");
            properties.entrySet().iterator().forEachRemaining((entry) -> {
                LOG.debug(SQL_INIT, "{} = {}", entry.getKey(), entry.getValue());
            });
        }
        return properties;
    }


    @Bean
    @Autowired
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabasePlatform(environment.getRequiredProperty("hibernate.dialect"));
//        vendorAdapter.setDatabase(Database.POSTGRESQL);

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//                entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        //        entityManagerFactoryBean.setPersistenceUnitName("mainPU");
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        entityManagerFactoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        entityManagerFactoryBean.setValidationMode(ValidationMode.NONE);

        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

}
