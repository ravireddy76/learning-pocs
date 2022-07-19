package com.sb.kong.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig class used for API documentation and provides html page.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to BCBS IL. It's Illegal to reproduce this code.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket payersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Spring Kong POC Service")
                .select()
                .paths(regex("/sbk-service.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Method to set swagger info
     *
     * @return ApiInfoBuilder
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring Kong Service")
                .description("Spring kong service to test oauth process")
                .license("HCSC - BCBS IL")
                .version("1.0").build();
    }
}
