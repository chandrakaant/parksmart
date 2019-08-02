package com.ckp.parksmart;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( entityManagerFactoryRef = "MYSQLEntityManagerFactory", transactionManagerRef = "MYSQLTransactionManager", basePackages = {
        "com.highpeak.parksmart.datastore.repository" } )
public class MYSQLConfiguration {

    @Bean( name = "MYSQLDataSource" )
    @ConfigurationProperties( prefix = "mysql.datasource" )
    public DataSource MYSQLDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean( name = "MYSQLEntityManagerFactory" )
    public LocalContainerEntityManagerFactoryBean MYSQLEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier( "MYSQLDataSource" ) DataSource MYSQLDataSource )
    {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.id.new_generator_mappings", "false");
        return builder.dataSource(MYSQLDataSource).properties(properties).packages("com.highpeak.parksmart.datastore.model")
                .persistenceUnit("MYSQL").build();
    }

    @Bean( name = "MYSQLTransactionManager" )
    public PlatformTransactionManager MYSQLTransactionManager(
            @Qualifier( "MYSQLEntityManagerFactory" ) EntityManagerFactory MYSQLEntityManagerFactory )
    {
        return new JpaTransactionManager(MYSQLEntityManagerFactory);
    }
}
