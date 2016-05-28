package com.robsmovies.RobsMovies.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.robsmovies.RobsMovies.model.Movie;

@Stateless
@LocalBean
public class MovieDAO {

	@PersistenceContext
	private EntityManager em;

	/*
	 * Method to get all movies from the database
	 */

	public List<Movie> getAllMovies() {
		Query query = em.createQuery("SELECT m FROM Movie m");
		return query.getResultList();
	}

	/*
	 * Method that fetches a movie by an ID appended to the URL
	 */

	public Movie getMovie(int id) {
		return em.find(Movie.class, id);
	}

	/*
	 * Method that saves a movie to the database
	 */

	public void save(Movie movie) {
		em.persist(movie);
	}

	/*
	 * This method updates an entry in the database with the given details
	 */

	public void update(Movie movie) {
		em.merge(movie);
	}

	/*
	 * This method removes a movie from the database
	 */

	public void remove(int id) {
		Movie m = em.find(Movie.class, id);
		em.remove(m);
	}

	public void savePicture(String picLocation, int id) {
		System.out.println(picLocation);
		picLocation = picLocation.replace('\\', '/');
		File source = new File(picLocation);
		File dest = new File("C:/Users/A00226084/" + id + ".jpg");
		System.out.println("File: " + source.exists());
	//	try {
	//		FileUtils.copyFileToDirectory(source, dest);
	//	} catch (IOException e) {
	//		e.printStackTrace();
	//	}
	}

	/*
	 * Uses a Hashtable with key/value pairs to insert column names and data
	 * into the query
	 */

	public List<Movie> getMovieBasedOnUnknownNumberOfCriteria(Hashtable<String, Object> columnsAndValues) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		List<Predicate> predicates = new ArrayList<Predicate>();

		Enumeration<?> columnKeys = columnsAndValues.keys();
		Root<Movie> movie = criteriaQuery.from(Movie.class);

		while (columnKeys.hasMoreElements()) {
			String dataForColumn = (String) columnKeys.nextElement();
			predicates.add(criteriaBuilder.equal(movie.get(dataForColumn), columnsAndValues.get(dataForColumn)));
		}
		criteriaQuery.multiselect(movie).where(predicates.toArray(new Predicate[] {}));

		em.createQuery(criteriaQuery).getResultList();

		List<Object> returnedMoviesAsObjects = em.createQuery(criteriaQuery).getResultList();
		List<Movie> movies = new ArrayList<Movie>();
		for (Object o : returnedMoviesAsObjects) {
			Movie m = (Movie) o;
			movies.add(m);
		}
		return movies;
	}
}
