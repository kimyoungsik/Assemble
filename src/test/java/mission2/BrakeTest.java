package mission2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrakeTest {

    Brake brake = Brake.getInstance();
    @Test
    @DisplayName("Brake ID 가져오기")
    void getBrakeId() {
        brake.setBrakeId(1);
        Assertions.assertThat(brake.getBrakeId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Brake name Mando 가져오기")
    void getBrakeNameMando() {
        brake.setBrakeId(1);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("Mando");
    }
    @Test
    @DisplayName("Brake name Continental 가져오기")
    void getBrakeNameCONTINENTAL() {
        brake.setBrakeId(2);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("Continental");
    }
    @Test
    @DisplayName("Brake name Bosch 가져오기")
    void getBrakeNameBOSCH_B() {
        brake.setBrakeId(3);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("Bosch");
    }
    @Test
    @DisplayName("Brake name Unknown")
    void getBrakeNameUnknown() {
        brake.setBrakeId(4);
        Assertions.assertThat(brake.getBrakeName()).isEqualTo("알 수 없는 제동장치");
    }
}