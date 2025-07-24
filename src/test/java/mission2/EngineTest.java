package mission2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    Engine engine = Engine.getInstance();


    @Test
    void getEngineId() {
        engine.setEngineId(1);
        Assertions.assertThat(engine.getEngineId()).isEqualTo(1);
    }

    @Test
    void getEngineName() {
        engine.setEngineId(1);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("GM");
    }

    @Test
    void getEngineName_TOYOTA() {
        engine.setEngineId(2);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("TOYOTA");
    }

    @Test
    void getEngineName_WIA() {
        engine.setEngineId(3);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("WIA");
    }

    @Test
    void getEngineName_BreakDown() {
        engine.setEngineId(4);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("고장난 엔진");
    }

    @Test
    void getEngineName_UNKNOWN() {
        engine.setEngineId(5);
        Assertions.assertThat(engine.getEngineName()).isEqualTo("알 수 없는 엔진");
    }
}