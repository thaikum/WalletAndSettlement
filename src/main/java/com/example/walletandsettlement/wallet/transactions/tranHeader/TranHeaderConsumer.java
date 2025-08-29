package com.example.walletandsettlement.wallet.transactions.tranHeader;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranHeaderConsumer {
    private final TranHeaderService tranHeaderService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(TranHeader tranHeader) {
        tranHeaderService.verifyTransaction(tranHeader);
    }
}
