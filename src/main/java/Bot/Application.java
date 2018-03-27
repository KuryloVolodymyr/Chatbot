package Bot;

import Bot.Service.MarvelTemplateBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public MarvelTemplateBuilder marvelTemplateBuilder() {
        MarvelTemplateBuilder marvelTemplateBuilder = new MarvelTemplateBuilder();
        return marvelTemplateBuilder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
