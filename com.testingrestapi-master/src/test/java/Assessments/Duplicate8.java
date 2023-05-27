package Assessments;

import java.util.Map;
import java.util.stream.Collectors;

public class Duplicate8 {
    public static void main(String[] args) {
        String str = "subbareddy";
       // Collectors.groupingBy() – this is the method of Collectors class to group objects by some property and store results in a Map instance
       // Collectors.counting() – this Collectors class method counts the number of elements passed in the stream as a parameter
//string is converting to stream
        Map< Character, Long > result = str
                .chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));


        result.forEach((k, v) -> {
            if (v > 1) {
                System.out.println(k + " : " + v);
            }
        });
    }
}
