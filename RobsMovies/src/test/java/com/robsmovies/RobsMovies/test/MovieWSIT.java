//package com.robsmovies.RobsMovies.test;
//
//import static org.junit.Assert.*;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.sql.SQLException;
//
//import javax.ejb.EJB;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//
//@RunWith(JUnitParamsRunner.class)
//public class MovieWSIT extends NonIntegrationUtils{
//
//	final String GET_ALL_ENTITIES = "http://localhost:8180/RobsMovies/rest/movies";
//	final String GET_SINGLE_ENTITY = "http://localhost:8180/RobsMovies/rest/movies/1";
//	final String SEARCH_PARAMETER_URL = "http://localhost:8180/RobsMovies/rest/movies/search?";
//	final String POST_ENTITY = "";
//	final String PUT_ENTITY = "";
//	
//	final String GET_REQUEST = "GET";
//	final String POST_REQUEST = "POST";
//	final String PUT_REQUEST = "PUT";
//	final String DELETE_REQUEST = "DELETE";
//
//	final String USER_AGENT = "User Agent";
//	
//	URL obj;
//	HttpURLConnection con;
//	JSONArray jsonArr;
//	JSONObject json;
//	
//	Object[] testingValues;
//	NonIntegrationUtils nonInt;
//	
//	@Before
//	public void setUp() throws FileNotFoundException, SQLException{
//		nonInt = new NonIntegrationUtils();
//		nonInt.insertData();
//	}
//	
//	@Test
//	@Parameters(method="filmParams")
//	public void testGetRequestToGetAllEntities(int getId, int id, String title, String description, String director,
//			String country, int year, double budget, double rentalPrice, String onLoan, String picture) throws IOException, JSONException {
//		
//		obj = new URL(GET_ALL_ENTITIES);
//		con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod(GET_REQUEST);
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//		
//		assertEquals(200, con.getResponseCode());
//		json = new JSONObject("{\"movies\":" + response.toString() + "}");
//		jsonArr = json.getJSONArray("movies");
//		
//		json = jsonArr.getJSONObject(getId);
//		assertEquals(id, json.getInt("id"));
//		System.out.println(json.getInt("id"));
//		assertEquals(title, json.getString("title"));
//		System.out.println(title + " " + json.getString("title"));
//		assertEquals(description, json.getString("description"));
//		assertEquals(budget, json.getDouble("budget"), 0.001);
//		assertEquals(onLoan, json.getString("onLoan"));
//		assertEquals(director, json.getString("director"));
//		assertEquals(country, json.get("country"));
//		assertEquals(year, json.getInt("yearMade"));
//		assertEquals(rentalPrice, json.getDouble("rentalPrice"), 0.001);
//		assertEquals(picture, json.get("picture"));
//	}
//	
//	@Test
//	@Parameters(method = "singleFilm")
//	public void testGetRequestForSingleEntity(int getId, int id, String title, String description, String director,
//			String country, int year, double budget, double rentalPrice, String onLoan, String picture) throws IOException, JSONException {
//		
//		obj = new URL(GET_ALL_ENTITIES);
//		con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod(GET_REQUEST);
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//		
//		assertEquals(200, con.getResponseCode());
//		json = new JSONObject("{\"movies\":" + response.toString() + "}");
//		jsonArr = json.getJSONArray("movies");
//		
//		json = jsonArr.getJSONObject(getId);
//		assertEquals(id, json.getInt("id"));
//		System.out.println(json.getInt("id"));
//		assertEquals(title, json.getString("title"));
//		System.out.println(title + " " + json.getString("title"));
//		assertEquals(description, json.getString("description"));
//		assertEquals(budget, json.getDouble("budget"), 0.001);
//		assertEquals(onLoan, json.getString("onLoan"));
//		assertEquals(director, json.getString("director"));
//		assertEquals(country, json.get("country"));
//		assertEquals(year, json.getInt("yearMade"));
//		assertEquals(rentalPrice, json.getDouble("rentalPrice"), 0.001);
//		assertEquals(picture, json.get("picture"));
//	}
//	
//	@Test
//	@Parameters(method = "searchParametersThatReturnOneFilm")
//	public void testGetRequestWithSingleSearchParameters(String column, String parameter, String name) throws IOException, JSONException {
//		
//		obj = new URL(SEARCH_PARAMETER_URL + column + "=" + parameter);
//		con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod(GET_REQUEST);
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//		
//		assertEquals(200, con.getResponseCode());
//		json = new JSONObject("{\"movies\":" + response.toString() + "}");
//		jsonArr = json.getJSONArray("movies");
//		
//		json = jsonArr.getJSONObject(0);
//		assertEquals(name, json.getString("title"));
//		
//	}
//	
//	@Test
//	@Parameters(method = "moreThanOneFilmReturnedFromSearch")
//	public void testGetRequestWithSingleSearchParametersForMoreThanOneFilm(int jsonId, String title) throws IOException, JSONException {
//		
//		obj = new URL(SEARCH_PARAMETER_URL + "director=Steven Spielberg");
//		con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod(GET_REQUEST);
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//		
//		assertEquals(200, con.getResponseCode());
//		json = new JSONObject("{\"movies\":" + response.toString() + "}");
//		jsonArr = json.getJSONArray("movies");
//		
//		json = jsonArr.getJSONObject(0);
//		assertEquals(title, json.getString("title"));
//		
//	}
//}
