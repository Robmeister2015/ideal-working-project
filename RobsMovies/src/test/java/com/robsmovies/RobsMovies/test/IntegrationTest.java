package com.robsmovies.RobsMovies.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.robsmovies.RobsMovies.data.MovieDAO;
import com.robsmovies.RobsMovies.model.Movie;
import com.robsmovies.RobsMovies.rest.MovieWS;

		@RunWith(Arquillian.class)
		public class IntegrationTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				JavaArchive archive = ShrinkWrap
						.create(JavaArchive.class, "Test.jar")
						.addClasses(MovieDAO.class, MovieWS.class,
								Movie.class, UtilsDAO.class)
						.addAsManifestResource("META-INF/persistence.xml",
								"persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
				archive.addClass(UtilsDAO.class);
				archive.addClass(Movie.class);
				archive.addClass(UtilsDAO.class);
				archive.addClass(Movie.class);
				return archive;

			}
			
			List<Movie> allMovies;
			 
			@PersistenceContext
		    private EntityManager em;
			
			@EJB
			private MovieWS MovieWS;
			
			@EJB
			private MovieDAO movieDAO;
			
			@EJB
			private UtilsDAO utilsDAO;
			 
			@Before
			public void setUp() throws FileNotFoundException {
				allMovies = new ArrayList<Movie>();
				allMovies = movieDAO.getAllMovies();
				
				utilsDAO.deleteTable();
				utilsDAO.setEmUp();
			}
			
			@Test
			public void testGetAllMovies() throws FileNotFoundException {
//				utilsDAO.deleteTable();
//				utilsDAO.resetTable();
				List<Movie> movies = movieDAO.getAllMovies();
				assertEquals("Data fetch = data persisted", movies.size(), 3);
			}
			
			@Test
			public void testDeleteMovie(){
				
				List<Movie> movies = movieDAO.getAllMovies();
				int id = movies.get(0).getId();
				movieDAO.remove(id);
				assertEquals(null, movieDAO.getMovie(id));
			}
			
			@Test
			public void testInsertMovie(){
				utilsDAO.deleteTable();
				List<Movie> movies = movieDAO.getAllMovies();
				assertEquals(0, movies.size());
				Movie movie = new Movie();
				movie.setBudget(200);
				movie.setCountry("Ireland");
				movie.setDescription("A description");
				movie.setDirector("Steven Spielberg");
				movie.setOnLoan("y");
				movie.setPicture("C:/apicture.jpeg");
				movie.setRentalPrice(12.50);
				movie.setTitle("A movie about stuff");
				movie.setYearMade(2010);
				movieDAO.save(movie);
				movies.clear();
				movies = movieDAO.getAllMovies();
				assertEquals(1, movies.size());
			}
			
			@Test
			public void getOneMovie(){
				List<Movie> movies = movieDAO.getAllMovies();
				int id = movies.get(0).getId();
				Movie movie = movieDAO.getMovie(id);
				assertEquals(id, movie.getId());
			}
			
			@After
			public void tearDown() throws FileNotFoundException{	
				utilsDAO.resetTable();
			}
			
			
			
}
