package com.example.walletandsettlement.wallet.transactions.tranHeader;

import com.example.walletandsettlement.data.TestData;
import com.example.walletandsettlement.exceptions.InvalidTransactionException;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTranRepository;
import com.example.walletandsettlement.wallet.wallet.WALLET_TYPE;
import com.example.walletandsettlement.wallet.wallet.Wallet;
import com.example.walletandsettlement.wallet.wallet.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TranHeaderServiceTest {
    @InjectMocks
    private TranHeaderService tranHeaderService;
    @Mock
    private TranHeaderRepository tranHeaderRepository;
    @Mock
    private PartTranRepository partTranRepository;
    @Mock
    private WalletRepository walletRepository;

    TranHeader tranHeader;

    @BeforeEach
    void setUp() {
        tranHeader = TestData.getTranHeader();
    }

    @Test
    void testVerifyTransaction_WhenAccountHasInsufficientFunds_ThenThrowException() {
        Wallet customerWallet =
                tranHeader.getPartTrans().stream()
                        .map(PartTran::getWallet)
                        .filter(wallet -> wallet.getWalletType() == WALLET_TYPE.CUSTOMER)
                        .findFirst()
                        .get();

        customerWallet.setBalance(0D);
        assertThrowsExactly(
                InvalidTransactionException.class, () -> tranHeaderService.verifyTransaction(tranHeader));
    }

    @Test
    void testVerifyTransaction_HappyPathShouldCallSave() {
        Wallet customerWallet =
                tranHeader.getPartTrans().stream()
                        .map(PartTran::getWallet)
                        .filter(wallet -> wallet.getWalletType() == WALLET_TYPE.CUSTOMER)
                        .findFirst()
                        .get();
        customerWallet.setBalance(100000D);
        tranHeader.setIsVerified(false);

        when(walletRepository.saveAll(anyList())).thenAnswer(args -> args.getArgument(0));
        when(tranHeaderRepository.save(argThat(TranHeader::getIsVerified))).thenReturn(tranHeader);
        tranHeaderService.verifyTransaction(tranHeader);
    }

    @Test
    void testCreateTransaction_WhenTransactionDoNotBalance_ThenThrowException() {
        tranHeader.getPartTrans().get(0).setAmount(100000D);
        assertThrowsExactly(InvalidTransactionException.class, () -> tranHeaderService.createTransaction(tranHeader));
    }

    @Test
    void testCreateTransaction_HappyPath_ShouldCallSave() {
        when(tranHeaderRepository.save(argThat(th -> !th.getIsVerified()))).thenReturn(tranHeader);
        tranHeaderService.createTransaction(tranHeader);
        verify(tranHeaderRepository, times(1)).save(any());
    }
}
