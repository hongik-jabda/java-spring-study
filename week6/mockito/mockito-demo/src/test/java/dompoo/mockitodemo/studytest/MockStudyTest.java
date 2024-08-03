package dompoo.mockitodemo.studytest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
public class MockStudyTest {
	
	@Test
	void testWithoutMock() {
	    //given
		DemoDomain demoDomain = new DemoDomain();
		MailDomain mailDomain = new MailDomain();
		DemoService service = new DemoService(demoDomain, mailDomain);
		DemoController controller = new DemoController(service);
		
		//when
		String result = controller.fun();
		
		//then
		assertThat(result).isEqualTo("소용돌이");
	}
	
	@Test
	void testWithMockLikeMockist() {
		//given
		DemoService mockService = mock(DemoService.class);
		DemoController controller = new DemoController(mockService);
		when(mockService.fun()).thenReturn("용돌이");
		
		//when
		String result = controller.fun();
		
		//then
		assertThat(result).isEqualTo("소용돌이");
		verify(mockService, times(1)).fun();
	}
	
	@Test
	void testWithMockLikeClassist() {
		//given
		DemoDomain demoDomain = new DemoDomain();
		MailDomain mailDomain = mock(MailDomain.class);
		DemoService service = new DemoService(demoDomain, mailDomain);
		DemoController controller = new DemoController(service);
		
		//when
		String result = controller.fun();
		
		//then
		assertThat(result).isEqualTo("소용돌이");
	}
	
	@RequiredArgsConstructor
	private class DemoController {
		
		private final DemoService demoService;
		
		public String fun() {
			log.info("컨트롤러 메서드 호출");
			String result = demoService.fun();
			return "소" + result;
		}
	}
	
	@RequiredArgsConstructor
	private class DemoService {
		
		private final DemoDomain demoDomain;
		private final MailDomain mailDomain;
		
		public String fun() {
			log.info("서비스 메서드 호출");
			String result = demoDomain.fun();
			mailDomain.sendEmail();
			return "용" + result;
		}
	}
	
	private class DemoDomain {
		public String fun() {
			log.info("도메인 메서드 호출");
			return "돌이";
		}
	}
	
	private class MailDomain {
		public void sendEmail() {
			log.info("메일 보내는 중...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			log.info("메일 보내기 완료");
		}
	}
}
