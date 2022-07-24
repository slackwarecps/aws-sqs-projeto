package br.com.fabioalvaro.mensageria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@RestController
public class ControllerTeste {
    private static final String QUEUE_PREFIX = "asgard-";
    private static final SqsClient SQS_CLIENT = SqsClient.builder().region(Region.US_EAST_1).build();
    private static String queueUrl = "asgard-teste";

    @GetMapping("/createQueue")
    public void createQueue() {
        String queueName = QUEUE_PREFIX + System.currentTimeMillis();

        CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                .queueName(queueName)
                .build();

        SQS_CLIENT.createQueue(createQueueRequest);

        GetQueueUrlResponse getQueueUrlResponse =
                SQS_CLIENT.getQueueUrl(GetQueueUrlRequest.builder().queueName(queueName).build());
        queueUrl = getQueueUrlResponse.queueUrl();
    }


    @GetMapping("listQueues")
    public String listQueues() {
        ListQueuesRequest listQueuesRequest = ListQueuesRequest.builder()
                .queueNamePrefix(QUEUE_PREFIX)
                .build();
        ListQueuesResponse listQueuesResponse = SQS_CLIENT.listQueues(listQueuesRequest);

        String queues = "";
        for (String url : listQueuesResponse.queueUrls()) {
            queues += url + "\n";
        }

        return queues;
    }

    @PostMapping("sendMessage")
    public void sendMessage(@RequestParam("text") String text) {
        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(text)
                .build();
        SQS_CLIENT.sendMessage(messageRequest);
    }

    @GetMapping("receiveMessagesWithoutDelete")
    public String receiveMessagesWithoutDelete() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .build();
        List<Message> receivedMessages =  SQS_CLIENT.receiveMessage(receiveMessageRequest).messages();

        String messages = "";
        for (Message receivedMessage : receivedMessages) {
            messages += receivedMessage.body() + "\n";
        }
        return messages;
    }

    @GetMapping("receiveMessagesWithDelete")
    public String receiveMessagesWithDelete() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .build();
        List<Message> receivedMessages =  SQS_CLIENT.receiveMessage(receiveMessageRequest).messages();

        String messages = "";
        for (Message receivedMessage : receivedMessages) {
            messages += receivedMessage.body() + "\n";
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .receiptHandle(receivedMessage.receiptHandle())
                    .build();
            SQS_CLIENT.deleteMessage(deleteMessageRequest);
        }
        return messages;
    }

    @GetMapping("receiveMessagesWithoutDeleteLimitedVisibilityTimeout")
    public void receiveMessagesWithoutDeleteLimitedVisibilityTimeout() {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder().queueUrl(queueUrl).build();
        String receipt = SQS_CLIENT.receiveMessage(receiveMessageRequest)
                .messages()
                .get(0)
                .receiptHandle();

        ChangeMessageVisibilityRequest visibilityRequest = ChangeMessageVisibilityRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receipt)
                .visibilityTimeout(5)
                .build();
        SQS_CLIENT.changeMessageVisibility(visibilityRequest);
    }





}
