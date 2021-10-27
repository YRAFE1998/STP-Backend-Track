package application.model;

import application.model.entities.Movie;
import application.model.requests.MovieEdit;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM movies LIMIT :page,2")
    List<Movie> getMoviesPaginated(@Param("page") int page);

    @Query(nativeQuery = true,value = "SELECT * FROM movies ORDER BY rating DESC LIMIT :page,2")
    List<Movie> getTopMovies(@Param("page") int page);


    @Query(nativeQuery = true, value = "SELECT DISTINCT * FROM movies LEFT JOIN movie_genres\n" +
            "ON movies.id = movie_genres.movie_id\n" +
            "WHERE movie_genres.genres =  ANY\n" +
            "\t(SELECT genres \n" +
            "\tFROM movie_genres LEFT JOIN users_rate_movies\n" +
            "\tON users_rate_movies.movie_id = movie_genres.movie_id\n" +
            "\tWHERE movie_genres.movie_id = ?1 OR users_rate_movies.user_id = ?2)  \n" +
            "AND NOT movies.id = ANY\n" +
            "\t(SELECT movie_id\n" +
            "    FROM users_rate_movies\n" +
            "    WHERE user_id = ?2\n" +
            "    )\n" +
            "ORDER BY rating DESC")
    List<Movie> recommendMovie(long movieId, long userId);

    @Query("SELECT m FROM Movie m WHERE m.flaggedUsers is NOT EMPTY")
    List<Movie> getFlaggedMovies();


    @Modifying
    @Transactional
    default void flagMovie(long movieId){
        Movie m = this.findById(movieId).get();
        m.setInappropriate(true);
        this.save(m);
    }

    @Modifying
    @Transactional
    default void editMovie(MovieEdit movieEdit) throws Exception {
        Movie m = this.findById(movieEdit.getMovieId()).get();
        System.out.println("Movie Name" + " " + m.getName());
        if(m == null)
            throw new Exception("Movie not found");
        else{
            //m.setGenres(movieEdit.getGenres());
            m.setLanguages(new ArrayList<>(movieEdit.getLanguages()));
            m.setGenres(new ArrayList<>(movieEdit.getGenres()));
            m.setReleaseDate(movieEdit.getReleaseDate());
            save(m);
        }
    }

}
