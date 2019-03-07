package com.highpeak.parksmart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan( basePackages = "com.highpeak.idp.controller" )
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "License";
    private static final String title = "Scarlet-IDP REST API's";
    private static final String description = "RESTful API for Scarlet-IDP";

    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().title(title).description(description).license(LICENSE_TEXT)
                .version(SWAGGER_API_VERSION).build();
    }

    @Bean
    public Docket productApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .pathMapping("/").select().paths(PathSelectors.any()).build();
    }
}