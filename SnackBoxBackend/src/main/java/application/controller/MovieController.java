package application.controller;

import application.model.entities.Movie;
import application.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class MovieController {
    @Autowired
    MovieService movieService;


    @GetMapping("/movie/getMovies")
    public List<Movie> getAllMovies(@RequestParam(required = false) Integer page){
        int p = page !=null?page:1;
        return movieService.getAllMovies(p);
    }


    @GetMapping("/movie/getTopMovies")
    public List<Movie> getTopMovies(@RequestParam(required = false) Integer page){
        int p = page !=null?page:1;
        return movieService.getTopMovies(p);
    }

    @GetMapping("/movie/movieDetails")
    public Movie getMovieDetails(@RequestParam long id) throws Exception{
        return movieService.getMovieById(id);
    }


}
