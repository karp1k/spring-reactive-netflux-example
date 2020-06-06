package guru.springframework.springreactivenetfluxexample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author kas
 */
@Slf4j
class ClosureEffectivelyFinalAndLazyEval {

    @Test
    void lambdaExample() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        arr.stream()
                .map(n -> n * 2) // stateless
                .forEach(n -> log.info(String.valueOf(n)));
    }

    @Test
    void closureExample() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer multiplier = 2; // lexical scope
        arr.stream()
                .map(n -> n * multiplier) // stateless
                .forEach(n -> log.info(String.valueOf(n)));
    }

    @Test
    void closureUsingFinal() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final Integer multiplier = 2; // lexical scope
        arr.stream()
                .map(n -> n * multiplier) // stateless
                .forEach(n -> log.info(String.valueOf(n)));
    }

    @Test
    void closureEffectivelyFina() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer multiplier = 2; // lexical scope
        arr.stream()
                .map(n -> n * multiplier) // stateless
                .forEach(n -> log.info(String.valueOf(n)));

        //multiplier = 3;
    }

    @Test
    void breakingFinal() {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Integer[] multiplier = {2}; // lexical scope
        Stream<Integer> integerStream = arr.stream()
                .map(n -> n * multiplier[0]);

        multiplier[0] = 0;
        integerStream.forEach(n -> log.info(String.valueOf(n)));
    }


}
