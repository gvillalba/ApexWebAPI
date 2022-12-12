package com.apexsystems.demo.germanvillalba.api.repository;

import com.apexsystems.demo.germanvillalba.api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByClient_Id(Long clientId);
}
