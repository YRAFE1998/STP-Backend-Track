package application.services;


import application.model.*;
import application.model.entities.Movie;
import application.model.entities.Rating;
import application.model.entities.RatingKey;
import application.model.entities.User;
import application.model.requests.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;


    public Optional<User> getUser(String userName){
        return userRepository.findByUsername(userName);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }



    public ResponseBuilder flagMovie(long movieId) throws Exception {

        User user = getContextUser();
        Movie m = movieRepository.findById(movieId).get();
        m.addFlag(user);

        try {
            if(m.getFlaggedUsers().size()>=2)
                m.setInappropriate(true);
            movieRepository.save(m);
            ResponseBuilder<ResponseData> responseBuilder =  new ResponseBuilder();
            responseBuilder.setError(null);
            responseBuilder.setMessage("Success");
            return responseBuilder;
        }
        catch (Exception e){
            throw new Exception("Could not flag movie " + e.getMessage());
        }
    }

    public ResponseBuilder rateMovies(RatingRequest ratingRequest) throws Exception {
        short ratingValue = ratingRequest.getRating();
        long movieId = ratingRequest.getMovieId();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Movie movie = movieService.getMovieById(movieId);
        long userId = userDetails.getUserId();
        float initialRating = movie.getRating();
        int voteCount = movie.getPopularity();
        float newRating = ((voteCount * initialRating) + ratingValue)/(voteCount + 1);
        movie.setRating(newRating);
        movie.setPopularity(movie.getPopularity() + 1);
        RatingKey ratingKey = new RatingKey(movieId,userId);
        Rating rating = new Rating(ratingKey,ratingValue);
        try {
            movieRepository.save(movie);
            ratingRepository.save(rating);
        }
        catch (Exception e){
            throw new Exception("Error while rating the movie , " + e.getMessage());
        }
        ResponseBuilder<ResponseData> responseBuilder =  new ResponseBuilder();
        responseBuilder.setError(null);
        responseBuilder.setMessage("Success");
        return responseBuilder;
    }


    public ResponseBuilder getRecommendedMovies(long movieId){
        long userId = getContextUser().getId();
        List<Movie> m = movieRepository.recommendMovie(movieId,userId);
        return new ResponseBuilder<Movie>(null,m);
    }

    User getContextUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return  userRepository.findById(userDetails.getUserId()).get();
    }

}
