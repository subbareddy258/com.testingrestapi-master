package Assessments;

import java.time.LocalDate;
import java.time.Period;

public class Experience {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2021, 10, 1); // set your start date here
        LocalDate endDate = LocalDate.now(); // set your end date here

        Period experience = Period.between(startDate, endDate);
        int years = experience.getYears();
        int months = experience.getMonths();
        int days = experience.getDays();

        System.out.println("Your work experience: " + years + " years, " + months + " months, and " + days + " days.");
        //Next, we use the Period.between() method to calculate the period between the start date and end date, which returns a Period object. We then extract the number of years, months, and days from this object using the getYears(), getMonths(), and getDays() methods, respectively.
    }
    }
