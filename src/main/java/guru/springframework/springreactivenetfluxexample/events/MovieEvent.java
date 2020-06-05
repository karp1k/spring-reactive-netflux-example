package guru.springframework.springreactivenetfluxexample.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEvent {

    private String movieId;
    private Date date;
}
