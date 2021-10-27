package application.model.TMDBResponses;

public class TMDBGenre{
    private int id;
    private String name;

    public TMDBGenre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TMDBGenre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
