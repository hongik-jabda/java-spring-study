package dompoo.mockitodemo.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ProductController {
	
	private final ProductService productService;
	
	public Product saveProduct(Product product) {
		log.info(">>> controller.saveProduct");
		return productService.saveProduct(product);
	}
}
