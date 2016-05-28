package com.robsmovies.RobsMovies.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;

import com.robsmovies.RobsMovies.model.Movie;

import junitparams.Parameters;

import com.robsmovies.RobsMovies.data.MovieDAO;

@Stateless
@LocalBean
@Table(name = "movies")
public class UtilsDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	public void setEmUp() throws FileNotFoundException{
		deleteTable();
		File f = new File("E:\\MSc Applied Software Engineering\\Web Technologies\\Project\\Product Table.sql");
		Scanner sc = new Scanner(f);
				StringBuilder sb = new StringBuilder();
				while(sc.hasNextLine()){
					sb.append(sc.nextLine() + " ");
				}
				System.out.println(sb.toString());
		em.createNativeQuery("BEGIN " + sb.toString() + " END;").executeUpdate();
	}
	
	MovieDAO movieDAO = new MovieDAO();
	int[] ids = {1, 2, 3};
	String[] titles = {"Jaws", "Jaws 2", "Jaws 3"};
	String[] descriptions = {"A movie about a shark that goes to Hollywood to find himself", "The shark from Jaws develops a drug problem and other Hollywood stars help him to get back on his fins", "The shark from Jaws and Jaws 2 returns to fight the Nazis by way of a time travel device that only allows famous sharks past its temporal barrier"};
	String[] directors = {"Steven Spielberg", "Steven Spielberg", "Steven Spielberg"};
	String[] country = {"USA", "Panama", "Ireland"};
	int[] year = {2009, 2010, 1978};
	double[] budget = {120.0, 120000, 120000};
	double[] rentalPrice = {1.5, 2.5, 1.0};
	String[] onLoan = {"y", "n", "y"};
	String[] picture = {"C:/Users/A00226084/Pictures/disregard females.jpg", "C:/Users/A00226084/Pictures/disregard females.jpg", "C:/Users/A00226084/Pictures/disregard females.jpg"};
	
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
	@Parameters
	public Object[] getValues(){
		
		Object[] paramsForTesting = new Object[3]; 
		
		for(int i = 0; i < paramsForTesting.length; i ++){
			paramsForTesting[i] = new Object[]{ids[i], titles[i], descriptions[i], directors[i], country[i], year[i], budget[i], rentalPrice[i], onLoan[i], picture[i]};
		}
		return paramsForTesting;

	}
}
