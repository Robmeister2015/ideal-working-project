package com.robsmovies.RobsMovies.test.unittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.robsmovies.RobsMovies.model.Movie;
import com.robsmovies.RobsMovies.rest.MovieWS;

public class MovieWSMockTests {

	MovieWS movieWs;
	List<Movie> movies = new ArrayList<Movie>();
	Movie movie1;
	Movie movie2;
	Movie movie3;
	
	@Before
	public void setUp(){
		
		movieWs = mock(MovieWS.class);
		movie1 = new Movie();
		movie1.setTitle("A test film");
		movie1.setDirector("director");
		movie1.setBudget(104.0);
		movie1.setRentalPrice(201.0);
		movie1.setDescription("description");
		movie1.setYearMade(2000);
		movie1.setOnLoan("Y");
		movie1.setCountry("country");
		movie1.setPicture("picture path");
		
		movie2 = new Movie();
		movie3 = new Movie();
	}
	
	@Test
	public void testGetAllMovies() {
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
		when(movieWs.findAll()).thenReturn(Response.status(200).entity(movies).build());
		Response r = movieWs.findAll();
		assertEquals(200, r.getStatus());
		List<Movie> receiverList = (List<Movie>) r.getEntity();
		assertEquals(3, receiverList.size());
	}
	
	@Test
	public void testGetSingleMovie(){
		when(movieWs.findMovieById("1")).thenReturn(Response.status(200).entity(movie1).build());
		Response r = movieWs.findMovieById("1");
		assertEquals(200, r.getStatus());
		Movie m = (Movie) r.getEntity();
		assertEquals("A test film", m.getTitle());
	}
	
	@Test
	public void testGetMovieWithParameter(){
		when(movieWs.findMovieByParams("A test film", "director", 104.0, 201.0, "description", "country", 2000, "Y", "picture path")).thenReturn(Response.status(200).entity(movie1).build());
		Response r = movieWs.findMovieByParams("A test film", "director", 104.0, 201.0, "description", "country", 2000, "Y", "picture path");
		assertEquals(200, r.getStatus());
		Movie m = (Movie) r.getEntity();
		assertEquals("A test film", m.getTitle());
	}

}
