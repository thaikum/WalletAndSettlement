package wallet.transactions.tranHeader;

import com.example.walletandsettlement.WalletAndSettlementApplication;
import com.example.walletandsettlement.data.TestData;
import com.example.walletandsettlement.rabbitmq.AbstractRabbitMQTest;
import com.example.walletandsettlement.rabbitmq.RabbitMQPublisher;
import com.example.walletandsettlement.wallet.transactions.tranHeader.TranHeader;
import com.example.walletandsettlement.wallet.transactions.tranHeader.TranHeaderRepository;
import com.example.walletandsettlement.wallet.wallet.Wallet;
import com.example.walletandsettlement.wallet.wallet.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Testcontainers
@SpringBootTest(classes = WalletAndSettlementApplication.class)
@TestPropertySource(properties = {
        "spring.rabbitmq.listener.simple.default-requeue-rejected=false",
        "spring.rabbitmq.listener.simple.retry.enabled=true",
        "spring.rabbitmq.listener.simple.retry.max-attempts=3"
})
public class TranHeaderConsumerIntegrationTest extends AbstractRabbitMQTest {

    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    @Autowired
    private TranHeaderRepository tranHeaderRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Test
    void testMessageConsumption() {
        Wallet wallet = walletRepository.findById(1L).get();
        TranHeader tranHeader = TestData.getTranHeader();
        tranHeader.getPartTrans().get(0).setWallet(wallet);
        tranHeader.getPartTrans().get(0).setAmount(39393969D);
        tranHeader.getPartTrans().get(1).setWallet(wallet);
        tranHeader.getPartTrans().get(1).setAmount(39393969D);

        tranHeader.setId(null);

        rabbitMQPublisher.send(tranHeader);

        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            List<TranHeader> tranHeaders = tranHeaderRepository.findAll();
            TranHeader tranHeader1 = tranHeaders.get(tranHeaders.size() - 1);
            assertEquals(39393969D, tranHeader1.getPartTrans().get(0).getAmount());
        });
    }
}
