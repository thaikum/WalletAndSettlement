package com.example.walletandsettlement.reconciliation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {
    private Long tranId;
    private Double amount;
}
