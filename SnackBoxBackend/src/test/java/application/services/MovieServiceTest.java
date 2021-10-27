package application.services;

import application.model.MovieRepository;
import application.model.ResponseBuilder;
import application.model.entities.Movie;
import application.model.requests.MovieEdit;
import net.bytebuddy.dynamic.DynamicType;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie m1;
    private Movie m2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        m1 = new Movie("Yousef's Movie", LocalDate.of(2021,10,25), 8, "posterPath", "trailerPath", "Description", 1, (short) 154);
        m2 = new Movie("2nd Movie", LocalDate.of(2021,9,25), 2, "posterPath", "trailerPath", "Description", 1, (short) 154);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void willSetSourceToAdminAndAddMovie() {
        movieService.addMovie(m1);
        ArgumentCaptor<Movie> argument = ArgumentCaptor.forClass(Movie.class);
        verify(movieRepository).save(argument.capture());
        assertEquals("Yousef's Movie", argument.getValue().getName());
        assertEquals("Admin", argument.getValue().getSource());
    }

    @Test
    void willReturnRightMovieAddMovie(){
        Movie m = movieService.addMovie(m1);
        assertEquals(m1,m);
    }


    @Test
    void willReturnRightListGetAllMovies() {
        when(movieRepository.getMoviesPaginated(anyInt())).thenReturn(Arrays.asList(m1));
        List<Movie> movies = movieService.getAllMovies(1);
        assertThat(movies,Is.is(Arrays.asList(m1)));
    }

    @Test
    void willCallGetMoviesPaginatedWithRightParametersGetAllMovies(){
        when(movieRepository.getMoviesPaginated(anyInt())).thenReturn(Arrays.asList(m1,m2));
        movieService.getAllMovies(1);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(movieRepository).getMoviesPaginated(argument.capture());
        assertEquals(0,(int) argument.getValue());
    }

    @Test
    void willCallGetMoviesPaginatedWithRightParametersGetAllMovies2(){
        when(movieRepository.getMoviesPaginated(anyInt())).thenReturn(Arrays.asList(m1,m2));
        movieService.getAllMovies(2);
        ArgumentCaptor<Integer> argument1 = ArgumentCaptor.forClass(Integer.class);
        verify(movieRepository).getMoviesPaginated(argument1.capture());
        assertEquals(2,(int) argument1.getValue());
    }

    @Test
    void willThrowExceptionGetAllMovies(){
        assertThrows(IndexOutOfBoundsException.class, () ->{
            movieService.getAllMovies(1);
        });
    }


    @Test
    void willReturnRightListGetTopMovies() {
        when(movieRepository.getTopMovies(anyInt())).thenReturn(Arrays.asList(m1));
        List<Movie> movies = movieService.getTopMovies(1);
        assertThat(movies,Is.is(Arrays.asList(m1)));
    }

    @Test
    void willCallGetTopMoviesWithRightParametersGetTopMovies(){
        when(movieRepository.getTopMovies(anyInt())).thenReturn(Arrays.asList(m1,m2));
        movieService.getTopMovies(1);
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(movieRepository).getTopMovies(argument.capture());
        assertEquals(0,(int) argument.getValue());
    }

    @Test
    void willCallGetTopMoviesWithRightParametersGetTopMovies2(){
        when(movieRepository.getTopMovies(anyInt())).thenReturn(Arrays.asList(m1,m2));
        movieService.getTopMovies(2);
        ArgumentCaptor<Integer> argument1 = ArgumentCaptor.forClass(Integer.class);
        verify(movieRepository).getTopMovies(argument1.capture());
        assertEquals(2,(int) argument1.getValue());
    }

    @Test
    void willThrowExceptionGetTopMovies(){
        assertThrows(IndexOutOfBoundsException.class, () ->{
            movieService.getTopMovies(1);
        });
    }


    @Test
    void CallsFindByIdGetMovieById() throws Exception {
        when(movieRepository.findById(anyLong())).thenReturn(java.util.Optional.of(m1));
        Movie movie = movieService.getMovieById((long)1);
        assertThat(movie,Is.is(m1));
    }

    @Disabled
    @Test
    void populate() {
    }

    @Test
    void CallsGetFlaggedMoviesgetFlaggedMovies() {
        when(movieRepository.getFlaggedMovies()).thenReturn(Arrays.asList(m1));
        ResponseBuilder<Movie> moviesResponseBuilder = movieService.getFlaggedMovies();
        assertThat(moviesResponseBuilder.getData(),Is.is(Arrays.asList(m1)));

    }

    @Test
    void WillFlagMovie() throws Exception {
        movieService.flagMovie(m1.getId());
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(movieRepository).flagMovie(argument.capture());
        assertEquals(java.util.Optional.of(m1.getId()), java.util.Optional.of(argument.getValue()));
    }

    @Test
    void editMovie() throws Exception {
        MovieEdit movieEdit = new MovieEdit();
        movieEdit.setMovieId(m1.getId());
        movieEdit.setGenres(Arrays.asList("Drama"));
        movieService.editMovie(movieEdit);
        ArgumentCaptor<MovieEdit> argument = ArgumentCaptor.forClass(MovieEdit.class);
        verify(movieRepository).editMovie(argument.capture());
        assertEquals(movieEdit, argument.getValue());

    }
}