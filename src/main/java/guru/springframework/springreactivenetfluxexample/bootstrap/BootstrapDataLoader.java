package guru.springframework.springreactivenetfluxexample.bootstrap;

import guru.springframework.springreactivenetfluxexample.domain.Movie;
import guru.springframework.springreactivenetfluxexample.repositories.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author kas
 */
@Slf4j
@Component
public class BootstrapDataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public BootstrapDataLoader(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //clear old data. Not sure why John doing that, bc we are using embedded instance of mongo db
        movieRepository.deleteAll().thenMany(
            Flux
                .just("Rick and Morty", "The Borne Identity", "The Note Book", "Star Wars: Episode V - The Empire Strikes Back")
                .map(t -> new Movie(UUID.randomUUID().toString(), t))
                .flatMap(movieRepository::save)
        ).subscribe(null, null, () -> movieRepository.findAll()
                .subscribe(m -> log.info(m.toString()))
        );

    }
}
