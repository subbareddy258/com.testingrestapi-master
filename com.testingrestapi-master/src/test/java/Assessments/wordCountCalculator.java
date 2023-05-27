package Assessments;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class wordCountCalculator {
    public static void main(String[] args) {
        /*String s = "This is subba reddy k v";

        wordCount wordCounts = (str) -> {
            String[] words = str.split(" ");
            return words.length;
        };
        int count = wordCounts.count(s);
        System.out.println("Number of words: " + count);
    */
        List<String> titles = given()
                .when()
                .get("https://reqres.in/api/users?per_page=2&page=2")
                .then()
                .extract()
                .response()
                .jsonPath()
                .getList("data.email");
        titles = titles.stream()
                .filter(title -> title.startsWith("e"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
          System.out.println(titles);
    }
    }
