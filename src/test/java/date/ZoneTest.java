package test.java.date;

import org.junit.Test;

import java.time.*;
import java.util.TimeZone;

public class ZoneTest {

    @Test
    public void zoneTest(){
        // saat 19:27
        ZoneId romeZone = ZoneId.of("Europe/Rome");

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println(zdt2);                // "2014-03-18T13:45+01:00[Europe/Rome]"

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println(zdt3);                              // "2014-11-24T18:27:54.537+01:00[Europe/Rome]"
        ZoneId defaultZone = TimeZone.getDefault().toZoneId(); // istanbul
        System.out.println(instant.atZone(defaultZone));       // "2014-11-24T19:27:54.537+02:00[Europe/Istanbul]"
        System.out.println(instant.atZone(ZoneId.of("UTC+0")));// "2014-11-24T17:27:54.537Z[UTC]"
    }
}
