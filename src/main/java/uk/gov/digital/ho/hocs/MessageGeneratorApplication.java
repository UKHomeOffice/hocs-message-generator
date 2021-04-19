package uk.gov.digital.ho.hocs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MessageGeneratorApplication implements CommandLineRunner {

    private final MessageGeneratorService messageGeneratorService;

    public MessageGeneratorApplication(MessageGeneratorService messageGeneratorService) {
        this.messageGeneratorService = messageGeneratorService;
    }

    @Override
    public void run(String... args) {
        messageGeneratorService.startSending();
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageGeneratorApplication.class, args);
    }
}
