package com.myFood.order;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.builders.ApiInfoBuilder;

@EnableSwagger2
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authScopes = {};
        return Arrays.asList(new SecurityReference(apiKey().getName(), authScopes));
    }

    private SecurityContext defaultContext() {
        return SecurityContext.builder()
                .forPaths(PathSelectors.any())
                .securityReferences(defaultAuth())
                .build();
    }

    @Bean
    public Docket api() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("MyFood")
                .description("API de integração com o sistema MyFood ")
                .version("1.0.0")
                .license("MyFood")
                .licenseUrl("http://www.myfood.com.br")
                .contact(new Contact("MyFood", "http://www.myfood.com.br", "myfood@myfood.com.br"))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.myFood.order.application.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo)
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Arrays.asList(defaultContext()));
    }
        

}
