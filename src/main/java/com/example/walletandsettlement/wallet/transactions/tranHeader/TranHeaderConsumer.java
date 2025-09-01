package com.example.walletandsettlement.wallet.transactions.tranHeader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranHeaderConsumer {
    private final TranHeaderService tranHeaderService;

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(TranHeader tranHeader) {
        log.info("Here but failure ist");
        tranHeaderService.verifyTransaction(tranHeader);
    }
}
