package com.a201.countingstar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
public class Workaround implements WebMvcOpenApiTransformationFilter {
    @Value("${cstar.backend.server.url}")
    private String backendUrl;
    @Value("${cstar.backend.local.url}")
    private String localUrl;

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI openApi = context.getSpecification();

        Server testServer = new Server();
        testServer.setDescription("ectserver");
        testServer.setUrl(backendUrl);

        Server localServer = new Server();
        localServer.setDescription("local");
        localServer.setUrl(localUrl);

        openApi.setServers(Arrays.asList(localServer, testServer));

        return openApi;
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return documentationType.equals(DocumentationType.OAS_30);
    }
}