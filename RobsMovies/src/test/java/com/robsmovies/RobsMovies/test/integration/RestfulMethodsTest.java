package com.robsmovies.RobsMovies.test.integration;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.robsmovies.RobsMovies.data.MovieDAO;
import com.robsmovies.RobsMovies.model.Movie;
import com.robsmovies.RobsMovies.rest.MovieWS;
import com.robsmovies.RobsMovies.test.utilities.UtilitiesDAO;


@RunWith(Arquillian.class) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestfulMethodsTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		JavaArchive archive = ShrinkWrap
				.create(JavaArchive.class, "Test.jar")
				.addClasses(MovieDAO.class, MovieWS.class,
						Movie.class, UtilitiesDAO.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		archive.addClass(Movie.class);
		archive.addClass(MovieDAO.class);
		archive.addClass(MovieWS.class);
		return archive;
		
	}
	
	Movie movie;
	Movie movie2;
	
	@PersistenceContext
    private EntityManager em;
	
	@EJB
	MovieWS movieWS;
	
	@EJB
	MovieDAO movieDAO;
	
	@EJB
	UtilitiesDAO utilsDAO;

	@Before
	public void setUp(){
		movie = new Movie();
		movie.setTitle("TestMovie");
		movie.setDescription("A Test Movie");
		movie.setDirector("A Test Director");
		movie.setCountry("A Test Country");
		movie.setBudget(100);
		movie.setOnLoan("y");
		movie.setBudget(120);
		movie.setYearMade(1900);
		movie.setRentalPrice(12);
		movie.setPicture("test string");
		
		movie2 = new Movie();
		movie2.setTitle("TestMovie2");
		movie2.setDescription("A Test Movie2");
		movie2.setDirector("A Test Director2");
		movie2.setCountry("A Test Country2");
		movie2.setBudget(1002);
		movie2.setOnLoan("n");
		movie2.setBudget(1202);
		movie2.setYearMade(1901);
		movie2.setRentalPrice(122);
		movie2.setPicture("test string2");
	}
	
	@Test
	public void AsaveMovieTest() {
		List<Movie> movies = movieDAO.getAllMovies();
		assertEquals(0, movies.size());
		movieWS.saveMovie(movie);
		movies = movieDAO.getAllMovies();
		assertEquals(1, movies.size());
		assertEquals("TestMovie", movies.get(0).getTitle());
	}
	
	@Test
	public void BdeleteMovieTest(){
		utilsDAO.deleteTable();
		List<Movie> movies = movieDAO.getAllMovies();
		assertEquals(0, movies.size());
		movieWS.saveMovie(movie);
		movies = movieDAO.getAllMovies();
		assertEquals(1, movies.size());
		movieWS.remove(movie, 1);
		movies = movieDAO.getAllMovies();
		assertEquals(0, movies.size());
	}
	
	@Test
	public void CupdateMovieTest(){
		utilsDAO.deleteTable();
		List<Movie> movies = movieDAO.getAllMovies();
		assertEquals(0, movies.size());
		movieWS.saveMovie(movie);
		movies = movieDAO.getAllMovies();
		assertEquals(1, movies.size());
		Movie m = movies.get(0);
		int id = m.getId();
		assertEquals("TestMovie", m.getTitle());
		assertEquals("A Test Movie", m.getDescription());
		assertEquals("A Test Director", m.getDirector());
		assertEquals("A Test Country", m.getCountry());
		assertEquals(120, m.getBudget(), 0.001);
		assertEquals(12, m.getRentalPrice(), 0.001);
		assertEquals(1900, m.getYearMade());
		assertEquals("y", m.getOnLoan());
		assertEquals("test string", m.getPicture());
		movieWS.update(movie2, id);
		movies = movieDAO.getAllMovies();
		m = movies.get(0);
		assertEquals("TestMovie2", m.getTitle());
		assertEquals("A Test Movie2", m.getDescription());
		assertEquals("A Test Director2", m.getDirector());
		assertEquals("A Test Country2", m.getCountry());
		assertEquals(1202, m.getBudget(), 0.001);
		assertEquals(122, m.getRentalPrice(), 0.001);
		assertEquals(1901, m.getYearMade());
		assertEquals("n", m.getOnLoan());
		assertEquals("test string2", m.getPicture());
	}

}
