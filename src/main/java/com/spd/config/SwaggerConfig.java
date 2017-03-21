package com.spd.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("security.oauth2.client.client-id")
    private String clientId;

    @Value("security.oauth2.client.client-secret")
    private String clientSecret;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spd.controller"))
                .build()
                .securitySchemes(Lists.newArrayList(securitySchema()))
                .securityContexts(Lists.newArrayList(securityContext()));
    }

    private static final String SECURITY_SCHEMA_OAUTH2 = "oauth2schema";
    private static final String AUTHORIZATION_SCOPE_GLOBAL = "global";
    private static final String AUTHORIZATION_SCOPE_GLOBAL_DESC ="accessEverything";

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(clientId, clientSecret, "true", "demo",
                "apiKey", ApiKeyVehicle.HEADER, "api_key", ",");
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = Lists.newArrayList();
        authorizationScopeList.add(new AuthorizationScope(AUTHORIZATION_SCOPE_GLOBAL, AUTHORIZATION_SCOPE_GLOBAL_DESC));
        List<GrantType> grantTypes = Lists.newArrayList();
        ResourceOwnerPasswordCredentialsGrant passwordGrant = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8080/oauth/token");
        grantTypes.add(passwordGrant);
        OAuth oAuth = new OAuth(SECURITY_SCHEMA_OAUTH2, authorizationScopeList, grantTypes);
        return oAuth;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope(AUTHORIZATION_SCOPE_GLOBAL, AUTHORIZATION_SCOPE_GLOBAL_DESC);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference(SECURITY_SCHEMA_OAUTH2, authorizationScopes));
    }

}
