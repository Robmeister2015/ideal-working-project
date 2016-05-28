package com.robsmovies.RobsMovies.test;

import java.io.FileNotFoundException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.robsmovies.RobsMovies.model.Movie;
import com.robsmovies.RobsMovies.data.MovieDAO;

@Stateless
@LocalBean
public class UtilsDAO {

	@PersistenceContext
	private EntityManager em;
	
	MovieDAO movieDAO = new MovieDAO();
	String[] titles = {"Jaws", "Jaws 2"};
	String[] descriptions = {"A movie about a shark that goes to Hollywood to find himself", "The shark from Jaws develops a drug problem and other Hollywood stars help him to get back on his fins"};
	String[] directors = {"Steven Spielberg", "Steven Spielberg"};
	String[] country = {"USA", "Panama"};
	int[] year = {2009, 2010};
	double[] budget = {120.0, 120000};
	double[] rentalPrice = {1.5, 2.5};
	String[] onLoan = {"y", "n"};
	String[] picture = {"C:/Users/A00226084/Pictures/disregard females.jpg", "C:/Users/A00226084/Pictures/disregard females.jpg"};
	
	public void deleteTable() {
		em.createQuery("DELETE FROM Movie").executeUpdate();
	}

	public void resetTable() throws FileNotFoundException {
		for(int i = 0; i < titles.length; i ++){
			Movie movie=new Movie();
			movie.setTitle(titles[i]);
			movie.setDescription(descriptions[i]);
			movie.setDirector(directors[i]);
			movie.setCountry(country[i]);
			movie.setYearMade(year[i]);
			movie.setBudget(budget[i]);
			movie.setRentalPrice(rentalPrice[i]);
			movie.setOnLoan(onLoan[i]);
			movie.setPicture(picture[i]);
			em.persist(movie);
		}

	}
}
