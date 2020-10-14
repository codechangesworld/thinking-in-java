package name.zuoguoqing.exercise.datetime;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class DateAndTimeExample {

    public static void localDateExample() {
        LocalDate date = LocalDate.now();

        System.out.println("*****LocalDate Example*****");
        System.out.println("LocalDate: " + date);
        System.out.println("Year: " + date.getYear());
        System.out.println("MonthValue: " + date.getMonthValue());
        System.out.println("Month: " + date.getMonth());
        System.out.println("Month.Name: " + date.getMonth().name());
        System.out.println("DayOfYear: " + date.getDayOfYear());
        System.out.println("DayOfMonth: " + date.getDayOfMonth());
        System.out.println("DayOfWeek: " + date.getDayOfWeek());
        System.out.println("DayOfWeek.Value: " + date.getDayOfWeek().getValue());
        System.out.println("DayOfWeek.Name: " + date.getDayOfWeek().name());

        System.out.println("Year: " + date.get(ChronoField.YEAR));
        System.out.println("Month: " + date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("DayOfYear: " + date.get(ChronoField.DAY_OF_YEAR));

        System.out.println("LocalDate.parse: " + LocalDate.parse("2020-10-10"));
        // System.out.println("LocalDate.parse: " + LocalDate.parse("2020/10/10"));
        System.out.println(
                "LocalDate.parse: " + LocalDate.parse("2020/10/10", 
                DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        System.out.println("DateTimeFormatter.BASIC_ISO_DATE:" + date.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("DateTimeFormatter.ISO_DATE:" + date.format(DateTimeFormatter.ISO_DATE));
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE:" + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
    
    public static void localTimeExample() {
        LocalTime time = LocalTime.now();
        
        System.out.println("*****LocalTime Example*****");
        System.out.println("LocalTime: " + time);
        System.out.println("Hour: " + time.getHour());
        System.out.println("Minute: " + time.getMinute());
        System.out.println("Second: " + time.getSecond());
        
        System.out.println("Hour: " + time.get(ChronoField.HOUR_OF_DAY));
        System.out.println("Hour: " + time.get(ChronoField.CLOCK_HOUR_OF_DAY));
        System.out.println("Hour: " + time.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("Minute: " + time.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("Second: " + time.get(ChronoField.SECOND_OF_MINUTE));

        System.out.println("LocalTime.parse: " + LocalTime.parse("12:30:20"));
        System.out.println("LocalTime.parse: " + LocalTime.parse("12,30,20", 
                                DateTimeFormatter.ofPattern("HH,mm,ss")));
                                
        System.out.println("DateTimeFormatter.ISO_TIME: " + time.format(DateTimeFormatter.ISO_TIME));
        System.out.println("DateTimeFormatter.ISO_LOCAL_TIME: " + time.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    public static void localDateTimeExample() {
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("*****LocalDateTime Example*****");
        System.out.println("LocalDateTime: " + dateTime);
        System.out.println("LocalDateTime: " + LocalDateTime.of(2020, 12, 23, 20, 30, 25));
        System.out.println("ToLocalDate: " + dateTime.toLocalDate());
        System.out.println("ToLocalTime: " + dateTime.toLocalTime());

    }

    public static void instantExample() {
        Instant instant = Instant.now();
        System.out.println("*****Instant Example*****");
        System.out.println("Instant: " + instant);
        System.out.println("Instant.ofEpochMilli: " + Instant.ofEpochMilli(100_000));
        System.out.println("Instant.ofEpochSecond: " + Instant.ofEpochSecond(100_000));
        System.out.println("Instant.ofEpochSecond: " + Instant.ofEpochSecond(100_000, 200));
        System.out.println("Instant.parse: " + Instant.parse("2020-10-13T10:15:30.00Z"));
        System.out.println("Instant: " + instant.isAfter(Instant.ofEpochSecond(1_000_000)));

    }

    public static void periodExample() {
        System.out.println("*****Period Example*****");

        Period period = Period.between(LocalDate.now(), LocalDate.of(1991, 7, 19));
        System.out.println("Period: " + period);
        System.out.println("Period.getYears: " + period.getYears());
        System.out.println("Period.MONTHS: " + period.get(ChronoUnit.MONTHS));
        System.out.println("Period.getDays: " + period.getDays());
        // Unsupported unit: Hours
        // System.out.println("Period.HOURS: " + period.get(ChronoUnit.HOURS));
        System.out.println("Period.minusMonths: " + period.minusMonths(3));

    }

    public static void durationExample() {
        System.out.println("*****Duration Example*****");

        Duration duration = Duration.between(LocalTime.now(), LocalTime.of(7, 0));
        System.out.println("Duration: " + duration);
        System.out.println("Duration: " + duration.getSeconds());
        System.out.println("Duration.SECONDS: " + duration.get(ChronoUnit.SECONDS));
        // Unsupported unit: Minutes
        // System.out.println("Duration.MINUTES: " + duration.get(ChronoUnit.MINUTES));
        // Unsupported unit: Hours
        // System.out.println("Duration.HOURS: " + duration.get(ChronoUnit.HOURS));
        System.out.println("Duration.plusDays: " + duration.plusDays(3));

    }

    public static void temporalAdjuster() {
        System.out.println("*****TemporalAdjuster Example*****");
        LocalDate date = LocalDate.of(2020, 10, 12);
        System.out.println("LocalDate: " + date);
        System.out.println("TemporalAdjusters.nextOrSame: "
        + date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)));
        System.out.println("TemporalAdjusters.lastDayOfMonth: "
                                 + date.with(TemporalAdjusters.lastDayOfMonth()));
                                 System.out.println("TemporalAdjusters.firstDayOfNextMonth: "
                                 + date.with(TemporalAdjusters.firstDayOfNextMonth()));
                                }

    public static void zoneExample() {
        System.out.println("*****Zone Example*****");

        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println("ZoneId: " + ZoneId.systemDefault());
        System.out.println("ZoneId: " + zoneId);
        zoneId = ZoneId.of("America/New_York");
        System.out.println("ZoneId: " + zoneId);
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("atZone: " + dateTime.atZone(zoneId));
        System.out.println("now: " + LocalDateTime.now(zoneId));

        System.out.println("AvaiableZoneIds: " + ZoneId.getAvailableZoneIds().stream().filter(s -> {
            return s.contains("America");
        }).collect(Collectors.toSet()));

    }

    public static void main(String[] args) {
        localDateExample();
        localTimeExample();
        localDateTimeExample();
        instantExample();
        periodExample();
        durationExample();
        temporalAdjuster();
        zoneExample();
    }
}
