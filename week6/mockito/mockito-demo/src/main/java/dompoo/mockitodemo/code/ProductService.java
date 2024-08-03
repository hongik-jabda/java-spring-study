package dompoo.mockitodemo.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {
	
	private final MailSendFactory mailSendFactory;
	private final ProductRepository productRepository;
	
	public Product saveProduct(Product product) {
		log.info(">>> service.saveProduct");
		Product result = productRepository.save(product);
		sendMailOfTotalPrice();
		return result;
	}
	
	public void sendMailOfTotalPrice() {
		log.info(">>> service.sendMailOfTotalPrice");
		int totalPrice = productRepository.findAll()
				.stream().mapToInt(Product::getCost)
				.sum();
		
		mailSendFactory.sendEmail(totalPrice);
	}
}
