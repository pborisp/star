package ru.bank.star.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

    @Configuration
    @EnableJpaRepositories(
            basePackages = "ru.bank.star.postgresql",
            entityManagerFactoryRef = "postgreSqlEntityManager",
            transactionManagerRef = "postgreSqlTransactionManager"
    )
    public class PostgressDataSourceConfig {
        @Autowired
        private JpaProperties jpaProperties;

        @Primary
        @Bean(name="postgreSqlDataSource")
        @ConfigurationProperties(prefix = "spring.second-datasource.hikari")
        public DataSource postgresDatasource() {
            return DataSourceBuilder.create().type(HikariDataSource.class).driverClassName("org.postgresql.Driver")
                    .username("adminStar")
                    .password("test")
                    .url("jdbc:postgresql://localhost:5432/star")
                    .build();
        }

        @Primary
        @Bean(name="postgreSqlEntityManager")
        public LocalContainerEntityManagerFactoryBean postgreSqlEntityManager(DataSource dataSource) {
            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
            Map<String, Object> hibernateProps = new HashMap<>();
    //        hibernateProps.putAll(jpaProperties.getProperties(dataSource));
    //        hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL12Dialect");
            hibernateProps.put("hibernate.show_sql", true);
            hibernateProps.put("hibernate.format_sql", true);
            hibernateProps.put("hibernate.jdbc.lob.non_contextual_creation", true);
            hibernateProps.put("hibernate.hbm2ddl.auto", "update");

            factoryBean.setDataSource(dataSource);
            factoryBean.setPackagesToScan("ru.bank.star.postgresql.model");
            factoryBean.setJpaPropertyMap(hibernateProps);

            factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

            return factoryBean;
        }

        @Primary
        @Bean(name="postgreSqlTransactionManager")
        PlatformTransactionManager postgreSqlTransactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory ){
            return new org.springframework.orm.jpa.JpaTransactionManager(entityManagerFactory.getObject());
        }

        @Primary
        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            return mapper;
        }
    }
