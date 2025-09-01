package com.example.walletandsettlement.wallet.transactions.partTran;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PartTranRepository extends JpaRepository<PartTran, Long> {
  @Query(
      nativeQuery = true,
      value =
              """
                      SELECT pt.*
                      FROM part_tran pt
                               join tran_header th on th.id = pt.tran_id
                               join wallet w on w.id = pt.wallet_id
                      WHERE DATE(th.verified_date) = DATE(:date)
                        and w.wallet_type = 'CUSTOMER'
                      """)
  List<PartTran> findAllCustomerTransactionsByVerifiedDate(Date date);
}
