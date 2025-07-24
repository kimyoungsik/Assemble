package mission2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SteeringTest {
    Steering steering = Steering.getInstance();

    @Test
    @DisplayName("Steering id 가져오기")
    void getSteeringId() {
        steering.setSteeringId(1);
        Assertions.assertThat(steering.getSteeringId()).isEqualTo(1);
    }

    @Test
    @DisplayName("Steering name Bosch")
    void getSteeringName_Bosch() {
        steering.setSteeringId(1);
        Assertions.assertThat(steering.getSteeringName()).isEqualTo("Bosch");
    }

    @Test
    @DisplayName("Steering name MOBIS")
    void getSteeringName_MOBIS() {
        steering.setSteeringId(2);
        Assertions.assertThat(steering.getSteeringName()).isEqualTo("Mobis");
    }

    @Test
    @DisplayName("Steering name UNKNOWN")
    void getSteeringName_Unknown() {
        steering.setSteeringId(3);
        Assertions.assertThat(steering.getSteeringName()).isEqualTo("알 수 없는 Steering 시스템");
    }
}