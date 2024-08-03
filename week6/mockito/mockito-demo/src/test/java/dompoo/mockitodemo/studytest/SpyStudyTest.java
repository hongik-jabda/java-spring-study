package dompoo.mockitodemo.studytest;

import dompoo.mockitodemo.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

@Slf4j
public class SpyStudyTest {
	
	@Test
	@DisplayName("상품 저장 테스트 - 모킹 X")
	void productSaveTestWithoutMock() {
		//given
		Product product = Product.builder()
				.name("아메리카노")
				.cost(5000)
				.build();
		
		MailSendFactory mailFactory = new MailSendFactory();
		ProductRepository repository = new ProductRepository();
		ProductService service = new ProductService(mailFactory, repository);
		ProductController controller = new ProductController(service);
		
		//when
		Product result = controller.saveProduct(product);
		
		//then
		assertThat(result.getName()).isEqualTo("아메리카노");
		assertThat(result.getCost()).isEqualTo(5000);
	}
	
	@Test
	@DisplayName("상품 저장 테스트 - 서비스 스파이 모킹")
	void productSaveTestWithSpy() {
		//given
		Product product = Product.builder()
				.name("아메리카노")
				.cost(5000)
				.build();
		
		MailSendFactory mailFactory = spy(MailSendFactory.class);
		ProductRepository repository = new ProductRepository();
		ProductService service = new ProductService(mailFactory, repository);
		ProductController controller = new ProductController(service);
		
		doNothing().when(mailFactory).send();
		
		//when
		Product result = controller.saveProduct(product);
		
		//then
		assertThat(result.getName()).isEqualTo("아메리카노");
		assertThat(result.getCost()).isEqualTo(5000);
	}
}
