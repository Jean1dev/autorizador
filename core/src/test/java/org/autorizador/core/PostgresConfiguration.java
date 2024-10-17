package org.autorizador.core;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class PostgresConfiguration {

    @Container
    protected static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:alpine")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @DynamicPropertySource
    static void pgProperties(DynamicPropertyRegistry registry) {
        registry.add("postgres.host", postgreSQLContainer::getJdbcUrl);
        registry.add("postgres.user", postgreSQLContainer::getUsername);
        registry.add("postgres.password", postgreSQLContainer::getPassword);
        registry.add("postgres.database", postgreSQLContainer::getDatabaseName);
    }

    @BeforeAll
    static void beforeAll() {
        postgreSQLContainer.start();
    }
}
