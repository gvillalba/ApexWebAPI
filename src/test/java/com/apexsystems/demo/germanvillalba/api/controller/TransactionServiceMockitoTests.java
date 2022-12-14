package com.apexsystems.demo.germanvillalba.api.controller;

import com.apexsystems.demo.germanvillalba.api.entity.Client;
import com.apexsystems.demo.germanvillalba.api.entity.Transaction;
import com.apexsystems.demo.germanvillalba.api.exception.ResourceNofFoundException;
import com.apexsystems.demo.germanvillalba.api.model.ClientPoint;
import com.apexsystems.demo.germanvillalba.api.model.MonthlyClientPoint;
import com.apexsystems.demo.germanvillalba.api.repository.ClientRepository;
import com.apexsystems.demo.germanvillalba.api.repository.TransactionRepository;
import com.apexsystems.demo.germanvillalba.api.service.TransactionService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;

import static com.apexsystems.demo.germanvillalba.api.util.MockedDataUtil.getTestClients;
import static com.apexsystems.demo.germanvillalba.api.util.MockedDataUtil.getTestTransactions;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceMockitoTests {

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private ClientRepository clientRepository;


    @Test
    public void getAllTransactionsTest() {
        List<Transaction> mockedTransactions = getTestTransactions();
        when(this.transactionRepository.findAll()).thenReturn(mockedTransactions);

        List<Transaction> transactionList = this.transactionService.getAllTransactions();
        Assertions.assertEquals(transactionList.size(), mockedTransactions.size());
    }

    @Test
    public void getTransactionByIdTest() {
        List<Transaction> mockedTransactions = getTestTransactions();
        long transactionId = 1L;

        try {
            when(this.transactionRepository.findById(transactionId)).thenReturn(Optional.ofNullable(mockedTransactions.get(0)));
            Transaction transaction1 = this.transactionService.getTransactionById(transactionId);
            Assertions.assertEquals(transaction1.getId(), transactionId);
        } catch (ResourceNofFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void createTransactionTest() {
        Client client1 = new Client(1L, "Alex", "Yuki", "123-34-3456");
        Transaction transaction1 = new Transaction(1L, new Date(), 120L, client1);
        when(this.transactionRepository.save(transaction1)).thenReturn(transaction1);

        Transaction mockedTransaction = this.transactionService.createTransaction(transaction1);
        Assertions.assertEquals(transaction1, mockedTransaction);
    }

    @Test
    public void getTotalPointsByClientTest() {
        List<Transaction> mockedTransactions = getTestTransactions();
        List<Client> mockedClientList = getTestClients();
        Client client1 = mockedClientList.get(0);
        when(this.clientRepository.findAll()).thenReturn(mockedClientList);
        when(this.transactionRepository.findTransactionByClient_Id(client1.getId())).thenReturn(mockedTransactions);

        List<ClientPoint> clientPointList = this.transactionService.getTotalPointsByClient();
        ClientPoint clientPoint1 = clientPointList.get(0);
        Assertions.assertEquals(130L, clientPoint1.getPoints());
        for (int i = 1; i < clientPointList.size(); i++){
            ClientPoint clientPoint_i = clientPointList.get(i);
            Assertions.assertEquals(0L, clientPoint_i.getPoints());
        }
    }

    @Test
    public void getMonthlyPointsByClientTest() {
        List<Transaction> mockedTransactions = getTestTransactions();
        List<Client> mockedClientList = getTestClients();
        Client client1 = mockedClientList.get(0);
        when(this.clientRepository.findAll()).thenReturn(mockedClientList);
        when(this.transactionRepository.findTransactionByClient_Id(client1.getId())).thenReturn(mockedTransactions);

        List<MonthlyClientPoint> monthlyPointsByClient = this.transactionService.getMonthlyPointsByClient();
        MonthlyClientPoint monthlyClientPoint1 = monthlyPointsByClient.get(0);
        Map<Integer, Long> monthlyPoints1 = monthlyClientPoint1.getMonthlyPoints();
        Long julyPoints = monthlyPoints1.get(6);
        Long augustPoints = monthlyPoints1.get(7);
        Long septemberPoints = monthlyPoints1.get(8);
        Assertions.assertEquals(90L, julyPoints);
        Assertions.assertEquals(40L, augustPoints);
        Assertions.assertEquals(0L, septemberPoints);
    }
}
