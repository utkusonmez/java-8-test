package test.java.transaction;

import main.java.transaction.CurrencyType;
import main.java.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TransactionTest {

    private List<Transaction> transactions = new ArrayList<>();

    @Before
    public void before() {
        transactions.add(new Transaction(1100d, CurrencyType.TL));
        transactions.add(new Transaction(100d, CurrencyType.TL));
        transactions.add(new Transaction(999d, CurrencyType.TL));

        transactions.add(new Transaction(10d, CurrencyType.USD));
        transactions.add(new Transaction(800d, CurrencyType.USD));
        transactions.add(new Transaction(900d, CurrencyType.USD));

        transactions.add(new Transaction(200d, CurrencyType.EUR));
        transactions.add(new Transaction(1100d, CurrencyType.EUR));
        transactions.add(new Transaction(1000d, CurrencyType.EUR));
        transactions.add(new Transaction(100d, CurrencyType.EUR));
    }

    @Test
    public void shouldFilterOver1000TransactionsCount() {
        Stream<Transaction> filtered = transactions.stream().filter((Transaction t) -> t.getPrice() > 1000);

        assertTrue(filtered.count() == 2);
    }

    @Test
    public void shouldFilterOver1000Transactions() {
        List<Transaction> filtered = transactions.stream()
                .filter((Transaction t) -> t.getPrice() > 1000)
                .collect(Collectors.toList());

        assertTrue(filtered.get(0).getPrice() == 1100);
        assertTrue(filtered.get(1).getPrice() == 1100);
    }

    @Test
    public void shouldGroupByCurrencyType() {
        Map<CurrencyType, List<Transaction>> transactionMap = transactions.stream()
                .filter((Transaction t) -> t.getPrice() > 800)
                .collect(Collectors.groupingBy(Transaction::getCurrenyType));

        assertTrue(transactionMap.get(CurrencyType.TL).size() == 2);
        assertTrue(transactionMap.get(CurrencyType.USD).size() == 1);
        assertTrue(transactionMap.get(CurrencyType.EUR).size() == 2);
    }

}