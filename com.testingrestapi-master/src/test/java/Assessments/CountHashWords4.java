package Assessments;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountHashWords4 {
    public static void main(String[] args) {
        String str = "subba reddy won the cricket match match";

        System.out.println(Stream.of(str.split(" ")).map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
        System.out.println(Arrays.stream(str.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        HashMap<String, Integer> map = new HashMap<>();
        String[] str1 = str.split(" ");
        for (String count : str1) {
            if (map.containsKey(count)) {
                map.put(count, map.get(count) + 1);
            } else {
                map.put(count, 1);
            }
        }
        System.out.println(map);

       /* for (int i = 0; i < str1.length-1; i++) {
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }
        System.out.println("second for loop" + map);
*/}

        }
