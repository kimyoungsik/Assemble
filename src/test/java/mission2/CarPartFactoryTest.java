package mission2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarPartFactoryTest {

    @Test
    @DisplayName("cartype 생성")
    public void testCreateCarType() {
        CarType carType = CarPartFactory.createCarType(1);

        assertEquals(1, carType.getCarTypeId());
        assertEquals("Sedan", carType.getCarTypeName());
    }

    @Test
    @DisplayName("engine 생성")
    public void testCreateEngine() {
        Engine engine = CarPartFactory.createEngine(2);

        assertEquals(2, engine.getEngineId());
        assertEquals("TOYOTA", engine.getEngineName());
    }

    @Test
    @DisplayName("brake 생성")
    public void testCreateBrake() {
        Brake brake = CarPartFactory.createBrake(3);

        assertEquals(3, brake.getBrakeId());
        assertEquals("Bosch", brake.getBrakeName());
    }

    @Test
    @DisplayName("steering 생성")
    public void testCreateSteering() {
        Steering steering = CarPartFactory.createSteering(1);

        assertNotNull(steering);
        assertEquals(1, steering.getSteeringId());
        assertEquals("Bosch", steering.getSteeringName());
    }

    @Test
    @DisplayName("싱글톤 패턴 테스트")
    public void testSingltonPattern() {
        CarType carType1 = CarPartFactory.createCarType(1);
        CarType carType2 = CarPartFactory.createCarType(2);

        assertSame(carType1, carType2);
        assertEquals(2, carType2.getCarTypeId());
    }
}