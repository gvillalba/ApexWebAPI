package com.apexsystems.demo.germanvillalba.api.service;

import com.apexsystems.demo.germanvillalba.api.entity.Client;
import com.apexsystems.demo.germanvillalba.api.exception.ResourceNofFoundException;
import com.apexsystems.demo.germanvillalba.api.entity.Transaction;
import com.apexsystems.demo.germanvillalba.api.model.ClientPoint;
import com.apexsystems.demo.germanvillalba.api.model.MonthlyClientPoint;
import com.apexsystems.demo.germanvillalba.api.repository.ClientRepository;
import com.apexsystems.demo.germanvillalba.api.repository.TransactionRepository;
import com.apexsystems.demo.germanvillalba.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.apexsystems.demo.germanvillalba.api.util.RewardsUtil.calculatePoints;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    public Transaction getTransactionById(long transactionId) throws ResourceNofFoundException {
        Transaction transaction = this.transactionRepository.findById(transactionId).orElseThrow(() -> new ResourceNofFoundException("Transaction not found for id :: " + transactionId));
        return transaction;
    }

    public List<Transaction> getAllTransactions()
    {
        List<Transaction> transactionList = this.transactionRepository.findAll();
        return transactionList;
    }

    public List<ClientPoint> getTotalPointsByClient() {
        List<ClientPoint> clientPointList = new ArrayList<>();
        List<Client> clientList = this.clientRepository.findAll();
        for (Client client: clientList) {
            long points = this.getPointsByClient(client.getId());
            ClientPoint clientPoint = new ClientPoint(client, points);
            clientPointList.add(clientPoint);
        }
        return clientPointList;
    }

    public List<MonthlyClientPoint> getMonthlyPointsByClient() {
        List<MonthlyClientPoint> monthlyClientPointList = new ArrayList<>();
        List<Client> clientList = this.clientRepository.findAll();
        for (Client client: clientList) {
            Map<Integer, Long> monthlyPoints = this.getMonthlyPointsByClient(client.getId());
            MonthlyClientPoint monthlyClientPoint = new MonthlyClientPoint(client, monthlyPoints);
            monthlyClientPointList.add(monthlyClientPoint);
        }
        return monthlyClientPointList;
    }

    private long getPointsByClient (Long clientId) {
        List<Transaction> transactionListByClient = this.transactionRepository.findTransactionByClient_Id(clientId);
        long points = 0L;
        for (Transaction transaction: transactionListByClient) {
            long dollarAmount = transaction.getAmount();
            points += calculatePoints(dollarAmount);
        }
        return points;
    }

    private Map<Integer, Long> getMonthlyPointsByClient (Long clientId) {
        List<Transaction> transactionListByClient = this.transactionRepository.findTransactionByClient_Id(clientId);
        Map<Integer, Long> pointsByMonth = new HashMap<>();
        for (Transaction transaction: transactionListByClient) {
            long dollarAmount = transaction.getAmount();
            int month = DateUtil.getMonthFromDate(transaction.getDate());
            if (!pointsByMonth.containsKey(month)) {
                pointsByMonth.put(month, calculatePoints(dollarAmount));
            }
            else
            {
                long points = pointsByMonth.get(month);
                points += calculatePoints(dollarAmount);
                pointsByMonth.put(month, points);
            }

        }
        return pointsByMonth;
    }
}
