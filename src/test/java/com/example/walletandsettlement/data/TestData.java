package com.example.walletandsettlement.data;

import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import com.example.walletandsettlement.wallet.transactions.tranHeader.TranHeader;
import com.example.walletandsettlement.wallet.wallet.WALLET_TYPE;
import com.example.walletandsettlement.wallet.wallet.Wallet;

import java.util.List;

public class TestData {

    public static Wallet getWallet() {
        return Wallet.builder()
                .walletName("customer")
                .walletType(WALLET_TYPE.CUSTOMER)
                .balance(100.0)
                .build();
    }

    public static PartTran getPartTran() {
        return PartTran.builder()
                .tranType('C')
                .amount(100.0)
                .wallet(getWallet())
                .build();
    }

    public static TranHeader getTranHeader(){
        TranHeader tranHeader = TranHeader.builder().build();
        PartTran partTran1 = getPartTran();
        PartTran partTran2 = getPartTran();
        Wallet wallet = getWallet();
        wallet.setWalletType(WALLET_TYPE.OFFICE);
        wallet.setWalletName("CASH");
        partTran2.setWallet(wallet);
        partTran2.setTranType('D');
        tranHeader.setPartTrans(List.of(partTran1, partTran2));
        return tranHeader;
    }
}
