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

    @GetMapping("/outcome")
    public ResponseEntity<?> retrieveOutcome() {
        return ResponseEntity.ok().body(transactionService.retrieveOutcome());
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok().body(transactionService.addTransaction(request));
    }


}
