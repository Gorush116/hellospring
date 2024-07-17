package tobyspring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        List<Integer> scores = Arrays.asList(5, 7, 1, 9, 2, 8);
        List<String> stringList = Arrays.asList("z", "x", "spring", "java");
        Collections.sort(scores);
        Collections.sort(stringList);

        System.out.println("scores = " + scores);
        System.out.println("stringList = " + stringList);
    }
}
