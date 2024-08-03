package dompoo.mockitodemo.studytest;

import dompoo.mockitodemo.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
public class MockStudyTest {
	
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
	@DisplayName("상품 저장 테스트 - 서비스 모킹")
	void productSaveTestWithMockLikeMockist() {
		//given
		Product product = Product.builder()
				.name("아메리카노")
				.cost(5000)
				.build();
		
		ProductService mockService = mock(ProductService.class);
		ProductController controller = new ProductController(mockService);
		
		when(mockService.saveProduct(any(Product.class)))
				.thenReturn(product);
		
		//when
		Product result = controller.saveProduct(product);
		
		//then
		assertThat(result.getName()).isEqualTo("아메리카노");
		assertThat(result.getCost()).isEqualTo(5000);
	}
	
	@Test
	@DisplayName("상품 저장 테스트 - 메일팩토리 모킹")
	void productSaveTestWithMockLikeClassist() {
		//given
		Product product = Product.builder()
				.name("아메리카노")
				.cost(5000)
				.build();
		
		MailSendFactory mailFactory = mock(MailSendFactory.class);
		ProductRepository repository = new ProductRepository();
		ProductService service = new ProductService(mailFactory, repository);
		ProductController controller = new ProductController(service);
		
		//when
		Product result = controller.saveProduct(product);
		
		//then
		assertThat(result.getName()).isEqualTo("아메리카노");
		assertThat(result.getCost()).isEqualTo(5000);
	}
}
