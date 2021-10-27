package application.model.requests;

import org.springframework.stereotype.Component;

@Component
public class FlagRequest {

    private long movieId;

    public FlagRequest(long movieId) {
        this.movieId = movieId;
    }

    public FlagRequest() {
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }
}
