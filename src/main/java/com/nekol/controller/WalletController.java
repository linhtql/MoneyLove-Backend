package com.nekol.controller;

import com.nekol.payload.request.WalletRequest;
import com.nekol.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping()
    public ResponseEntity<?> createWallet(@RequestBody WalletRequest request) {
        return ResponseEntity.ok().body(walletService.createWallet(request));

    }

    @GetMapping("")
    public ResponseEntity<?> retrieveAllByUser() {
        return ResponseEntity.ok().body(walletService.retrieveAllByUserId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveById(@PathVariable Long id) {
        return ResponseEntity.ok().body(walletService.retrieveById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWallet(@RequestBody WalletRequest request, @PathVariable Long id) {
        return ResponseEntity.ok().body(walletService.updateWallet(request, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.ok().body("Delete category successfully!");
    }
}
