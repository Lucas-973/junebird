package com.junebird.junebird_app.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywaySlaveInitializer {

    @Bean(initMethod = "migrate")
    public Flyway publicFlywayInstance(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .defaultSchema("public")
                .locations("classpath:db/migration/public")
                .load();
    }

    @Bean(initMethod = "migrate")
    public Flyway adminFlywayInstance(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .defaultSchema("admin")
                .locations("classpath:db/migration/admin")
                .load();
    }

    @Bean(initMethod = "migrate")
    public Flyway templateFlywayInstance(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .defaultSchema("template")
                .locations("classpath:db/migration/template")
                .load();
    }
}