package com.nekol.controller;

import com.nekol.payload.request.TransactionRequest;
import com.nekol.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/detail")
    public ResponseEntity<?> detail() {
        return ResponseEntity.ok().body(transactionService.detail());
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok().body(transactionService.addTransaction(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        transactionService.delete(id);
        return ResponseEntity.ok().body("Delete transaction successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TransactionRequest request) {
        return ResponseEntity.ok().body(transactionService.updateTransaction(id, request));
    }


}
