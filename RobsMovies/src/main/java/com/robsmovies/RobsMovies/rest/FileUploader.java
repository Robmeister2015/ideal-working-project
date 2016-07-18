package com.robsmovies.RobsMovies.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONException;

import sun.misc.BASE64Decoder;

@Path("/movies")
@Stateless
@LocalBean
public class FileUploader {

	@POST
	@Path("/upload")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response uploadFile(
	        @FormDataParam("data") String imageInBase64) throws IOException, JSONException
	         {

		String[] imageArray = imageInBase64.split(",");
		System.out.println(imageArray.length);
		String imageString = imageArray[1];
		String fileExtension = "";
		if(imageArray[0].contains("jpg")){
			fileExtension = "jpg";
		}
		if(imageArray[0].contains("png")){
			fileExtension = "png";
		}
		System.out.println(imageArray[2]);
		BufferedImage image = null;
		byte[] imageByte;

		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		bis.close();

		// write the image to a file
		File outputfile = new File("C:/Users/A00226084/Documents/Software/jboss/standalone/deployments/RobsMovies.war/resources/images/" + imageArray[2]);
		ImageIO.write(image, "png", outputfile);
		
		
	    return Response.status(200).build();

	}
	
}
