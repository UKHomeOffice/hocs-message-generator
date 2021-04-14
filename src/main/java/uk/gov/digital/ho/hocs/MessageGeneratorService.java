package uk.gov.digital.ho.hocs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import uk.gov.digital.ho.hocs.client.SQSClient;
import uk.gov.digital.ho.hocs.payload.PayloadFile;
import uk.gov.digital.ho.hocs.payload.Replacer;

import java.util.Random;

import static org.apache.commons.lang3.StringUtils.replaceEach;
import static uk.gov.digital.ho.hocs.payload.FileReader.getResourceFileAsString;

@Service
@Slf4j
public class MessageGeneratorService  {

    private final SQSClient sqsClient;
    private final int numMessages;
    private final String complaintType;
    private final Replacer replacer = new Replacer();

    public MessageGeneratorService(SQSClient sqsClient,
                                   @Value("${run.config.num-messages}") int numMessages,
                                   @Value("${run.config.complaint-type}")  String complaintType) {
        this.sqsClient = sqsClient;
        this.numMessages = numMessages;
        this.complaintType = complaintType;
    }

    public void startSending() {

        if (!StringUtils.isEmpty(complaintType)) {
            for (int i = 0; i < numMessages; i++) {
                String fileName = PayloadFile.valueOf(complaintType).getFileName();
                log.info("Sending : {}", fileName);
                sqsClient.sendMessage(replaceEach(getResourceFileAsString(fileName), replacer.getSearchList(), replacer.getReplaceList()));
            }
        } else {
            new Random().ints(numMessages, 1, PayloadFile.values().length).forEach((typeIndex) -> {
                String fileName = PayloadFile.values()[typeIndex].getFileName();
                log.info("Sending : {}", fileName);
                sqsClient.sendMessage(replaceEach(getResourceFileAsString(fileName), replacer.getSearchList(), replacer.getReplaceList()));
            });
        }
    }
}
