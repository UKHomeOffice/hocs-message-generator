package uk.gov.digital.ho.hocs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.gov.digital.ho.hocs.client.SQSClient;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MessageGeneratorServiceTest {
    @Mock
    private SQSClient sqsClient;

    @Test
    public void shouldSendCountMessagesWithComplaintType() {
        int numMessages = 2;
        MessageGeneratorService generatorService = new MessageGeneratorService(sqsClient, numMessages, "BIOMETRIC");
        generatorService.startSending();
        verify(sqsClient, times(numMessages)).sendMessage(anyString());
    }

    @Test
    public void shouldSendCountMessagesWithoutComplaintType() {
        int numMessages = 2;
        MessageGeneratorService generatorService = new MessageGeneratorService(sqsClient, numMessages, "");
        generatorService.startSending();
        verify(sqsClient, times(numMessages)).sendMessage(anyString());
    }
}