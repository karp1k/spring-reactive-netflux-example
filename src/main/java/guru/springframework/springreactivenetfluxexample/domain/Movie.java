package guru.springframework.springreactivenetfluxexample.domain;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author kas
 */
@Document // mongodb entity aka mongodb document
@Data
public class Movie {

    private String id;
    @NonNull
    private String title;


}
