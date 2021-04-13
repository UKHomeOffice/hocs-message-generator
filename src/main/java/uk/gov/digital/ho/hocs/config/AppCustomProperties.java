package uk.gov.digital.ho.hocs.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "case.creator")
@Getter
@Setter
public class AppCustomProperties {
    @NotBlank
    private ClientCustomProperties ukviComplaint;

    @Getter
    @Setter
    private static class ClientCustomProperties {
        @NotBlank
        private String queueName;
    }
}
