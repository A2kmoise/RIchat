package com.example.rib.Iconf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {
@Bean
    public OpenAPI defineOpenApi() {
    Server server =  new Server();
    server.setUrl("http://localhost:8080");
    server.setDescription("dev server");


    Contact contact = new Contact();
    contact.setName("A2kdev");
    contact.setEmail("a2kdev@gmail.com");

    Info information = new Info()
            .title("Chatting system")
            .version("1.0.0")
            .description("Version 1 RIchat")
            .contact(contact);

    return new OpenAPI().info(information).servers(List.of(server));

}
}
