package application.model.entities;

import application.model.ResponseData;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Table(name = "movies")
public class Movie implements ResponseData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 255)
    private long id;


    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "release_date")
    //@Temporal(TemporalType.DATE)
    private LocalDate releaseDate;

    @Column(name = "rating")
    private float rating;

    @Column(name = "poster_path", length = 100)
    private String posterPath;

    @Column(name = "trailer_path", length = 100)
    private String trailerPath;


    @Column(name = "description")
    private String description;

    @Column(name = "popularity")
    private int popularity;


    @Column(name = "duration", length = 15)
    private short duration;

    @Column(name = "source", length = 5)
    private String source;

    @ElementCollection
    @CollectionTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    private List<String> genres = new ArrayList<String>();

    @Column(name = "inappropriate")
    private boolean inappropriate;

    @ElementCollection
    @CollectionTable(
            name = "movie_languages",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    private List<String> languages= new ArrayList<String>();

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    Set<Rating> ratings = new HashSet<>();


    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_flag_movies",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Set<User> flaggedUsers = new HashSet<>();




    public Movie() {
        this.inappropriate = false;
    }

    public Movie(String name) {
        this.name = name;
    }

    public Movie(String name, LocalDate releaseDate, float rating, String posterPath, String trailerPath, String description, int popularity, short duration) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.posterPath = posterPath;
        this.trailerPath = trailerPath;
        this.description = description;
        this.popularity = popularity;
        this.duration = duration;
        this.inappropriate =false;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTrailerPath() {
        return trailerPath;
    }

    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public void addGenre(String genre){
        genres.add(genre);
    }

    public void  addLanguage(String lang){
        languages.add(lang);
    }

    public void addFlag(User user){
        flaggedUsers.add(user);
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public boolean isInappropriate() {
        return inappropriate;
    }

    public void setInappropriate(boolean inappropriate) {
        this.inappropriate = inappropriate;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<User> getFlaggedUsers() {
        return flaggedUsers;
    }

    public void setFlaggedUsers(Set<User> flaggedUsers) {
        this.flaggedUsers = flaggedUsers;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
