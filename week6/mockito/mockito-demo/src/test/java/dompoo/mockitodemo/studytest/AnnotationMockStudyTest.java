package dompoo.mockitodemo.studytest;

import dompoo.mockitodemo.code.MailSendFactory;
import dompoo.mockitodemo.code.Product;
import dompoo.mockitodemo.code.ProductRepository;
import dompoo.mockitodemo.code.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AnnotationMockStudyTest {
	
	@Mock
	private MailSendFactory mailSendFactory;
	
	@Spy
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	@Test
	@DisplayName("상품 저장 테스트 - 어노테이션 기반 모킹")
	void productSaveTestWithMockAnnotations() {
	    //given
		Product product = Product.builder()
				.name("아메리카노")
				.cost(5000)
				.build();
		
	    //when
		Product result = productService.saveProduct(product);
		
		//then
		assertThat(result.getName()).isEqualTo("아메리카노");
		assertThat(result.getCost()).isEqualTo(5000);
	}
}
