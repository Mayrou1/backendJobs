package apis.com.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .description("Documentation des APIs de l'application")
                        .version("1.0.0"));
    }
    // .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
    
    
    // http://localhost:8090/swagger-ui/index.html
}