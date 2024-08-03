package dompoo.mockitodemo.code;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class Product {
	
	private Long id;
	private String name;
	private int cost;
	
	@Builder
	private Product(String name, int cost) {
		this.name = name;
		this.cost = cost;
	}
}
