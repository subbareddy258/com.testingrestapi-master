package Assessments;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseANumber11 {
    public static void main(String[] args) {
         int i = 5678;
       String str=  String.valueOf(i);
       System.out.println(str);
        //Stream.of() method returns Stream primitive type
        //  Arrays.stream() method for non-primitive and returns IntStream
      //  StringBuilder str1= new StringBuilder(str);
    System.out.println(Stream.of(str).map(String -> new  StringBuilder(str).reverse()).collect(Collectors.joining()));

        //System.out.println(str1.reverse());
        int number = 123456789;
        String reversedNumber = String.valueOf(number)
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        int result = Integer.parseInt(reversedNumber);
        System.out.println("Reversed number: " + result);
    }

}
