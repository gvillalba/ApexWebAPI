package com.apexsystems.demo.germanvillalba.api.util;

import com.apexsystems.demo.germanvillalba.api.entity.Client;
import com.apexsystems.demo.germanvillalba.api.entity.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MockedDataUtil {
    public static List<Transaction> getTestTransactions()  {
        try {
            Client client1 = new Client(1L, "Alex", "Yuki", "123-34-3456");
            Transaction transaction1 = new Transaction(1L, getDate("2022-07-01"), 120L, client1);
            Transaction transaction2 = new Transaction(2L, getDate("2022-08-10"), 90L, client1);
            Transaction transaction3 = new Transaction(3L, getDate("2022-09-17"), 45L, client1);
            Transaction transaction4 = new Transaction(4L, getDate("2022-09-27"), 49L, client1);
            List<Transaction> transactionList = new ArrayList<>();
            Collections.addAll(transactionList, transaction1, transaction2, transaction3, transaction4);
            return transactionList;
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static List<Client> getTestClients() {
        Client client1 = new Client(1L, "Alex", "Yuki", "123-34-3456");
        Client client2 = new Client(2L, "Brent", "Sharp", "223-34-3456");
        Client client3 = new Client(3L, "Camille", "Claudel", "356-34-3456");

        List<Client> clientList = new ArrayList<>();
        Collections.addAll(clientList, client1, client2, client3);
        return clientList;
    }

    private static Date getDate(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date date = formatter.parse(dateInString);
        return date;
    }
}
