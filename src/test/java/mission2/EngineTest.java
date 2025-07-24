package mission2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    Engine engine = Engine.getInstance();


    @Test
    @DisplayName("engine id 가져오기")
    void getEngineId() {
        engine.setEngineId(1);
        Assertions.assertThat(engine.getEngineId()).isEqualTo(1);
    }

    @Test
    @DisplayName("engine name GM 가져오기")
    void getEngineName_GM() {
        engine.setEngineId(1);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("GM");
    }

    @Test
    @DisplayName("engine name TOYOTA 가져오기")
    void getEngineName_TOYOTA() {
        engine.setEngineId(2);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("TOYOTA");
    }

    @Test
    @DisplayName("engine name WIA 가져오기")
    void getEngineName_WIA() {
        engine.setEngineId(3);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("WIA");
    }

    @Test
    @DisplayName("engine name 고장케이스")
    void getEngineName_BreakDown() {
        engine.setEngineId(4);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("고장난 엔진");
    }

    @Test
    @DisplayName("engine name UNKNOWN")
    void getEngineName_UNKNOWN() {
        engine.setEngineId(5);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("알 수 없는 엔진");
    }
}