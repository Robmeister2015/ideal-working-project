package com.robsmovies.RobsMovies.test.integration;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.sql.SQLException;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.robsmovies.RobsMovies.data.MovieDAO;
import com.robsmovies.RobsMovies.model.Movie;
import com.robsmovies.RobsMovies.rest.MovieWS;
import com.robsmovies.RobsMovies.test.utilities.UtilitiesDAO;
import com.robsmovies.RobsMovies.util.SearchMovieByParams;

		@RunWith(Arquillian.class)
		public class PersistenceTest {
			
			@Deployment
			public static Archive<?> createTestArchive() {
				JavaArchive archive = ShrinkWrap
						.create(JavaArchive.class, "Test.jar")
						.addClasses(MovieDAO.class, MovieWS.class,
								Movie.class, UtilitiesDAO.class, SearchMovieByParams.class)
						.addAsManifestResource("META-INF/persistence.xml",
								"persistence.xml")
						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
				archive.addClass(Movie.class);
				archive.addClass(MovieDAO.class);
				archive.addClass(UtilitiesDAO.class);
				archive.addClass(MovieWS.class);
				archive.addClass(SearchMovieByParams.class);
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
			private UtilitiesDAO utilsDAO;
			 
			@Before
			public void setUp() throws FileNotFoundException, SQLException, ClassNotFoundException {
				utilsDAO.deleteTable();
			}
			
			@Test
			public void testGetAllMovies() throws FileNotFoundException, ClassNotFoundException, SQLException {
				utilsDAO.deleteTable();
				utilsDAO.resetTable();
				List<Movie> movies = movieDAO.getAllMovies();
				assertEquals("Data fetch = data persisted", movies.size(), 3);
			}
			
			@Test
			public void testDeleteMovie() throws FileNotFoundException{
				utilsDAO.deleteTable();
				utilsDAO.resetTable();
				List<Movie> movies = movieDAO.getAllMovies();
				int id = movies.get(0).getId();
				movieDAO.remove(id);
				assertEquals(null, movieDAO.getMovie(id));
			}
			
			@Test
			public void testInsertMovie() throws FileNotFoundException, ClassNotFoundException, SQLException{
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
			public void getOneMovie() throws FileNotFoundException{
				utilsDAO.resetTable();
				List<Movie> movies = movieDAO.getAllMovies();
				int id = movies.get(0).getId();
				Movie movie = movieDAO.getMovie(id);
				assertEquals(id, movie.getId());
			}
}
