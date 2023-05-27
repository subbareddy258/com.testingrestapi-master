package Assessments;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoStreams {
    public static void main(String[] args) {
//count leength greater than 5
        String str = "subba reddy won the cricket and football match";
        long result= Stream.of(str.split(" ")).filter(word -> word.length() >5).count();
        System.out.println("Number of strings  length is greater than 5 is   : " + result);

        //count leength greater than 5

        Stream<String> stringStream = Stream.of("subba", "reddy", "", "k", "v", "cricket", "football", "","");
        long count = stringStream.filter(w -> w.length() > 5).count();
        System.out.println("Number of strings  length is greater than 5 is   : " + count);

//count empty strings
        List<String>  result2 = Arrays.asList("subba", "", "reddy", "", "K", "", "V", "", "");

        long result3= result2.stream().filter(String :: isEmpty).count();
        System.out.println("Number of strings  length is empty   : " + result3);

        // store non empty word to list and ignore empty words
        List<String> notEmptyWords = result2.stream()
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());

        System.out.println("Original list of words: " + result3);
        System.out.println("List of not-empty words: " + notEmptyWords);


        //alternative way
       /* Supplier<Stream<String>> stringStreamSupplier = () -> Stream.of("subba", "reddy", "", "k", "v", "cricket", "football", "","");

        long emptyCount = stringStreamSupplier.get().filter(w -> w.isEmpty()).count();
        long greaterThanFiveCount = stringStreamSupplier.get().filter(w -> w.length() > 5).count();

        System.out.println("Number of empty strings: " + emptyCount);
        System.out.println("Number of strings with length > 5: " + greaterThanFiveCount);

        List<String> nonEmptyList = stringStreamSupplier.get().filter(w -> !w.isEmpty()).collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Original stream: " + stringStreamSupplier.get().collect(Collectors.toList()));
        System.out.println("New list with non-empty strings: " + nonEmptyList);
*/
    }
    }
