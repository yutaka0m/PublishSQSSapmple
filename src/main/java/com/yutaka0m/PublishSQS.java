package com.yutaka0m;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.net.URI;
import java.net.URISyntaxException;

public class PublishSQS {
    public static void main(String[] args) throws URISyntaxException {
        var queueUrl = "http://localhost:9324/queue/test";

        SqsClient sqsClient = SqsClient.builder()
            .credentialsProvider(DefaultCredentialsProvider.create())
            .region(Region.AP_NORTHEAST_1)
            .endpointOverride(new URI("http://localhost:9324")) // ElasticMQコンテナ向けのURL
            .build();

        SendMessageResponse res =
            sqsClient.sendMessage(
                SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody("Hello world!")
                    .build());
    }
}
