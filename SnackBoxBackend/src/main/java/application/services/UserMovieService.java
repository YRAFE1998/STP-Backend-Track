package application.services;

import application.model.*;
import application.model.entities.Rating;
import application.model.entities.RatingKey;
import application.model.requests.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserMovieService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    RatingRepository ratingRepository;


}
