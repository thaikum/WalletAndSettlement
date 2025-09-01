package com.example.walletandsettlement.reconciliation;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/reconciliation")
@RequiredArgsConstructor
public class ReconciliationController {
  private final ReconciliationService reconciliationService;

  @GetMapping("report")
  public ResponseEntity<?> getReconReport(
      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
    try {
      return ResponseEntity.ok(reconciliationService.reconcile(date));
    } catch (IOException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
