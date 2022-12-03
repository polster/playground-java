package io.dietschi.edu.sb.hexagonbasics.application.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi(@Value("\${application-description}") description: String,
                     @Value("\${application-version}") version: String
    ): OpenAPI = OpenAPI().info(

        Info()
            .title("Hexagonal basics")
            .version(version)
            .description(description)
    )
}