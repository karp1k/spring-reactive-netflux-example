package guru.springframework.springreactivenetfluxexample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author kas
 */
@Slf4j
class FunctionalProgrammingExamples {

    @Test
    void functionWithThing() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("In thread!");
            }
        });

        t.start();
        log.info("In main thread");
    }

    @Test
    void lambdaExpression() {
        Thread t = new Thread(() -> log.info("Silence of the lambdas"));
        t.start();
        log.info("In main thread");
    }

    @Test
    void listIteratorHigh() {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP");
        for (int i = 0; i < dogs.size(); i ++) {
            log.info(dogs.get(i));
        }
    }

    @Test
    void listIteratorLessCeremonyExternalIter() {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP");
        for (String s: dogs) {
            log.info(s);
        }
    }

    @Test
    void listInternalIterConsumer() {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP");
        dogs.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info(s);
            }
        });
    }


    @Test
    void listInternalIterLambdaMethod() {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP");
        dogs.forEach(s -> log.info(s));
    }

    @Test
    void listInternalIterLambdaMethodTypeJava8MethodReference() {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP");
        dogs.forEach(log::info);
    }

    @Test
    void countDogsWithSixCharactersImp() {
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Yorkie");
        int count = 0;
        for (String s: dogs) {
            if (s.length() == 6) {
                count++;
            }
        }
        log.info(String.valueOf(count));
    }

    @Test
    void countDogsWithSixCharacters() {
        // no mutability
        List<String> dogs = Arrays.asList("Vizsla", "Lab", "Golden", "GSP", "Yorkie");
        log.info(String.valueOf(dogs.stream()
                .filter(d -> d.length() == 6)
                .collect(Collectors.toList()).size()));
    }
}
