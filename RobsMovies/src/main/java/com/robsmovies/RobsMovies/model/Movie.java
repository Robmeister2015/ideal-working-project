package com.robsmovies.RobsMovies.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int identifier;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "director")
	private String director;

	@Column(name = "country")
	private String country;

	@Column(name = "yearMade")
	private int yearMade;

	@Column(name = "budget")
	private double budget;

	@Column(name = "rentalPrice")
	private double rentalPrice;

	@Column(name = "onLoan")
	private String onLoan;

	@Column(name = "picture")
	private String picture;

	public int getId() {
		return identifier;
	}

	public void setId(final int identifier) {
		this.identifier = identifier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(final String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public int getYearMade() {
		return yearMade;
	}

	public void setYearMade(final int yearMade) {
		this.yearMade = yearMade;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(final double budget) {
		this.budget = budget;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(final double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getOnLoan() {
		return onLoan;
	}

	public void setOnLoan(final String onLoan) {
		this.onLoan = onLoan;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}
}
