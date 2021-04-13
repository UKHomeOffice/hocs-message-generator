package uk.gov.digital.ho.hocs;

import com.amazonaws.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.gov.digital.ho.hocs.client.SQSClient;
import uk.gov.digital.ho.hocs.payload.PayloadFile;

import java.util.Random;
import java.util.stream.IntStream;

import static uk.gov.digital.ho.hocs.payload.FileReader.getResourceFileAsString;

@SpringBootApplication
@Slf4j
public class MessageGeneratorApplication implements CommandLineRunner {

    private final SQSClient sqsClient;
    private final int numMessages;
    private final String complaintType;

    public MessageGeneratorApplication(SQSClient sqsClient,
                                       @Value("${run.config.num-messages}") int numMessages, 
                                       @Value("${run.config.complaint-type}")  String complaintType) {
        this.sqsClient = sqsClient;
        this.numMessages = numMessages;
        this.complaintType = complaintType;
    }

    @Override
    public void run(String... args) {

        if (StringUtils.hasValue(complaintType)) {
            for (int i = 0; i < numMessages; i++) {
                String fileName = PayloadFile.valueOf(complaintType).getFileName();
                log.info("Sending : {}", fileName);
                sqsClient.sendMessage(getResourceFileAsString(fileName));
            }
        } else {
            new Random().ints(numMessages, 1, PayloadFile.values().length).forEach((typeIndex) -> {
                String fileName = PayloadFile.values()[typeIndex].getFileName();
                log.info("Sending : {}", fileName);
                sqsClient.sendMessage(getResourceFileAsString(fileName));
            });
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageGeneratorApplication.class, args);
    }
}
