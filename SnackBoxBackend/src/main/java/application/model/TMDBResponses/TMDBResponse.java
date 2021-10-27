package application.model.TMDBResponses;

import application.model.TMDBResponses.TMDBMovie;

import java.util.List;

public class TMDBResponse {

    private int page;
    private List<TMDBMovie> results;


    public TMDBResponse() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TMDBMovie> getResults() {
        return results;
    }

    public void setResults(List<TMDBMovie> results) {
        this.results = results;
    }
}
