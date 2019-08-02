package com.ckp.parksmart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableAutoConfiguration
@CrossOrigin( "*" )
@ComponentScan( excludeFilters = { @ComponentScan.Filter( type = FilterType.ASSIGNABLE_TYPE ) } )
public class ParkSmartApplication {

    private static final Logger LOGEER = LoggerFactory.getLogger(ParkSmartApplication.class);

    public static void main( final String[] args ) throws Exception
    {
        LOGEER.debug("Booting Spring Application ...... ");
        SpringApplication.run(ParkSmartApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean( name = "messageSource" )
    public ResourceBundleMessageSource getMessageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("bundles/errors");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

}
