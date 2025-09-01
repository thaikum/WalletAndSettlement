package com.example.walletandsettlement.rabbitmq;

import com.example.walletandsettlement.data.TestData;
import com.example.walletandsettlement.wallet.transactions.tranHeader.TranHeader;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RabbitMQPublisherIntegrationTest extends AbstractRabbitMQTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMQPublisher messageProducer;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Test
    void testMessageSending() {
        TranHeader tranHeader = TestData.getTranHeader();

        messageProducer.send(tranHeader);
        Object message = rabbitTemplate.receiveAndConvert(queueName, 5000);
        assertNotNull(message);
        assertInstanceOf(TranHeader.class, message);
        assertEquals(tranHeader.getId(), ((TranHeader) message).getId());
    }
}
