package guru.springframework.springreactivenetfluxexample.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kas
 */
@Data
@NoArgsConstructor
public class MovieEvent {

    private String movieId;
    private Date date;
}
