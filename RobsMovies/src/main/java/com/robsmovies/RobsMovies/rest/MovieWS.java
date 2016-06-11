package com.robsmovies.RobsMovies.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.io.ByteArrayInputStream;
import sun.misc.BASE64Decoder;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.sonatype.plexus.components.cipher.Base64;

import com.mysql.jdbc.util.Base64Decoder;
import com.robsmovies.RobsMovies.data.MovieDAO;
import com.robsmovies.RobsMovies.model.Movie;
import com.robsmovies.RobsMovies.util.SearchMovieByParams;
import com.sun.jersey.core.header.FormDataContentDisposition;

@Path("/movies")
@Stateless
@LocalBean
public class MovieWS {

	Object[][] columnValues = new Object[9][9];
	//SearchMovieByParams movieSearcher = new SearchMovieByParams();

	@EJB
	private MovieDAO movieDao;
	
	@EJB
	private SearchMovieByParams movieSearcher;

	/*
	 * This method receives requests for 'all movies', when nothing is appended
	 * to the URL
	 */

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		System.out.println("Get all movies");
		final List<Movie> movie = movieDao.getAllMovies();
		return Response.status(200).entity(movie).build();
	}

	/*
	 * This method uses an ID on the end of the URL to get a movie
	 */

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findMovieById(@PathParam("id") final String identifier) {
		Movie movieMatch = getMovieById(identifier);

		if (movieMatch.getTitle().length() > 0) {
			return Response.status(200).entity(movieMatch).build();
		} else {
			return Response.status(404).entity("<html><h2>No Data To Return</h2></html>").build();
		}
	}
	
	/*
	 * This method takes in parameters from the URL and begins the search
	 * process
	 */

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/search")
	public Response findMovieByParams(@QueryParam("title") final String title,
			@QueryParam("director") final String director, @QueryParam("budget") final Double budget,
			@QueryParam("rentalPrice") final Double rentalPrice, @QueryParam("description") final String description,
			@QueryParam("country") final String country, @QueryParam("yearMade") final Integer yearMade,
			@QueryParam("onLoan") final String onLoan, @QueryParam("picture") final String picture) {

		final Object[] parametersDerivedFromUrl = { title, description, director, country, yearMade, budget, rentalPrice,
				onLoan, picture };
		
		final List<Movie> movie = movieSearcher.searchMovie(parametersDerivedFromUrl);


		if (movie.isEmpty()) {
			return Response.status(404).entity("<html>No data to return</html>").build();
		} else {

			return Response.status(200).entity(movie).build();
		}

	}

	/*
	 * This method takes in parameters for a new movie
	 */

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveMovie(final Movie movie) {
		movieDao.save(movie);
		return Response.status(201).entity(movie).build();
	}

	/*
	 * This method updates a movie with given parameters
	 */

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/{id}")
	public Movie update(final Movie movie, @PathParam("id") final int identifier) {
		movie.setId(identifier);
		System.out.println("Updating movie: " + movie.getTitle());
		movieDao.update(movie);
		return movie;
	}

	/*
	 * This method receives the data used to remove a movie from the database
	 */

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(Movie movie, @PathParam("id") final int identifier) {
		movieDao.remove(identifier);
	}
	
	@POST
	@Path("/upload")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response uploadFile(
	        @FormDataParam("data") String imageInBase64) throws IOException, JSONException
	         {


		// tokenize the data
		String[] imageArray = imageInBase64.split(",");
		String imageString = imageArray[1];
		String fileExtension = "";
		if(imageArray[0].contains("jpg")){
			fileExtension = "jpg";
		}
		if(imageArray[0].contains("png")){
			fileExtension = "png";
		}
		System.out.println(imageArray[2]);
		// create a buffered image
		BufferedImage image = null;
		byte[] imageByte;

		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		bis.close();

		// write the image to a file
		File outputfile = new File("C:\\Users\\A00226084\\Desktop\\newimage." + fileExtension);
		ImageIO.write(image, "png", outputfile);
	    return Response.status(200).entity("HIHI").build();

	}

	/**
	 * This method begins the find by ID process
	 */

	public Movie getMovieById(final String identifier) {
		final int movieIdAsInt = Integer.parseInt(identifier);
		final Movie movieMatch = movieDao.getMovie(movieIdAsInt);
		return movieMatch;
	}
}