package io.github.EduardoAraujoPires.sistema.ingressos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Ingressos Api",
        version = "v1",
        contact = @Contact(
                 name = "Eduardo Araújo",
                 email = "eduardoaraujopires.dev@gmail.com",
                url = "https://www.linkedin.com/in/eduardoaraujopires/")
     )
)
public class OpenApiConfiguration {
}
