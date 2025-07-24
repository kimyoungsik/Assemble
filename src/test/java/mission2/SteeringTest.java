package mission2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SteeringTest {
    Steering steering = Steering.getInstance();
    @Test
    void getSteeringId() {
        steering.setSteeringId(1);
        Assertions.assertThat(steering.getSteeringId()).isEqualTo(1);
    }

  //  private static final int BOSCH_S = 1, MOBIS = 2;

    @Test
    void getSteeringName() {
        steering.setSteeringId(1);
        Assertions.assertThat(steering.getSteeringName()).isEqualTo("Bosch");
    }

    @Test
    void getSteeringName_MOBIS() {
        steering.setSteeringId(2);
        Assertions.assertThat(steering.getSteeringName()).isEqualTo("Mobis");
    }
    @Test
    void getSteeringName_Unknown() {
        steering.setSteeringId(3);
        Assertions.assertThat(steering.getSteeringName()).isEqualTo("알 수 없는 Steering 시스템");
    }
}