package tarea_IP03.tarea_IP03.config; // <--- Cambiado a tu paquete

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de Ventas - Bodega Don Pepe") // <--- Cambiado el título
                        .version("1.0")
                        .description("Documentación de la API REST para el cálculo de ventas con IGV, descuentos y comisiones.") // <--- Cambiada la descripción
                        .contact(new Contact()
                                .name("Don Pepe") // <--- Cambiado el contacto
                                .email("donpepe@gmail.com")));
    }

}