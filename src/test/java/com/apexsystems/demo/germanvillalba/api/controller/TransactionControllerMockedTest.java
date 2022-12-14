package com.apexsystems.demo.germanvillalba.api.controller;

import com.apexsystems.demo.germanvillalba.api.entity.Client;
import com.apexsystems.demo.germanvillalba.api.entity.Transaction;
import com.apexsystems.demo.germanvillalba.api.model.ClientPoint;
import com.apexsystems.demo.germanvillalba.api.model.MonthlyClientPoint;
import com.apexsystems.demo.germanvillalba.api.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.apexsystems.demo.germanvillalba.api.util.MockedDataUtil.getTestClients;
import static com.apexsystems.demo.germanvillalba.api.util.MockedDataUtil.getTestTransactions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
public class TransactionControllerMockedTest {

    private static final String API_BASE_URL = "/api/v1/transaction";

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createTransactionTest() throws Exception {
        Transaction transaction1 = this.getFirstTransaction();
        when(this.transactionService.createTransaction(transaction1)).thenReturn(transaction1);

        String json = mapper.writeValueAsString(transaction1);
        mockMvc
            .perform(
                post(API_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
    }

    @Test
    public void getAllTransactionsTest() throws Exception {
        List<Transaction> mockedTransactions = getTestTransactions();
        when(this.transactionService.getAllTransactions()).thenReturn(mockedTransactions);

        MvcResult getRequestResult = mockMvc.perform(get(API_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String getResult = getRequestResult.getResponse().getContentAsString();
        Transaction[] transactions = new ObjectMapper().readValue(getResult, Transaction[].class);
        Assertions.assertEquals(4L, transactions.length);
        for (int i = 0; i < transactions.length; i++){
            Assertions.assertEquals((i + 1) ,transactions[i].getId());
        }
    }

    @Test
    public void getTransactionByIdTest() throws Exception {
        Transaction transaction1 = this.getFirstTransaction();
        when(this.transactionService.getTransactionById(transaction1.getId())).thenReturn(transaction1);

        MvcResult getRequestResult = mockMvc.perform(get(API_BASE_URL + "/" + transaction1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String getResult = getRequestResult.getResponse().getContentAsString();
        Transaction transaction = new ObjectMapper().readValue(getResult, Transaction.class);
        Assertions.assertEquals(transaction1.getId(), transaction.getId());
    }

    @Test
    public void getClientPointsTest() throws Exception {
        Client client1 = getFirstClient();
        List<ClientPoint> clientPointList = new ArrayList<>();
        ClientPoint clientPoint = new ClientPoint();
        clientPoint.setClient(client1);
        clientPoint.setPoints(90L);
        clientPointList.add(clientPoint);
        when(this.transactionService.getTotalPointsByClient()).thenReturn(clientPointList);

        MvcResult getRequestResult = mockMvc.perform(get(API_BASE_URL + "/total-client-points")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String getResult = getRequestResult.getResponse().getContentAsString();
        ClientPoint[] clientPoints = new ObjectMapper().readValue(getResult, ClientPoint[].class);
        ClientPoint clientPoint1 = clientPoints[0];
        Assertions.assertEquals(90, clientPoint1.getPoints());
    }

    @Test
    public void getMonthlyClientPointsTest() throws Exception {
        Client client1 = getFirstClient();
        List<MonthlyClientPoint> monthlyClientPointList = new ArrayList<>();
        MonthlyClientPoint monthlyClientPoint = new MonthlyClientPoint();
        monthlyClientPoint.setClient(client1);
        Map<Integer, Long> monthlyPoints = new HashMap<>();
        monthlyPoints.put(6, 90L);
        monthlyClientPoint.setMonthlyPoints(monthlyPoints);
        monthlyClientPointList.add(monthlyClientPoint);
        when(this.transactionService.getMonthlyPointsByClient()).thenReturn(monthlyClientPointList);

        MvcResult getRequestResult = mockMvc.perform(get(API_BASE_URL + "/monthly-client-points")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String getResult = getRequestResult.getResponse().getContentAsString();
        MonthlyClientPoint[] monthlyClientPoints = new ObjectMapper().readValue(getResult, MonthlyClientPoint[].class);
        MonthlyClientPoint monthlyClientPoint1 = monthlyClientPoints[0];
        Assertions.assertEquals(90, monthlyClientPoint1.getMonthlyPoints().get(6));
    }


    private Transaction getFirstTransaction() {
        List<Transaction> mockedTransactions = getTestTransactions();
        return mockedTransactions.get(0);
    }

    private Client getFirstClient() {
        List<Client> mockedClients = getTestClients();
        return mockedClients.get(0);
    }
}
