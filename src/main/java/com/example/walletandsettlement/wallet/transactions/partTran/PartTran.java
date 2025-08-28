package com.example.walletandsettlement.wallet.transactions.partTran;

import com.example.walletandsettlement.wallet.wallet.Wallet;
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
public class PartTran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Character tranType;
    @ManyToOne
    private Wallet wallet;
}
