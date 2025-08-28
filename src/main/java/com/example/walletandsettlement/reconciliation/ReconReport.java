package com.example.walletandsettlement.reconciliation;

import java.util.List;

public record ReconReport(List<Transaction> matched, List<Transaction> exceptions) {
}
