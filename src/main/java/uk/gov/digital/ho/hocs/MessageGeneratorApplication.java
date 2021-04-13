package uk.gov.digital.ho.hocs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.gov.digital.ho.hocs.client.SQSClient;

@SpringBootApplication
public class MessageGeneratorApplication implements CommandLineRunner {

    private final SQSClient sqsClient;

    public MessageGeneratorApplication(SQSClient sqsClient) {
        this.sqsClient = sqsClient;
    }
    
    @Override
    public void run(String... args) {
        sqsClient.sendMessage("{\n" +
                "  \"creationDate\": \"2020-10-02\",\n" +
                "  \"complaint\": {\n" +
                "    \"complaintType\": \"POOR_INFORMATION\",\n" +
                "    \"reference\": {\n" +
                "      \"referenceType\": \"UAN_REF\",\n" +
                "      \"reference\": \"in ut id veniam\"\n" +
                "    },\n" +
                "    \"reporterDetails\": {\n" +
                "      \"applicantType\": \"APPLICANT\",\n" +
                "      \"applicantName\": \"occaecat Lorem\",\n" +
                "      \"applicantNationality\": \"Czech Republic\",\n" +
                "      \"applicantDob\": \"2020-10-03\",\n" +
                "      \"applicantEmail\": \"sss@uevptde.com\",\n" +
                "      \"applicantPhone\": \"01772 700806\"\n" +
                "    },\n" +
                "    \"complaintDetails\": {\n" +
                "      \"complaintText\": \"do tempor\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n"
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageGeneratorApplication.class, args);
    }
}
