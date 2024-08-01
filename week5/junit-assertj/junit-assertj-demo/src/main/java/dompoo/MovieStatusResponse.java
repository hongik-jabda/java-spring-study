package dompoo;

public class MovieStatusResponse {
	private String name;
	private int cost;
	
	public MovieStatusResponse(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
}
