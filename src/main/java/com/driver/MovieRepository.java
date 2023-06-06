package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieRepository {
    Map<String, Movie> movieDB = new HashMap<>();
    Map<String, Director> DirectorDB = new HashMap<>();
    Map<String, List<String>> MovieDirectorPairDB = new HashMap<>();
    public String addMovie(Movie movie) {
        movieDB.put(movie.getName(), movie);
        return "SUCCESS";
    }

    public String addDirector(Director director) {
        DirectorDB.put(director.getName(), director);
        return "SUCCESS";
    }

    public String addMovieDirectorPair(String movie, String director) {
        if(MovieDirectorPairDB.containsKey(director)) {
            List<String> list = MovieDirectorPairDB.get(director);
            list.add(movie);
        } else {
            List<String> list = new ArrayList<>();
            list.add(movie);
            MovieDirectorPairDB.put(director, list);
        }
        return "SUCCESS";
    }

    public Movie getMovieByName(String name) {
        return movieDB.get(name);
    }

    public Director getDirectorByName(String name) {
        return DirectorDB.get(name);
    }

    public List<String> getMoviesByDirectorName(String name) {
        return MovieDirectorPairDB.get(name);
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieDB.keySet());
    }

    public String deleteDirectorByName(String name) {
        List<String> movies = MovieDirectorPairDB.get(name);
        for(String movie : movies) {
            movieDB.remove(movie);
        }
        MovieDirectorPairDB.remove(name);
        DirectorDB.remove(name);
        return "Deleted Succesfully";
    }

    public String deleteAllDirectors() {
        for(String director : MovieDirectorPairDB.keySet()) {
            List<String> movie = MovieDirectorPairDB.get(director);
            for(String movies : movie) {
                movieDB.remove(movies);
            }
        }
        MovieDirectorPairDB.clear();
        DirectorDB.clear();
        return "Deleted Succesfullly";
    }
}
