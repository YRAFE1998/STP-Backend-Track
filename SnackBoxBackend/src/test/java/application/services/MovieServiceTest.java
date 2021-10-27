package application.services;

import application.model.MovieRepository;
import application.model.entities.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    private AutoCloseable autoCloseable;
    private MovieService movieService;
    private Movie m1;


    @BeforeEach
    void setUp() {
        //autoCloseable =  MockitoAnnotations.initMocks(this);
        movieService = new MovieService();
        movieService.movieRepository = movieRepository;
        m1 = new Movie("Yousef's Movie", LocalDate.of(2021,10,25), 8, "posterPath", "trailerPath", "Description", 1, (short) 154);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void willSetSourceToAdminAddMovie() {
        Movie movie = movieService.addMovie(m1);
        //verify()
        verify(movieRepository).save(m1);
        //assertEquals(movie.getSource(),"Admin");

    }

    @Test
    void getAllMovies() {
    }

    @Test
    void getTopMovies() {
    }

    @Test
    void getMovieById() {
    }

    @Test
    void populate() {
    }

    @Test
    void getFlaggedMovies() {
    }

    @Test
    void flagMovie() {
    }

    @Test
    void editMovie() {
    }
}