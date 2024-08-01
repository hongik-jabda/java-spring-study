package dompoo;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Theater {
	
	private String name;
	private List<Screen> screens;
	
	public Theater(String name, List<Screen> screens) {
		this.name = name;
		this.screens = screens;
	}
	
	public int getExpectedIncome() {
		int totalIncome = 0;
		
		for (Screen screen : screens) {
			totalIncome += screen.getMovieCost();
		}
		return totalIncome;
	}
	
	public TheaterStatus getStauts() {
		return new TheaterStatus(
				name,
				screens.stream().map(Screen::getName).toList(),
				screens.stream().map(Screen::getMovieName).toList(),
				getExpectedIncome()
		);
	}
	
	public List<MovieStatusResponse> getMovieStatusResponse() {
		ArrayList<MovieStatusResponse> result = new ArrayList<>();
		
		for (Screen screen : screens) {
			MovieStatusResponse response = screen.getMovieStatusResponse();
			result.add(response);
		}
		
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Screen> getScreens() {
		return screens;
	}
}
