package com.scapor.core.config.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;

public class OpenApiConfig {

    /**
     * Custom open api data open api.
     *
     * @return the open api
     */
    @Bean
    public OpenAPI customOpenAPIData() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("valid Api")
                        .description("Esta es la especificación de la REST API de valid")
                        .version("0.0.1"));
    }

}
