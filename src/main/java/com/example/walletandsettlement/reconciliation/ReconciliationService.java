package com.example.walletandsettlement.reconciliation;

import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTranRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReconciliationService {
  @Value("${files.external-transaction-file-location}")
  private String externalTransactionFileLocation;

  private final PartTranRepository partTranRepository;

  private Map<Long, Transaction> readExternalCsv(Date date) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    List<String> lines =
        Files.readAllLines(
            Paths.get(externalTransactionFileLocation, "extern-" + sdf.format(date) + ".csv"));

    return lines.stream()
        .map(
            line -> {
              String[] split = line.split(",");
              return Transaction.builder()
                  .tranId(Long.parseLong(split[0]))
                  .amount(Double.parseDouble(split[1]))
                  .build();
            })
        .collect(Collectors.toMap(Transaction::getTranId, Function.identity()));
  }

  public List<Transaction> reconcile(Date date) throws IOException {
    List<PartTran> partTrans = partTranRepository.findAllCustomerTransactionsByVerifiedDate(date);
    Map<Long, Transaction> externalTransactions = readExternalCsv(date);

    ReconReport reconReport = new ReconReport(new ArrayList<>(), new ArrayList<>());

    for (PartTran partTran : partTrans) {
      if (externalTransactions.containsKey(partTran.getId())) {
        reconReport.matched().add(externalTransactions.get(partTran.getId()));
        externalTransactions.remove(partTran.getId());
      } else {
        reconReport
            .exceptions()
            .add(
                Transaction.builder()
                    .tranId(partTran.getId())
                    .amount(partTran.getAmount())
                    .build());
      }
    }

    reconReport
        .exceptions()
        .addAll(externalTransactions.values()); // add all remaining transactions as exceptions.
    exportReportToCsv(reconReport);
    return reconReport.exceptions();
  }

  private void exportReportToCsv(ReconReport reconReport) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String dateStr = sdf.format(new Date());

    // Write matched transactions
    Files.write(
        Paths.get(externalTransactionFileLocation, "matched-" + dateStr + ".csv"),
        reconReport.matched().stream()
            .map(t -> t.getTranId() + "," + t.getAmount())
            .collect(Collectors.toList()));

    // Write exception transactions
    Files.write(
        Paths.get(externalTransactionFileLocation, "exception-" + dateStr + ".csv"),
        reconReport.exceptions().stream()
            .map(t -> t.getTranId() + "," + t.getAmount())
            .collect(Collectors.toList()));
  }
}
