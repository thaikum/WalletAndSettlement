package com.example.walletandsettlement.reconciliation;

import com.example.walletandsettlement.exceptions.NotFoundException;
import com.example.walletandsettlement.reconciliation.ReconReport;
import com.example.walletandsettlement.reconciliation.ReconciliationService;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTran;
import com.example.walletandsettlement.wallet.transactions.partTran.PartTranRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ReconciliationServiceTest {

    @Mock
    private PartTranRepository partTranRepository;

    @InjectMocks
    private ReconciliationService service;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "externalTransactionFileLocation", tempDir.toString());
    }

    @Test
    void reconcile_happyPath_matches_and_exceptions_and_exportsFiles() throws Exception {
        // Arrange: external CSV for a specific date (e.g., 2025-08-31)
        Date requestedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-08-31");
        String dateToken = new SimpleDateFormat("yyyyMMdd").format(requestedDate);
        Path externalFile = tempDir.resolve("extern-" + dateToken + ".csv");
        Files.write(
                externalFile,
                List.of(
                        "5635,300",
                        "5637,3000",
                        "9999,50"
                )
        );

        PartTran ptMatch = mock(PartTran.class, RETURNS_DEEP_STUBS);
        when(ptMatch.getTranHeader().getId()).thenReturn(5635L);
        when(ptMatch.getAmount()).thenReturn(300.0);

        PartTran ptUnmatched = mock(PartTran.class, RETURNS_DEEP_STUBS);
        when(ptUnmatched.getTranHeader().getId()).thenReturn(8888L);
        when(ptUnmatched.getAmount()).thenReturn(123.45);

        when(partTranRepository.findAllCustomerTransactionsByVerifiedDate(requestedDate))
                .thenReturn(List.of(ptMatch, ptUnmatched));


        ReconReport report = service.reconcile(requestedDate);

        assertNotNull(report);
        assertEquals(1, report.matched().size(), "Expected exactly one matched transaction");
        assertTrue(report.matched().stream().anyMatch(t -> t.getTranId() == 5635L && t.getAmount() == 300.0));

        assertEquals(3, report.exceptions().size(), "Expected two exception transactions");
        assertTrue(report.exceptions().stream().anyMatch(t -> t.getTranId() == 8888L && t.getAmount() == 123.45));
        assertTrue(report.exceptions().stream().anyMatch(t -> t.getTranId() == 9999L && t.getAmount() == 50.0));
        assertTrue(report.exceptions().stream().anyMatch(t -> t.getTranId() == 5637 && t.getAmount() == 3000.0));

        // Also assert export files are written for today's date (service uses new Date() for export)
        String todayToken = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Path matchedFile = tempDir.resolve("matched-" + todayToken + ".csv");
        Path exceptionFile = tempDir.resolve("exception-" + todayToken + ".csv");

        assertTrue(Files.exists(matchedFile), "Matched CSV should be created");
        assertTrue(Files.exists(exceptionFile), "Exception CSV should be created");

        List<String> matchedLines = Files.readAllLines(matchedFile);
        List<String> exceptionLines = Files.readAllLines(exceptionFile);

        // Matched should contain 5635,300
        assertTrue(matchedLines.contains("5635,300.0") || matchedLines.contains("5635,300"),
                "Matched file should contain 5635,300");

        // Exceptions should contain 8888,123.45 and 9999,50
        assertTrue(exceptionLines.stream().anyMatch(s -> s.startsWith("8888,")), "Exceptions should contain 8888 entry");
        assertTrue(exceptionLines.stream().anyMatch(s -> s.startsWith("9999,")), "Exceptions should contain 9999 entry");
    }

    @Test
    void reconcile_allExternalBecomeExceptions_whenNoRepoTransactions() throws Exception {
        // Arrange
        Date requestedDate = new SimpleDateFormat("yyyy-MM-dd").parse("2025-08-31");
        String dateToken = new SimpleDateFormat("yyyyMMdd").format(requestedDate);
        Files.write(
                tempDir.resolve("extern-" + dateToken + ".csv"),
                List.of("1001,10.5", "1002,20.0")
        );

        when(partTranRepository.findAllCustomerTransactionsByVerifiedDate(requestedDate))
                .thenReturn(List.of());

        ReconReport report = service.reconcile(requestedDate);

        assertNotNull(report);
        assertEquals(0, report.matched().size());
        assertEquals(2, report.exceptions().size());
        assertTrue(report.exceptions().stream().anyMatch(t -> t.getTranId() == 1001L && t.getAmount() == 10.5));
        assertTrue(report.exceptions().stream().anyMatch(t -> t.getTranId() == 1002L && t.getAmount() == 20.0));
    }

    @Test
    void reconcile_throwsNotFound_whenExternalCsvMissing() {
        Date requestedDate = new Date(0);

        when(partTranRepository.findAllCustomerTransactionsByVerifiedDate(requestedDate))
                .thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> service.reconcile(requestedDate));
    }
}
