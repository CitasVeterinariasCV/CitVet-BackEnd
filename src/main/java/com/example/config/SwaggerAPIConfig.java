package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerAPIConfig {

    @Value("${CitasVets-api.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        //SERVIDOR DE DESARROLLO
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Development Server");

        //INFORMACION DE CONTACTO
        Contact contact = new Contact();
        contact.setEmail("fabricio@gmail.com");
        contact.setName("CitVet");
        contact.setUrl("https://www.citvet.com");

        License mitlicense = new License().name("MIT License").url("https://opensource.org/licenses/MIT");
        //INFORMACION GENERAL DE LA API
        Info info = new Info()
                .title("API sobre citas veterinarias")
                .version("2.0")
                .contact(contact)
                .description("API Restful de citas veterinarias")
                .termsOfService("https://www.citvet.com/terms")
                .license(mitlicense);

        return new OpenAPI()
                .info(info)
                .addServersItem(devServer);
    }
}
