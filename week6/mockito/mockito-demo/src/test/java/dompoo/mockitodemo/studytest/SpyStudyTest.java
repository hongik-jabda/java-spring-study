package dompoo.mockitodemo.studytest;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
public class SpyStudyTest {
	
	@Test
	void testWithoutMock() {
	    //given
		DemoDomain demoDomain = new DemoDomain();
		DemoService service = new DemoService(demoDomain);
		DemoController controller = new DemoController(service);
		
		//when
		String result = controller.fun();
		
		//then
		assertThat(result).isEqualTo("소용돌이");
	}
	
	@Test
	void testWithMockLikeClassist() {
		//given
		DemoDomain realDemoDomain = new DemoDomain();
		DemoDomain mockDomain = spy(realDemoDomain);
		DemoService service = new DemoService(mockDomain);
		DemoController controller = new DemoController(service);
		doNothing().when(mockDomain).sendEmail();
		
		//when
		String result = controller.fun();
		
		//then
		assertThat(result).isEqualTo("소용돌이");
		verify(mockDomain, times(1)).sendEmail();
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
		
		public String fun() {
			log.info("서비스 메서드 호출");
			String result = demoDomain.fun();
			demoDomain.sendEmail();
			return "용" + result;
		}
	}
	
	public class DemoDomain {
		
		public DemoDomain() {
		}
		
		public String fun() {
			log.info("도메인 메서드 호출");
			return "돌이";
		}
		
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
