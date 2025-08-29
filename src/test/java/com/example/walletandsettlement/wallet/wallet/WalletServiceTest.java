package com.example.walletandsettlement.wallet.wallet;

import com.example.walletandsettlement.data.TestData;
import com.example.walletandsettlement.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {
    @InjectMocks
    private WalletService walletService;
    @Mock
    private WalletRepository walletRepository;

    @Test
    void testTransact_HappyPath_ShouldReturnCustomerWallet() {
        Wallet cashWallet = TestData.getWallet();
        Wallet customerWallet = TestData.getWallet();
        customerWallet.setWalletType(WALLET_TYPE.CUSTOMER);
        cashWallet.setWalletType(WALLET_TYPE.OFFICE);

        when(walletRepository.findById(1L)).thenReturn(Optional.of(customerWallet));
        when(walletRepository.findByWalletName("CASH")).thenReturn(Optional.of(cashWallet));

        Wallet returned = walletService.transact(1, 100, false);
        assertEquals(WALLET_TYPE.CUSTOMER, returned.getWalletType());
    }

    @Test
    void testGetBalance_WhenAccountDoesNotExist_ShouldThrowException() {
        when(walletRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrowsExactly(NotFoundException.class, () -> walletService.getBalance(1L));
    }

    @Test
    void testGetBalance_HappyPath_ShouldReturnBalance() {
        Wallet wallet = TestData.getWallet();
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        double returnedBalance = walletService.getBalance(1L);
        assertEquals(wallet.getBalance(), returnedBalance);
    }

    @Test
    void testCreateCashWallet_whenCashAccountExist_ShouldNotCallSave() {
        when(walletRepository.findByWalletName("CASH")).thenReturn(Optional.of(TestData.getWallet()));
        walletService.createCashWallet(walletRepository);
        verify(walletRepository, never()).save(any());
    }

    @Test
    void testCreateCashWallet_whenCashAccountExist_ShouldCallSave() {
        when(walletRepository.findByWalletName("CASH")).thenReturn(Optional.empty());
        when(walletRepository.save(argThat(arg -> arg.getWalletName().equals("CASH")))).thenReturn(TestData.getWallet());
        walletService.createCashWallet(walletRepository);
        verify(walletRepository).save(any());
    }
}
