package com.agilepop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("📅 AgilePop API")
                        .version("1.0.0")
                        .description("API para gerenciamento e divulgação de eventos da plataforma AgilePop.")
                        .termsOfService("https://agilepop.com/termos")
                        .contact(new Contact()
                                .name("Victor - Desenvolvedor")
                                .email("contato@agilepop.com")
                                .url("https://github.com/victor"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
