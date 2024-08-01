package dompoo;

import java.util.List;

public class TheaterStatus {
	
	private String theaterName;
	private List<String> screenNames;
	private List<String> movieNames;
	private int expectedIncome;
	
	public TheaterStatus(String theaterName, List<String> screenNames, List<String> movieNames, int expectedIncome) {
		this.theaterName = theaterName;
		this.screenNames = screenNames;
		this.movieNames = movieNames;
		this.expectedIncome = expectedIncome;
	}
	
	public String getTheaterName() {
		return theaterName;
	}
	
	public List<String> getScreenNames() {
		return screenNames;
	}
	
	public List<String> getMovieNames() {
		return movieNames;
	}
	
	public int getExpectedIncome() {
		return expectedIncome;
	}
}
