package com.apexsystems.demo.germanvillalba.api.controller;

import com.apexsystems.demo.germanvillalba.api.exception.ResourceNofFoundException;
import com.apexsystems.demo.germanvillalba.api.entity.Transaction;
import com.apexsystems.demo.germanvillalba.api.model.ClientPoint;
import com.apexsystems.demo.germanvillalba.api.model.MonthlyClientPoint;
import com.apexsystems.demo.germanvillalba.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        Transaction savedTransaction = this.transactionService.createTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable long transactionId) throws ResourceNofFoundException {
        Transaction transaction = this.transactionService.getTransactionById(transactionId);
        return ResponseEntity.ok().body(transaction);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactionList = this.transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionList);
    }

    @GetMapping(value = "/total-client-points")
    public ResponseEntity<List<ClientPoint>> getClientPoints(){
        List<ClientPoint> clientPointList = this.transactionService.getTotalPointsByClient();
        return ResponseEntity.ok(clientPointList);
    }

    @GetMapping(value = "/monthly-client-points")
    public ResponseEntity<List<MonthlyClientPoint>> getMonthlyClientPoints(){
        List<MonthlyClientPoint> monthlyClientPointList = this.transactionService.getMonthlyPointsByClient();
        return ResponseEntity.ok(monthlyClientPointList);
    }
}
