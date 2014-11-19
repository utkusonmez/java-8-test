package test.java.stream;

import main.java.trader.Trader;
import main.java.trader.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TraderTest {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = null;

    @Before
    public void before() {
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void shouldFind2011TransactionsAndSortThemByValueAscending() {
        List<Transaction> list = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        assertThat(list.get(0).getValue(), equalTo(300));
        assertThat(list.get(0).getTrader(), equalTo(brian));
        assertThat(list.get(1).getValue(), equalTo(400));
        assertThat(list.get(1).getTrader(), equalTo(raoul));
        assertThat(list.size(), equalTo(2));
    }

    @Test
    public void shouldFindUniqueCities() {
        List<String> list = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        assertThat(list.get(0), equalTo("Cambridge"));
        assertThat(list.get(1), equalTo("Milan"));
        assertThat(list.size(), equalTo(2));
    }

    @Test
    public void shouldFindAllCambridgeTradersAndSortThemByName() {
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        assertThat(traders.get(0).getName(), equalTo("Alan"));
        assertThat(traders.get(1).getName(), equalTo("Brian"));
        assertThat(traders.get(2).getName(), equalTo("Raoul"));
        assertThat(traders.size(), equalTo(3));
    }

    @Test
    public void shouldFindAllTraderNames() {
        List<String> traders = transactions.stream()
                .map((t) -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        assertThat(traders.get(0), equalTo("Alan"));
        assertThat(traders.get(1), equalTo("Brian"));
        assertThat(traders.get(2), equalTo("Mario"));
        assertThat(traders.get(3), equalTo("Raoul"));
        assertThat(traders.size(), equalTo(4));
    }

    @Test
    public void shouldAnyMilanTrader() {
        boolean matches = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        assertThat(matches, equalTo(true));
    }

    @Test
    public void shouldPrintAllCambridgeTradersTransactions() {
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    public void shouldFindMaximumTransactionValue() {
        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);

        assertThat(max, equalTo(1000));
    }

    @Test
    public void shouldFindMininumumTransaction() {
        Optional<Transaction> first = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getValue))
                .findFirst();

        assertThat(first.get().getValue(), equalTo(300));
    }
}
