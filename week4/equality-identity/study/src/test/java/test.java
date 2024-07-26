import equal.Car;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class test {

    @Test
    void carEqualityTest(){
        Car car1 = new Car("k5");
        Car car2 = new Car("k5");

        Assertions.assertTrue(car1.equals(car2));
//        Assertions.assertThat(car1).isEqualTo(car2); // isEqualTo는 내부적으로 equals 메서드 사용
    }


    @Test
    void carContainTest(){
        Car car1 = new Car("벤츠");
        Car car2 = new Car("벤츠");

        Set<Car> cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);

        assertThat(cars.size()).isEqualTo(1);

    }
}
