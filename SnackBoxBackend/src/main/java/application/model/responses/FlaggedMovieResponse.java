package application.model.responses;

import application.model.ResponseData;
import application.model.entities.User;

import java.util.Set;

public class FlaggedMovieResponse implements ResponseData {

    private long movieId;
    private String movieName;
    private Set<User> flaggedUsers;

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Set<User> getFlaggedUsers() {
        return flaggedUsers;
    }

    public void setFlaggedUsers(Set<User> flaggedUsers) {
        this.flaggedUsers = flaggedUsers;
    }

    public FlaggedMovieResponse() {
    }

    public FlaggedMovieResponse(long movieId, String movieName, Set<User> flaggedUsers) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.flaggedUsers = flaggedUsers;
    }



}
