package application.model.requests;

import org.springframework.stereotype.Component;

@Component
public class RatingRequest {
    private short rating;
    private long movieId;

    public RatingRequest(short rating, long movieId) {
        this.rating = rating;
        this.movieId = movieId;
    }

    public RatingRequest() {
    }

    public short getRating() {
        return rating;
    }

    public long getMovieId(){
        return this.movieId;
    }

}
