package dompoo;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class TheaterTest {
	
	@Test
	@DisplayName("영화는 상영중인 영화들의 최대 매출 합계를 구할 수 있다.")
	void getExpectedIncome() {
	    //given
		Movie movie1 = new Movie("달팽이인간", 10, MovieStatus.ON_SCREEN, LocalDate.now().minusDays(3), LocalDate.now().plusDays(3));
		Movie movie2 = new Movie("키리에", 20, MovieStatus.ON_SCREEN, LocalDate.now().minusDays(3), LocalDate.now().plusDays(4));
		Screen screen1 = new Screen("상영관1", movie1, 100);
		Screen screen2 = new Screen("상영관2", movie2, 100);
		Theater theater = new Theater("CGV", List.of(screen1, screen2));
		
		//when
		int result = theater.getExpectedIncome();
		
		//then
		assertThat(result).isEqualTo(11000);
	}
	
	@Test
	@DisplayName("assertj 탐구해보기")
	void test1() {
		String str = "창근 태순 경주";
		
		assertThat(str).contains("태순");
		assertThat(str).startsWith("창");
		assertThat(str).endsWith("주");
		
		LocalDate now = LocalDate.now();
		LocalDate yesterday = LocalDate.now().minusDays(1);
		
		assertThat(now).isAfter(yesterday);
	}
	
	@Test
	@DisplayName("영화관은 자신의 영화관 상태를 반환할 수 있어야 한다.")
	void getStauts() {
	    //given
		Movie movie1 = new Movie("달팽이인간", 10, MovieStatus.ON_SCREEN, LocalDate.now().minusDays(3), LocalDate.now().plusDays(3));
		Movie movie2 = new Movie("키리에", 20, MovieStatus.ON_SCREEN, LocalDate.now().minusDays(3), LocalDate.now().plusDays(4));
		Screen screen1 = new Screen("상영관1", movie1, 100);
		Screen screen2 = new Screen("상영관2", movie2, 100);
		Theater theater = new Theater("CGV", List.of(screen1, screen2));
	    
	    //when
		TheaterStatus result = theater.getStauts();
		
		//then
		assertThat(result)
				.extracting("theaterName", "screenNames", "movieNames", "expectedIncome")
				.contains("CGV", List.of("상영관1", "상영관2"), List.of("달팽이인간", "키리에"), 11000);
		
		assertThat(result.getTheaterName()).isEqualTo("CGV");
		assertThat(result.getScreenNames()).containsExactlyInAnyOrder("상영관1", "상영관2");
		assertThat(result.getMovieNames()).containsExactlyInAnyOrder("달팽이인간", "키리에");
		assertThat(result.getExpectedIncome()).isEqualTo(11000);
	}
	
	@Test
	@DisplayName("영화관은 상영중인 모든 영화의 이름과 표 가격을 반환할 수 있어야 한다.")
	void getMovieNames() {
		//given
		Movie movie1 = new Movie("달팽이인간", 10, MovieStatus.ON_SCREEN, LocalDate.now().minusDays(3), LocalDate.now().plusDays(3));
		Movie movie2 = new Movie("키리에", 20, MovieStatus.ON_SCREEN, LocalDate.now().minusDays(3), LocalDate.now().plusDays(4));
		Screen screen1 = new Screen("상영관1", movie1, 100);
		Screen screen2 = new Screen("상영관2", movie2, 100);
		Theater theater = new Theater("CGV", List.of(screen1, screen2));
		
		//when
		List<MovieStatusResponse> result = theater.getMovieStatusResponse();
		
		//then
		assertThat(result.get(0).getName()).isEqualTo("달팽이인간");
		assertThat(result.get(0).getCost()).isEqualTo(10);
		assertThat(result.get(1).getName()).isEqualTo("키리에");
		assertThat(result.get(1).getCost()).isEqualTo(20);
		
		
		assertThat(result)
				.extracting("name", "cost")
				.containsExactlyInAnyOrder(
						Tuple.tuple("키리에", 20),
						Tuple.tuple("달팽이인간", 10)
				);
	}
	
}