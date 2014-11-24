package test.java.date;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DateTest {

    @Test
    public void shouldTestLocalDate() {
        LocalDate date = LocalDate.of(2014, 3, 20);

        assertThat(date.getYear(), equalTo(2014));
        assertThat(date.getMonth(), equalTo(Month.MARCH));
        assertThat(date.getMonthValue(), equalTo(3));
        assertThat(date.getDayOfMonth(), equalTo(20));
        assertThat(date.getDayOfWeek(), equalTo(DayOfWeek.THURSDAY));
        assertThat(date.lengthOfMonth(), equalTo(31));
        assertThat(date.isLeapYear(), equalTo(false));

        LocalTime localTime = LocalTime.of(15, 12, 13);
        assertThat(localTime.getHour(), equalTo(15));
        assertThat(localTime.getMinute(), equalTo(12));
        assertThat(localTime.getSecond(), equalTo(13));
    }

    @Test
    public void shouldTestInstant() {
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));

        System.out.println(Instant.now());
    }


    @Test
    public void shouldTestDurationAndPeriod(){
//        Duration d1 = Duration.between(time1, time2);
//        Duration d1 = Duration.between(dateTime1, dateTime2);
//        Duration d2 = Duration.between(instant1, instant2);

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
        Duration threeMinutes1 = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays1 = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        Instant.now().plus(threeMinutes1);

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1990, Month.FEBRUARY, 7);

        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                " months, and " + p.getDays() +
                " days old. (" + p2 + " days total)");
    }

    @Test
    public void manipulateLocalDate(){
        LocalDate date1 = LocalDate.of(2014, 10, 25);
        System.out.println(date1);
        LocalDate date2 = date1.withYear(2011);
        System.out.println(date2);
        LocalDate date3 = date2.withDayOfMonth(11);
        System.out.println(date3);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 11);
        System.out.println(date4);

        LocalDate date5 = date1.minusYears(3);
        System.out.println(date5);
        LocalDate date6 = date5.plus(1L, ChronoUnit.MONTHS);
        System.out.println(date6);
        LocalDate date7 = date6.minusWeeks(2);
        System.out.println(date7);
    }

    @Test
    public void testTemporalAdjusters(){
        System.out.println("temporaladjusters");
        LocalDate now = LocalDate.now();
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(now.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)));
        System.out.println(now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
    }

}
