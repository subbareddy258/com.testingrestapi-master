package Assessments;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseAString1 {
    public static void main(String[] args) {
        String str = "subbareddy";
        //Stream.of() method returns Stream primitive type r
      //  Arrays.stream() method for non-primitive and returns IntStream
        //joining() - concatenates the input elements into a String, in encounter order.
    String reverse=    Stream.of(str).map(String -> new StringBuilder(str).reverse())
                .collect(Collectors.joining());
    System.out.println(reverse);
    }
    }
