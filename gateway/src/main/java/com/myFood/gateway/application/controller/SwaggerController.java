package com.myFood.gateway.application.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 *
 * @author reinaldo.andre
 */
@Component
@Primary
@EnableAutoConfiguration
public class SwaggerController implements SwaggerResourcesProvider {

    @Override
    public List get() {
        List resources = new ArrayList<>();

        resources.add(swaggerResource("MyFood", "/api/myFood/v2/api-docs", "2.0"));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
