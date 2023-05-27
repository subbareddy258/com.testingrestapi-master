package Assessments;

import java.util.Arrays;

public class SecondHighestNumber9 {
    public static void main(String[] args) {

        int[] array= {17, 43, 54, 10, 8, 86};
     Arrays.sort(array);
        System.out.println("sorted Array ::"+ Arrays.toString(array));
        int num = array[array.length-2];
        System.out.println("2nd highest number"+num);

    }
    }
