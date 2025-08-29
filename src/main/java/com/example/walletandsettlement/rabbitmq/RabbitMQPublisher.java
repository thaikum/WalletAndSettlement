package com.example.walletandsettlement.rabbitmq;

import com.example.walletandsettlement.wallet.transactions.tranHeader.TranHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQPublisher {
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void send(TranHeader message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
