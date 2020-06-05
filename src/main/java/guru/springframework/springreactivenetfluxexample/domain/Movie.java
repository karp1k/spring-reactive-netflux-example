package guru.springframework.springreactivenetfluxexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author kas
 */
@Document // mongodb entity aka mongodb document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private String id;
    @NonNull
    private String title;


}
