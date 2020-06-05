package guru.springframework.springreactivenetfluxexample.controllers;

import guru.springframework.springreactivenetfluxexample.domain.Movie;
import guru.springframework.springreactivenetfluxexample.events.MovieEvent;
import guru.springframework.springreactivenetfluxexample.services.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author kas
 */
@RequestMapping("/movie")
@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // by default is json, but in this case method movieService.event() return stream of data
    @GetMapping(value = "/{id}/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> streamMovieEvent(@PathVariable String id) {
        return movieService.events(id);
    }

    @GetMapping("/{id}")
    Mono<Movie> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping
    Flux<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
