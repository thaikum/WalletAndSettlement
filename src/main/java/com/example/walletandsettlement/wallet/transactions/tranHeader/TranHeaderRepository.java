package com.example.walletandsettlement.wallet.transactions.tranHeader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranHeaderRepository extends JpaRepository<TranHeader, Long> {}
