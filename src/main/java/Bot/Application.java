package Bot;

import Bot.Service.MarvelTemplateBuilder;
import Bot.Service.MessageTypeDetector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MarvelTemplateBuilder marvelTemplateBuilder() {
        return new MarvelTemplateBuilder();
    }

    @Bean
    public MessageTypeDetector messageTypeDetector(){
        return new MessageTypeDetector();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
