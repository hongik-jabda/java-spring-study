package dompoo;


import java.time.LocalDate;
import java.time.Period;

public class Movie {
	
	private String name;
	private int cost;
	private MovieStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Movie(String name, int cost, MovieStatus status, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.cost = cost;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int getCostWithValidationDate() {
		LocalDate now = LocalDate.now();
		LocalDate endDate = this.getEndDate();
		if (now.isAfter(endDate)) {
			throw new IllegalArgumentException("영화 종료됐는딩");
		}
		int screenDays = Period.between(now, endDate).getDays();
		
		return screenDays * cost;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public MovieStatus getStatus() {
		return status;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
}
