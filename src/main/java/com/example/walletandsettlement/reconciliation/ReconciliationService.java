package com.example.walletandsettlement.reconciliation;

import com.example.walletandsettlement.exceptions.NotFoundException;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTranRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class ReconciliationService {
    @Value("${files.external-transaction-file-location}")
    private String externalTransactionFileLocation;

    private final PartTranRepository partTranRepository;

    private Map<Long, Transaction> readExternalCsv(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            List<String> lines =
                    Files.readAllLines(
                            Paths.get(externalTransactionFileLocation + "/extern-" + sdf.format(date) + ".csv"));

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
        } catch (IOException ex) {
            throw new NotFoundException("External file for date " + date + " was not found. Please check if it was correctly dumped.");
        }

    }

    public ReconReport reconcile(Date date) throws IOException {
        List<PartTran> partTrans = partTranRepository.findAllCustomerTransactionsByVerifiedDate(date);
        Map<Long, Transaction> externalTransactions = readExternalCsv(date);

        log.info("Part trans are: {}", externalTransactions);

        ReconReport reconReport = new ReconReport(new ArrayList<>(), new ArrayList<>());

        for (PartTran partTran : partTrans) {
            long tran_id = partTran.getTranHeader().getId();
            Transaction external = externalTransactions.get(tran_id);
            if (external != null && external.getAmount().equals(partTran.getAmount())) {
                reconReport.matched().add(externalTransactions.get(tran_id));
                externalTransactions.remove(tran_id);
            } else {
                reconReport
                        .exceptions()
                        .add(
                                Transaction.builder()
                                        .tranId(tran_id)
                                        .amount(partTran.getAmount())
                                        .build());
            }
        }

        reconReport
                .exceptions()
                .addAll(externalTransactions.values()); // add all remaining transactions as exceptions.
        exportReportToCsv(reconReport);
        return reconReport;
    }

    private void exportReportToCsv(ReconReport reconReport) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());

        // Write matched transactions
        Files.write(
                Paths.get(externalTransactionFileLocation + "/matched-" + dateStr + ".csv"),
                reconReport.matched().stream()
                        .map(t -> t.getTranId() + "," + t.getAmount())
                        .collect(Collectors.toList()));

        // Write exception transactions
        Files.write(
                Paths.get(externalTransactionFileLocation + "/exception-" + dateStr + ".csv"),
                reconReport.exceptions().stream()
                        .map(t -> t.getTranId() + "," + t.getAmount())
                        .collect(Collectors.toList()));
    }
}
