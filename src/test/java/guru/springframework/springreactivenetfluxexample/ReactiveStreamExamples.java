package guru.springframework.springreactivenetfluxexample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author kas
 */
@Slf4j
public class ReactiveStreamExamples {

    Flux<String> dogs = Flux.just("Vizsla", "Lab", "Golden", "GSP", "Yorkie");

    @Test
    void simpleStreamExample() {
        // flux is a publisher
        dogs.toStream().forEach(log::info);
    }

    @Test
    void simpleStreamExample2() {
        // subscribing on publisher
        dogs.subscribe(log::info);
    }

    @Test
    void simpleStreamExample3() {
        // reactive streams are lazy
        // subscribing on doOnEach
        dogs.doOnEach(signal -> log.info(signal.get())).subscribe();
    }

    @Test
    void differentSubscribers() {
        dogs.subscribe(s -> log.info(s), null, () -> log.info("I am done"));
    }

    @Test
    void differentSubscriberWithConsumers() {
        Consumer<String> handler = log::info;
        Consumer<Throwable> errorHandler = e -> log.info("There is some error");
        Runnable allDone = () -> log.info("Everything is done");
        dogs.subscribe(handler, errorHandler, allDone);
    }

    @Test
    void mapStreamExample() {
        dogs.map(String::length).doOnEach(signal -> log.info(signal.toString())).subscribe();
    }

    @Test
    void filterStreamExample() {
        dogs.filter(s -> s.length() == 6).subscribe(log::info);

    }

    @Test
    void filterAndLimitStreamExample() {
        dogs.filter(s -> s.length() == 6)
                .take(1) // like limit in java 8 stream
                .subscribe(log::info);
    }

    @Test
    void filterAndSortStreamExample() {
        dogs.filter(s -> s.length() == 6)
                .take(2) // like limit in java 8 stream
                .sort()
                .subscribe(log::info);
    }

    @Test
    void collectorExampleStream() {
        dogs.filter(s -> s.length() == 6)
                .take(3) // like limit in java 8 stream
                .sort()
                .collect(Collectors.joining(", ")) // collect return Mono instead of Flux
                .subscribe(log::info);
    }


    @Test
    void flatMap() {
        Flux<List<List<Number>>> fluxOfList = Flux.just(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5)));
        fluxOfList.flatMap(lists -> Flux.fromIterable(lists))
                .flatMap(lists -> Flux.fromIterable(lists))
                .subscribe(l -> log.info(l.toString()));
    }

    @Test
    void flatMap2() {
        Flux<List<List<Integer>>> fluxOfList = Flux.just(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5)));
        fluxOfList.flatMap(lists -> Flux.fromIterable(
                (lists.stream()
                        .flatMap(Collection::stream)
                ).collect(Collectors.toList())))
                .subscribe(System.out::println);
    }

    @Test
    void testReduction() {
        dogs.reduce((a, b) -> a + " - " + b).subscribe(s -> log.info(s));
    }
}
