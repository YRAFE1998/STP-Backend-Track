package application.controller;

import application.model.ResponseBuilder;
import application.model.requests.FlagRequest;
import application.model.requests.RatingRequest;
import application.services.MovieService;
import application.services.UserMovieService;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MovieService movieService;

    @Autowired
    UserMovieService userMovieService;

    @PostMapping("/user/movie/flag")
    public String flagMovie(@RequestBody FlagRequest flagRequest) throws Exception {
        long movieId = flagRequest.getMovieId();
        userService.flagMovie(movieId);
        return "Success";
    }

    @PutMapping("/user/movie/rate")
    public ResponseBuilder rateMovies(@RequestBody RatingRequest ratingRequest) throws Exception{
        return(userService.rateMovies(ratingRequest));
    }

    @GetMapping("/user/movie/recommend")
    public ResponseBuilder recommendMovies(@RequestParam long movieId){
        return userService.getRecommendedMovies(movieId);
    }


}
