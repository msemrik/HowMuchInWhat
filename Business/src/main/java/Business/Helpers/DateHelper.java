package Business.Helpers;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHelper {

    public static String getDayMonthYear(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dateTime.toString(dateTimeFormat);

    }

    public static Date setDayMonthYearHourMinute(Date date, int day, int month, int year, int hour, int minute) {
        DateTime dateTime = new DateTime(date).withDate(year, month, day).withTime(hour, minute, 0, 0);
        return dateTime.toDate();
    }

    public static Date setHourAndMinute(Date date, int hour, int minute) {
        DateTime dateTime = new DateTime(date).withTime(hour, minute, 0, 0);
        return dateTime.toDate();
    }

    public static boolean isToday(Date date1) {
        DateTime dateTime = new DateTime(date1);
        DateTime dateTimeToday = DateTime.now();
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("dd/MM/yyyy");
        return (dateTime.toString(dateTimeFormat).equals(dateTimeToday.toString(dateTimeFormat)));
    }

    public static boolean areEqual(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return date1 == date2;
        }
        return date1.equals(date2);
    }

    public static boolean areDayMonthYearEqual(Date date1, Date date2) {
        String firstDate = getDayMonthYear(date1);
        String secondDate = getDayMonthYear(date2);
        return firstDate.equals(secondDate);
    }

    public static boolean isAfter(Date date1, Date date2) {
        DateTime dateTime = new DateTime(date1);
        DateTime dateTime2 = new DateTime(date2);
        return dateTime.isAfter(dateTime2);
    }

    public static String getDetailedDifference(Date startDate) {
        DateTime endDateTime = new DateTime(new Date());
        DateTime startDateTime = new DateTime(startDate);
        Long difference = endDateTime.getMillis() - startDateTime.getMillis();

        long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(difference) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference));
        long millis = difference - TimeUnit.SECONDS.toMillis(seconds) - TimeUnit.MINUTES.toMillis(minutes);

        return String.format("%d min, %d sec, %d millis", minutes, seconds, millis);
    }
}
