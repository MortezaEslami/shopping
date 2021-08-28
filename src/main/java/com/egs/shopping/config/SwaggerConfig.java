package com.egs.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Morteza Eslami",
            "https://www.energizeglobal.com/", "eslami.morteza86@gmail.com");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo(" Online Shopping APIs", "Api Documentation", "1.0", " ", DEFAULT_CONTACT, " ", "",
            new ArrayList());
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json"));

    @Bean
    public Docket api() {

        SecurityReference securityReference = SecurityReference.builder()
                .reference("basicAuth")
                .scopes(new AuthorizationScope[0])
                .build();

        ArrayList<SecurityReference> reference = new ArrayList<>(1);
        reference.add(securityReference);

        ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
        securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

        ArrayList<SecurityScheme> auth = new ArrayList<>(1);
        auth.add(new BasicAuth("basicAuth"));

        return new Docket(DocumentationType.SWAGGER_2).
                 apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .securitySchemes(auth)
                .securityContexts(securityContexts)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}