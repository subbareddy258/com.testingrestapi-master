package Assessments;

import java.util.Arrays;

public class CountNoOfWords2 {
    public static void main(String[] args) {
        String str = "subba reddy won the match";
        long result= Arrays.stream(str.split(" ")).count();

       // Map<String,Integer> result1= Arrays.stream(str.split(" "));
     /*  Map<Long, String>  result2 = Arrays.asList(str.split(" ")).stream().collect(Integer::sum);
                              System.out.println(result2);
     */   System.out.println(result);

    }
    }
