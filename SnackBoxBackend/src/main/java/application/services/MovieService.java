package application.services;


import application.model.*;
import application.model.TMDBResponses.GenresResponse;
import application.model.TMDBResponses.TMDBMovie;
import application.model.TMDBResponses.TMDBResponse;
import application.model.entities.Movie;
import application.model.requests.MovieEdit;
import application.model.responses.FlaggedMovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;


    public Movie addMovie(Movie movie){
        try{
            movie.setSource("Admin");
            movieRepository.save(movie);
        }
        catch (Exception e){
            throw e;
        }
        return movie;
    }

    public List<Movie> getAllMovies(int page) throws IndexOutOfBoundsException{
        List<Movie> movies = movieRepository.getMoviesPaginated((page-1)*10);
        if(movies.size()==0)
            throw new IndexOutOfBoundsException("You exceeded the maximum number of pages");
        return movies;
    }

    public List<Movie> getTopMovies(int page){
        List<Movie> movies = movieRepository.getTopMovies((page-1)*10);
        if(movies.size()==0)
            throw new IndexOutOfBoundsException("You exceeded the maximum number of pages");
        return movies;
    }

    public Movie getMovieById(long id) throws Exception {
        try{
            Movie m =movieRepository.findById(id).get();
            return m;
        }
        catch (Exception e){
            throw new Exception("Movie with id " + id + " does not exist");
        }
    }

    public String populate(){
        final String getGenresurI = "https://api.themoviedb.org/3/genre/movie/list?api_key=f36e1abd7b1bb7561a3768b154be7599&language=en-US";
        final String uri = "https://api.themoviedb.org/3/movie/top_rated?api_key=f36e1abd7b1bb7561a3768b154be7599&language=en-US&page=";
        RestTemplate restTemplate = new RestTemplate();
        GenresResponse genresResponse = restTemplate.getForObject(getGenresurI,GenresResponse.class);
        System.out.print(genresResponse.getGenres().get(0).getId());
        for(int i=0;i<10;i++){
            TMDBResponse tmdbResponse = restTemplate.getForObject(uri+(i+1), TMDBResponse.class);
            int page = tmdbResponse.getPage();
            for(TMDBMovie tmdbMovie : tmdbResponse.getResults()){
                Movie m = new Movie(tmdbMovie.getTitle());
                m.setDescription(tmdbMovie.getOverview());
                m.setPopularity(0);
                m.setPosterPath(tmdbMovie.getPoster_path());
                m.setReleaseDate(tmdbMovie.getRelease_date());
                List<String> genresStringList = genresResponse.getGenres().stream()
                        .filter(item -> tmdbMovie.getGenre_ids().contains(item.getId()))
                        .map(genre ->  genre.getName())
                        .collect(Collectors.toList());
                genresStringList.stream().forEach(item -> m.addGenre(item));
                m.addLanguage(tmdbMovie.getOriginal_language());
                m.setDuration((short) 152);
                m.setSource("TMDB");
                movieRepository.save(m);
        }
        }
        return "Success";
    }


    public ResponseBuilder getFlaggedMovies(){

        List<Movie> movies = movieRepository.getFlaggedMovies();

        ResponseBuilder<FlaggedMovieResponse> responseBuilder = new ResponseBuilder();
        responseBuilder.setData(movies.stream().
                map(movie -> new FlaggedMovieResponse(movie.getId(),movie.getName(),movie.getFlaggedUsers()))
                        .collect(Collectors.toList()));
        responseBuilder.setError(null);

        return responseBuilder;
    }


    public ResponseBuilder flagMovie(long movieId) throws Exception {
        try {
            movieRepository.flagMovie(movieId);
            ResponseBuilder<ResponseData> responseBuilder = new ResponseBuilder<ResponseData>();
            responseBuilder.setMessage("Success");
            responseBuilder.setError(null);
            return responseBuilder;
        }
        catch (Exception e){
            throw new Exception("Could not flag movie" + e.getMessage());
        }
    }

    public ResponseBuilder editMovie(MovieEdit movieEdit) throws Exception {
        if(movieEdit.getMovieId() == -1)
            throw new NullPointerException("movieId cannot be null");
        try{
            movieRepository.editMovie(movieEdit);
            return new ResponseBuilder(null, "Success");
        }
        catch (Exception e){
            throw new Exception("Could not edit movie " + e.getMessage());
        }
    }
}
