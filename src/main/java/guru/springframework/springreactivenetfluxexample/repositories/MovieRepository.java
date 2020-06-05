package guru.springframework.springreactivenetfluxexample.repositories;

import guru.springframework.springreactivenetfluxexample.domain.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author kas
 */
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
