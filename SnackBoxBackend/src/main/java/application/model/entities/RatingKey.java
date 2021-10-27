package application.model.entities;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RatingKey implements Serializable {
    @Column(name = "movie_id")
    Long movieId;

    @Column(name = "user_id")
    Long userId;

    public RatingKey(Long movieId, Long userId) {
        this.movieId = movieId;
        this.userId = userId;
    }

    public RatingKey() {
    }
}
