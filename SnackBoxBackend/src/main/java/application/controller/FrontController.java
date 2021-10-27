package application.controller;

import application.model.entities.Movie;
import application.model.entities.User;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import application.services.MovieService;

import java.util.Optional;

@RestController
public class FrontController {

    @Autowired MovieService movieService;
    @Autowired UserService userService;

    @GetMapping("/")
    String hello(){
        return "<h1> Hello</h1>" +
                "<h2> Here are some useful routes </h2>" +
                "<p>login : Sign in using username and password to route POST /auth/signin </p>" +
                "<p>As an admin you can GET a list of all users from /admin/users</p>" +
                "<p>As an admin you can add a movie through POST /admin/movie/add</p>" +
                "<p>As an admin you can populate 200 movies through POST /admin/movie/populate</p>, EVERYTIME AN ADMIN POPULATES, AN EXTRA 200 MOVIES ARE ADDED TO THE DATABASE" +
                "<p>As a user you can view a list of all movies paginated through GET /movie/getMovies and, 'page' value to query parameters</p>" +
                "<p>As a user you can view a list of top movies paginated through GET /movie/getTopMovies, add 'page' value to query parameters</p>" +
                "<p>As a user you can view movie details through GET /movie/movieDetails, add 'id' value to query parameters</p>"+
                "<p>As a user you can rate a movie through POST /user/movie/rate, add {'rating':X,'movieId':Y} value to Request Body</p>" +
                "<p>As a user you can flag a movie as inappropriate through POST /user/movie/flag, add {'movieId':Y} value to Request Body</p>"+
                "<h5>Good Luck</h5>"
                ;
    }




}
