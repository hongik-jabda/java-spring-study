package dompoo.mockitodemo.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ProductRepository {
	
	private final Map<Long, Product> repositoryMap = new HashMap<>();
	private Long id = 0L;
	
	public Product save(Product product) {
		log.info(">>> repository.save");
		Long newId = id++;
		product.setId(newId);
		repositoryMap.put(newId, product);
		return repositoryMap.get(newId);
	}
	
	public List<Product> findAll() {
		log.info(">>> repository.findAll");
		return repositoryMap.values().stream().toList();
	}
}
