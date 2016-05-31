package com.robsmovies.RobsMovies.data;

import java.io.File;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.robsmovies.RobsMovies.model.Movie;

@Stateless
@LocalBean
public class MovieDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * Method to get all movies from the database
	 */

	public List<Movie> getAllMovies() {
		final Query QUERY = entityManager.createQuery("SELECT m FROM Movie m");
		return QUERY.getResultList();
	}

	/*
	 * Method that fetches a movie by an ID appended to the URL
	 */

	public Movie getMovie(final int movieIdentifier) {
		return entityManager.find(Movie.class, movieIdentifier);
	}

	/*
	 * Method that saves a movie to the database
	 */

	public void save(final Movie movie) {
		entityManager.persist(movie);
	}

	/*
	 * This method updates an entry in the database with the given details
	 */

	public void update(final Movie movie) {
		entityManager.merge(movie);
	}

	/*
	 * This method removes a movie from the database
	 */

	public void remove(final int identifier) {
		final Movie movie = entityManager.find(Movie.class, identifier);
		entityManager.remove(movie);
	}

	public void savePicture(String picLocation, final int identifier) {
		System.out.println(picLocation);
		picLocation = picLocation.replace('\\', '/');
		final File source = new File(picLocation);
	//	File dest = new File("C:/Users/A00226084/" + identifier + ".jpg");
		System.out.println("File: " + source.exists());
//		try {
//			FileUtils.copyFileToDirectory(source, dest);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
