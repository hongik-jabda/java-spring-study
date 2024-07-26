package equal;

import java.util.Objects;

public class Car {
    private final String name;
    private int num;

    public Car(String name) {
        this.name = name;
    }

    // 객체의 동등성 검증에 사용됨
    // 논리적 동등성의 기준을 어떻게 정하느냐에 따라 개발자가 재정의해야함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // 주소값 같으면 동일성 만족하니 바로 true 리턴
        if (o == null || getClass() != o.getClass()) return false; // null 또는 자료형이 다르면 false 리턴
        Car car = (Car)o; // 인자로 전달된 객체를 Car 클래스로 형변환
        return name.equals(car.name); // name 필드 값이 동일한지 비교
    }


    // 주소값이 다르더라도 name 필드 값이 동일하면 hashCode 결과값이 동일
    @Override
    public int hashCode() {
        return Objects.hash(name, num);
    }
}
