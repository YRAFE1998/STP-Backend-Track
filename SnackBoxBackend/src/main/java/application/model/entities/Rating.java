package application.model.entities;

import application.model.ResponseData;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "users_rate_movies")
public class Rating implements ResponseData {

    @EmbeddedId
    RatingKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "movie_id")
    Movie movie;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "user_id")
    User user;

    short rating;

    public Rating(RatingKey id, short rating) {
        this.id = id;
        this.rating = rating;
    }

    public Rating() {
    }

    public RatingKey getId() {
        return id;
    }

    public void setId(RatingKey id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }
}
