package application.model.TMDBResponses;

import java.util.List;

public class GenresResponse {

    private List<TMDBGenre> genres;

    public GenresResponse() {
    }

    public GenresResponse(List<TMDBGenre> genres) {
        this.genres = genres;
    }

    public List<TMDBGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<TMDBGenre> genres) {
        this.genres = genres;
    }
}



