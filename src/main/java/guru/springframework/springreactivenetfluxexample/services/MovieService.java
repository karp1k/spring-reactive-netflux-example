package guru.springframework.springreactivenetfluxexample.services;

import guru.springframework.springreactivenetfluxexample.domain.Movie;
import guru.springframework.springreactivenetfluxexample.events.MovieEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author kas
 */
public interface MovieService {

    Flux<MovieEvent> events(String movieId);

    Mono<Movie> getMovieById(String id);

    Flux<Movie> getAllMovies();
}
