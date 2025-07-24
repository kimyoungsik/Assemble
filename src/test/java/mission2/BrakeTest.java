package mission2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BrakeTest {

    Brake brake = Brake.getInstance();
    @Test
    void getBrakeId() {
        brake.setBrakeId(1);
        Assertions.assertThat(brake.getBrakeId()).isEqualTo(1);
    }

    @Test
    void getBrakeName() {
        brake.setBrakeId(1);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("Mando");
    }
    @Test
    void getBrakeNameCONTINENTAL() {
        brake.setBrakeId(2);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("Continental");
    }
    @Test
    void getBrakeNameBOSCH_B() {
        brake.setBrakeId(3);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("Bosch");
    }
    @Test
    void getBrakeNameUnknown() {
        brake.setBrakeId(4);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("알 수 없는 제동장치");
    }
}