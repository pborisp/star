package ru.bank.star.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "ru.bank.star.postgresql",
        entityManagerFactoryRef = "postgreSqlEntityManager",
        transactionManagerRef = "postgreSqlTransactionManager"
)
public class PostgressDataSourceConfig {
    @Autowired
    private DataSourceProperties secondDataSourceProperties;

    @Bean(name="postgreSqlDataSource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource postgresDatasource() {
            return secondDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name="postgreSqlEntityManager")
    public LocalContainerEntityManagerFactoryBean postgreSqlEntityManager(@Qualifier("postgreSqlDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("ru.bank.star.postgresql"); // сканируем классы сущностей для PostgreSQL
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean(name="postgreSqlTransactionManager")
    PlatformTransactionManager postgreSqlTransactionManager(@Qualifier("postgreSqlEntityManager") EntityManagerFactory emf){
        return new JpaTransactionManager(emf);
    }
}
