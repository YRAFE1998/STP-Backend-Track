package application.controller;

import application.model.ResponseBuilder;
import application.model.entities.Movie;
import application.model.entities.User;
import application.model.requests.MovieEdit;
import application.services.MovieService;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @GetMapping("/users")
    List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/movie/add")
    Movie addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

    @PostMapping("/movie/populate")
    public String populateMovies(){
        return movieService.populate();
    }

    @GetMapping("/movie/check/flag")
    public ResponseBuilder getFlaggedMovies(){
        return movieService.getFlaggedMovies();
    }


    @PutMapping("/movie/flag")
    public ResponseBuilder flagMovie(@RequestParam long movieId) throws Exception {
        return movieService.flagMovie(movieId);
    }

    @PutMapping("/movie/edit")
    public ResponseBuilder editMovie(@RequestBody MovieEdit movieEdit) throws Exception {
        return movieService.editMovie(movieEdit);
    }
}
