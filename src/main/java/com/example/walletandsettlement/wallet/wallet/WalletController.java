package com.example.walletandsettlement.wallet.wallet;

import com.example.walletandsettlement.responses.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {
  private final WalletService walletService;

  @PostMapping("{id}/topup")
  public ResponseEntity<?> topUp(@PathVariable("id") long id, @RequestBody UpdateBalanceDto dto) {
    walletService.transact(id, dto.amount(), true);
    return ResponseEntity.ok(new MessageResponse("Transaction successful"));
  }

  @PostMapping("{id}/consume")
  public ResponseEntity<?> consume(@PathVariable("id") long id, @RequestBody UpdateBalanceDto dto) {
    walletService.transact(id, dto.amount(), false);
    return ResponseEntity.ok(new MessageResponse("Transaction successful"));
  }

  @GetMapping("{id}/balance")
  public ResponseEntity<?> getBalance(@PathVariable("id") long id) {
    return ResponseEntity.ok(walletService.getBalance(id));
  }
}
