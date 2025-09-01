package com.example.walletandsettlement.wallet.wallet;

import com.example.walletandsettlement.exceptions.InvalidTransactionException;
import com.example.walletandsettlement.exceptions.NotFoundException;
import com.example.walletandsettlement.rabbitmq.RabbitMQPublisher;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import com.example.walletandsettlement.wallet.transactions.tranHeader.TranHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.lang.model.type.NullType;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final RabbitMQPublisher publisher;

    public Wallet transact(long walletId, double amount, boolean isATopUp) throws NotFoundException {
        Wallet customerWallet =
                walletRepository
                        .findById(walletId)
                        .orElseThrow(() -> new NotFoundException("Wallet not found"));
        Wallet cashWallet =
                walletRepository
                        .findByWalletName("CASH")
                        .orElseThrow(() -> new NotFoundException("Cash wallet not found"));

        if(!isATopUp && customerWallet.getBalance() < amount){
            throw new InvalidTransactionException("Not enough balance to perform the withdrawal!");
        }

        PartTran partTran1 =
                PartTran.builder()
                        .amount(amount)
                        .tranType(isATopUp ? 'D' : 'C')
                        .wallet(customerWallet)
                        .build();
        PartTran partTran2 =
                PartTran.builder()
                        .amount(amount)
                        .tranType(isATopUp ? 'C' : 'D')
                        .wallet(cashWallet)
                        .build();
        TranHeader tranHeader = TranHeader.builder().partTrans(List.of(partTran1, partTran2)).build();
        publisher.send(tranHeader);
        return customerWallet;
    }

    public double getBalance(long walletId) throws NotFoundException {
        return walletRepository
                .findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found"))
                .getBalance();
    }

    @Bean
    public NullType createCashWallet(WalletRepository walletRepository) {
        if (walletRepository.findByWalletName("CASH").isEmpty()) {
            Wallet wallet =
                    Wallet.builder().balance(0.0).walletType(WALLET_TYPE.OFFICE).walletName("CASH").build();
            walletRepository.save(wallet);
            return null;
        }
        return null;
    }
}
