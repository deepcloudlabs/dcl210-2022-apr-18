package com.example.exercises;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Collection;
import java.util.List;

import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
public class Exercise1 {
	private static final MovieService movieService = InMemoryMovieService.getInstance();

	public static void main(String[] args) {
		// Find the number of movies of each director
        final Collection<Movie> movies = movieService.findAllMovies();
        var directorMovieCounts =
        movies.stream()    // Stream<Movie>
//              .map(movie -> movie.getDirectors())
              .map(Movie::getDirectors)  // Stream<List<Director>>
              .flatMap(List::stream)     // Stream<Director>
              .collect(groupingBy(identity(),counting()));
        directorMovieCounts.forEach(
        	(director,count) -> System.out.println("%-24s: %3d".formatted(director.getName(),count))	
        );
	}

}
