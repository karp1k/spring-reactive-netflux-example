package guru.springframework.springreactivenetfluxexample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kas
 */
@Slf4j
class Java8StreamExamples {
    List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
    List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Yorkie");

    @Test
    void simpleStreamExample() {
        dogs.stream().forEach(s -> log.info(String.valueOf(s)));

    }

    @Test
    void parallelStreamExample() {
        dogs.parallelStream().forEach(s -> log.info(s));
    }

    @Test
    void mapStreamExample() {
        dogs.stream().map(String::length).forEach(s -> log.info(String.valueOf(s)));
    }

    @Test
    void filterStreamExample() {
        dogs.stream().filter(s -> s.length() == 6).forEach(s -> log.info(String.valueOf(s)));
    }

    @Test
    void limitStreamExample() {
        dogs.stream().filter(s -> s.length() == 6).limit(1).forEach(s -> log.info(String.valueOf(s)));
    }

    @Test
    void sortStreamExample() {
        dogs.stream().filter(s -> s.length() == 6).sorted().forEach(s -> log.info(String.valueOf(s)));
    }

    @Test
    void collectorStreamExample() {
        String dogsString = dogs.stream().filter(s -> s.length() == 6).sorted().collect(Collectors.joining(", "));
        log.info(dogsString);
    }

    @Test
    void functionalCompStreamExample() {
        String dogsString = dogs.stream()
                .filter(s -> s.length() == 6)
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));
        log.info(dogsString);
    }

    @Test
    void testStats() {
        IntSummaryStatistics statistics = arr.stream().mapToInt(x -> x).summaryStatistics();
        log.info(statistics.toString());
    }

    @Test
    void testMax() {
        OptionalInt optionalInt = arr.stream().mapToInt(x -> x).max();
        log.info(String.valueOf(optionalInt.getAsInt()));
    }

    @Test
    void testSum() {
        int sum = arr.stream().mapToInt(x -> x).sum();
        log.info(String.valueOf(sum));
    }

    @Test
    void groupingBy() {
        Map<Integer, Set<String>> groupMap = dogs.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        groupMap.entrySet().stream().forEach(e -> log.info(e.toString()));
    }

    @Test
    void flatMap() {
        List<List<Integer>> listOfList = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
        List<Integer> newList = listOfList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());// stream number of each list
        newList.forEach(n -> log.info(String.valueOf(n)));
    }

    @Test
    void testReduction() {
        String s = dogs.stream().reduce((a, b) -> a + " - " + b)
                .get();

        log.info(s);
    }
}
