package mission2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTypeTest {

    CarType carType = CarType.getInstance();
    @Test
    void getCarTypeId() {
        carType.setCarTypeId(1);
        Assertions.assertThat(carType.getCarTypeId()).isEqualTo(1);
    }

    @Test
    void getCarTypeName_SEDAN() {
        carType.setCarTypeId(1);
        Assertions.assertThat(carType.getCarTypeName()).isEqualTo("Sedan");
    }
    @Test
    void getCarTypeName_SUV() {
        carType.setCarTypeId(2);
        Assertions.assertThat(carType.getCarTypeName()).isEqualTo("SUV");
    }
    @Test
    void getCarTypeName_TRUCK() {
        carType.setCarTypeId(3);
        Assertions.assertThat(carType.getCarTypeName()).isEqualTo("Truck");
    }
    @Test
    void getCarTypeName_UNKNOWN() {
        carType.setCarTypeId(4);
        Assertions.assertThat(carType.getCarTypeName()).isEqualTo("알 수 없는 차종");
    }
}