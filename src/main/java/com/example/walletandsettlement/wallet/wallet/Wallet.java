package com.example.walletandsettlement.wallet.wallet;

import com.example.walletandsettlement.wallet.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double balance;
    @ManyToOne
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private WALLET_TYPE walletType;
    @Column(unique = true)
    private String walletName;
}
