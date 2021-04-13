package uk.gov.digital.ho.hocs.client;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SQSClient {
    private final AmazonSQS sqs;
    private final String queueUrl;

    @Autowired
    public SQSClient(AmazonSQS sqs, 
                     @Value("${case.creator.ukvi-complaint.queue-name}")  String queueName) {
        this.sqs = sqs;
        this.queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
    }
    
    public void sendMessage(String message) {
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message);

        SendMessageResult sendMessageResult = sqs.sendMessage(send_msg_request);

        log.info("Successfully sent MessageId: {}", sendMessageResult.getMessageId());
    }
}
