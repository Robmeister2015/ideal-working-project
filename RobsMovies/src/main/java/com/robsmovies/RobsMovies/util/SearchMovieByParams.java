package com.robsmovies.RobsMovies.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.robsmovies.RobsMovies.data.MovieDAO;
import com.robsmovies.RobsMovies.model.Movie;

@Stateless
@LocalBean
public class SearchMovieByParams {

	@EJB
	private MovieDAO movieDao;

	public List<Movie> searchMovie(final Object[] parameters) {

		final List<Movie> movies = movieDao.getAllMovies();
		final List<Movie> moviesToReturn = new ArrayList<Movie>();

		for (final Movie m : movies) {
			if (parameters[0] != null && !m.getTitle().equals(parameters[0].toString())) {
				continue;
			}
			if (parameters[1] != null && !m.getDescription().equals(parameters[1].toString())) {
				continue;
			}
			if (parameters[2] != null && !m.getDirector().equals(parameters[2].toString())) {
				continue;
			}
			if (parameters[3] != null && !m.getCountry().equals(parameters[3].toString())) {
				continue;
			}
			if (parameters[4] != null && m.getYearMade() != Integer.parseInt(parameters[4].toString())) {
				continue;
			}
			if (parameters[5] != null && m.getBudget() != Double.parseDouble(parameters[5].toString())) {
				continue;
			}
			if (parameters[6] != null && m.getRentalPrice() != Double.parseDouble(parameters[6].toString())) {
				continue;
			}
			if (parameters[7] != null && !m.getOnLoan().equals(parameters[7].toString())) {
				continue;
			}
			if (parameters[8] != null && !m.getPicture().equals(parameters[8].toString())) {
				continue;
			}

			moviesToReturn.add(m);

		}

		return moviesToReturn;
	}
}
