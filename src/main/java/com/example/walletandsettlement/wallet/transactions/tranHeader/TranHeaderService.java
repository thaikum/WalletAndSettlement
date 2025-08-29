package com.example.walletandsettlement.wallet.transactions.tranHeader;

import com.example.walletandsettlement.exceptions.InvalidTransactionException;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTranRepository;
import com.example.walletandsettlement.wallet.wallet.WALLET_TYPE;
import com.example.walletandsettlement.wallet.wallet.Wallet;
import com.example.walletandsettlement.wallet.wallet.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TranHeaderService {
    private final TranHeaderRepository tranHeaderRepository;
    private final WalletRepository walletRepository;
    private final PartTranRepository partTranRepository;

    @Transactional
    public void verifyTransaction(TranHeader tranHeader) throws InvalidTransactionException{
        for(PartTran partTran: tranHeader.getPartTrans()){
            Wallet wallet = partTran.getWallet();

            if(partTran.getTranType() == 'C' && wallet.getBalance() < partTran.getAmount() && wallet.getWalletType() == WALLET_TYPE.CUSTOMER){
                throw new InvalidTransactionException("Insufficient funds");
            }
            wallet.setBalance(wallet.getBalance() + (partTran.getTranType() == 'C' ? -partTran.getAmount() : partTran.getAmount()));
        }
        walletRepository.saveAll(tranHeader.getPartTrans().stream().map(PartTran::getWallet).toList());
        tranHeader.setIsVerified(true);
        tranHeader.setVerifiedDate(new Date());
        tranHeaderRepository.save(tranHeader);
    }

    public TranHeader createTransaction(TranHeader tranHeader) throws InvalidTransactionException {
        double total = 0;
        for(PartTran partTran: tranHeader.getPartTrans()){
            total += partTran.getTranType() == 'C' ? -partTran.getAmount() : partTran.getAmount();
        }

        if(total != 0){
            throw new InvalidTransactionException("Transaction did not balance");
        }
        tranHeader.setIsVerified(false);
        tranHeader.setPostedDate(new Date());

        return tranHeaderRepository.save(tranHeader);
    }
}
