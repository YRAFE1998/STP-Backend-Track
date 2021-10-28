package application.model;

import application.model.entities.Movie;
import application.model.entities.Rating;
import application.model.entities.User;
import application.model.requests.MovieEdit;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    private Movie m1;
    private Movie m2;
    private Movie m3;


    @BeforeEach
    void setUp() {
        m1 = new Movie("Yousef's Movie", LocalDate.of(2021,10,25), 8, "posterPath", "trailerPath", "Description", 1, (short) 154);
        m2 = new Movie("Yousef's 2nd Movie", LocalDate.of(2021,10,26), 10, "posterPath", "trailerPath", "Description", 5, (short) 158);
        m3 = new Movie("Yousef's 3rd Movie", LocalDate.of(2021,9,15), 9, "posterPath", "trailerPath", "Description", 0, (short) 158);

        movieRepository.save(m1);
        movieRepository.save(m2);
        movieRepository.save(m3);
    }

    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void shouldBeOfSizeRightGetMoviesPaginated() {
        //when
        List<Movie> moviesOfSize2 = movieRepository.getMoviesPaginated(1);
        List<Movie> moviesOfSize1 = movieRepository.getMoviesPaginated(2);
        List<Movie> moviesOfSize0 = movieRepository.getMoviesPaginated(3);

        //then
        assertAll(
                () -> assertEquals(2, moviesOfSize2.size()),
                () -> assertEquals(1, moviesOfSize1.size()),
                () -> assertEquals(0, moviesOfSize0.size())
                );
    }

    @Test
    void shouldReturnRightMoviesGetMoviesPaginated(){
        List<Movie> movies = movieRepository.getMoviesPaginated(0);
        assertThat(movies, Is.is(Arrays.asList(m1,m2,m3)));
    }

    @Test
    void shouldReturnRightMoviesgetTopMovies() {
        //given
        m1.setRating(8);
        m3.setRating(10);
        movieRepository.save(m1);
        movieRepository.save(m3);

        List<Movie> movies = movieRepository.getTopMovies(0);
        assertThat(movies, Is.is(Arrays.asList(m3,m2,m1)));
    }

    @Disabled
    @Test
    void recommendMovie() {
        User u1 = new User("firstName", "lastName", "userName", "email", "password",
                Arrays.asList("ROLE_USER"));
        u1.setId(1);
        userRepository.save(u1);

        Set<Rating> ratings = new HashSet<Rating>();
        Rating r = new Rating();
        r.setMovie(m1);
        r.setUser(u1);
        r.setRating((short)5);
        ratings.add(r);
        m1.setRatings(ratings);
        m1.addGenre("Comedy");
        m3.addGenre("Comedy");
        movieRepository.save(m1);
        movieRepository.save(m3);

        List<Movie> allMovies = movieRepository.findAll();

        for(Movie m :allMovies){
            if(m.getGenres().size()>0) {
                System.out.println(m.getName() + " " + m.getRating() + " " + m.getGenres().get(0));
            }
            else
                System.out.println(m.getName() + " " + m.getRating());
        }
        List<Movie> recommendedMovies = movieRepository.recommendMovie(m1.getId(),u1.getId());

        for(Movie m :recommendedMovies){
                System.out.println(m.getName());
        }

        assertThat(recommendedMovies,Is.is(Arrays.asList(m3)));
    }

    @Test
    void ShouldReturnFlaggedMoviesGetFlaggedMovies() {
        User u1 = new User("firstName", "lastName", "userName", "email", "password",
                Arrays.asList("ROLE_USER"));
        m1.addFlag(u1);

        movieRepository.save(m1);

        List<Movie> flaggedMovies = movieRepository.getFlaggedMovies();
        assertThat(flaggedMovies,Is.is(Arrays.asList(m1)));
    }

    @Test
    void ShouldReturnEmptyListGetFlaggedMovies(){
        List<Movie> flaggedMovies = movieRepository.getFlaggedMovies();
        assertThat(flaggedMovies,Is.is(new ArrayList<Movie>()));

    }


    @Test
    @Modifying
    @Commit
    void ShouldFlagMovie() {
        movieRepository.flagMovie(m1.getId());
        Movie flaggedMovie = movieRepository.findById(m1.getId()).get();
        System.out.println(flaggedMovie.getName() + " " + flaggedMovie.isInappropriate());
        assertTrue(flaggedMovie.isInappropriate());

    }

    @Rollback
    @Commit
    @Test
    void ShouldEditMovie() throws Exception {
        MovieEdit movieEdit = new MovieEdit();
        movieEdit.setMovieId(m1.getId());
        List<String> languages = Arrays.asList("Hi");
        LocalDate date = LocalDate.of(2021,10,27);
        List<String> genres = Arrays.asList("genre1");
        movieEdit.setLanguages(Arrays.asList("Hi"));
        movieEdit.setReleaseDate(LocalDate.of(2021,10,27));
        movieEdit.setGenres(Arrays.asList("genre1"));
        movieRepository.editMovie(movieEdit);
        Movie movie = movieRepository.findById(m1.getId()).get();
        assertAll(
                () -> assertThat(movie.getLanguages(),Is.is(languages)),
                () -> assertThat(movie.getGenres(),Is.is(genres)),
                () -> assertThat(movie.getReleaseDate(),Is.is(date))
                );

    }
}