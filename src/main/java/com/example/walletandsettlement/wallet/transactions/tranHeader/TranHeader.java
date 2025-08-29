package com.example.walletandsettlement.wallet.transactions.tranHeader;

import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date postedDate;
    private Boolean isVerified;
    private Date verifiedDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tran_id")
    private List<PartTran> partTrans;
}
