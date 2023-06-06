package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String movieAdded =  movieService.addMovie(movie);

        return new ResponseEntity<>(movieAdded, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        String directorAdded = movieService.addDirector(director);
        return new ResponseEntity<>(directorAdded, HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director) {
        String movieDirectorPairAdded = movieService.addMovieDirectorPair(movie, director);
        return new ResponseEntity<>(movieDirectorPairAdded, HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name) {
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
    //Get Director by director name: GET /movies/get-director-by-name/{name}
    //Pass director name as path parameter
    //Return Director object wrapped in a ResponseEntity object
    //Controller Name - getDirectorByName

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name) {
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }

    //Get List of movies name for a given director name: GET /movies/get-movies-by-director-name/{director}
    //Pass director name as path parameter
    //Return List of movies name(List()) wrapped in a ResponseEntity object
    //Controller Name - getMoviesByDirectorName

    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("name") String name) {
        List<String> list = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    //Get List of all movies added: GET /movies/get-all-movies
    //No params or body required
    //Return List of movies name(List()) wrapped in a ResponseEntity object
    //Controller Name - findAllMovies

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> list = movieService.findAllMovies();
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    //Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    //Pass director’s name as request parameter
    //Return success message wrapped in a ResponseEntity object
    //Controller Name - deleteDirectorByName
    //
    //Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    //No params or body required
    //Return success message wrapped in a ResponseEntity object
    //Controller Name - deleteAllDirectors
    //(Note that there can be some movies on your watchlist that aren’t mapped
    // to any of the director. Make sure you do not remove them.)

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String name) {
        String msg = movieService.deleteDirectorByName(name);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        String msg = movieService.deleteAllDirectors();
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}
