package dompoo;


import java.time.LocalDate;
import java.time.Period;

public class Screen {
	
	private String name;
	private Movie movie;
	private int seats;
	
	public Screen(String name, Movie movie, int seats) {
		this.name = name;
		this.movie = movie;
		this.seats = seats;
	}
	
	public int getMovieCost() {
		int seats = this.getSeats();
		int movieCost = this.movie.getCostWithValidationDate();
		
		return seats * movieCost;
	}
	
	public String getMovieName() {
		return movie.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public MovieStatusResponse getMovieStatusResponse() {
		return new MovieStatusResponse(getMovieName(), movie.getCost());
	}
}
