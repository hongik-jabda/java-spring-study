package dompoo.mockitodemo.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MailSendFactory {
	public void sendEmail(int price) {
		log.info("메일 보내는 중...");
		send();
		log.info("메일 보내기 완료, 총 가격 : {}", price);
	}
	
	public void send() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
