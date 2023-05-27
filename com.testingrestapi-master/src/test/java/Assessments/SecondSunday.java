package Assessments;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class SecondSunday {
    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusMonths(1);

        LocalDate secondSunday = nextMonth.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SUNDAY));
        System.out.println("The date of the second Sunday of next month is: " + secondSunday);

        /*In this class, we first get the current date using LocalDate.now(), and then calculate the date of the next month by adding one month to the current date using
        today.plusMonths(1).

        Next, we use the TemporalAdjusters.dayOfWeekInMonth() method to get the second Sunday of the next month.
        This method takes two arguments: the ordinal number of the day of the week (in this case, 2 for the second Sunday),
        and the DayOfWeek enum value for the day of the week (in this case, DayOfWeek.SUNDAY).

        Finally, we print the date of the second Sunday of next month using System.out.println().
    */}
    }
