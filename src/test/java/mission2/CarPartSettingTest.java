package mission2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarPartSettingTest {

    @Test
    @DisplayName("cartype setting 검증")
    public void testCarTypesSetting() {
        assertEquals("Sedan", CarPartSetting.CAR_TYPES.get(1));
        assertEquals("SUV", CarPartSetting.CAR_TYPES.get(2));
        assertEquals("Truck", CarPartSetting.CAR_TYPES.get(3));
        assertNull(CarPartSetting.CAR_TYPES.get(4));
    }

    @Test
    @DisplayName("엔진 setting 검증")
    public void testEnginesSetting() {
        assertEquals("GM", CarPartSetting.ENGINES.get(1));
        assertEquals("TOYOTA", CarPartSetting.ENGINES.get(2));
        assertEquals("WIA", CarPartSetting.ENGINES.get(3));
        assertEquals("고장난 엔진", CarPartSetting.ENGINES.get(4));
        assertNull(CarPartSetting.ENGINES.get(5));
    }

    @Test
    @DisplayName("brake setting 검증")
    public void testBrakesSetting() {
        assertEquals("Mando", CarPartSetting.BRAKES.get(1));
        assertEquals("Continental", CarPartSetting.BRAKES.get(2));
        assertEquals("Bosch", CarPartSetting.BRAKES.get(3));
        assertNull(CarPartSetting.BRAKES.get(4));
    }

    @Test
    @DisplayName("Steering setting 검증")
    public void testSteeringsSetting() {
        assertEquals("Bosch", CarPartSetting.STEERINGS.get(1));
        assertEquals("Mobis", CarPartSetting.STEERINGS.get(2));
        assertNull(CarPartSetting.STEERINGS.get(3));
    }

    @Test
    @DisplayName("부품  id 범위 체크")
    public void testValidationMethods() {

        assertTrue(CarPartSetting.isValidCarType(1));
        assertTrue(CarPartSetting.isValidEngine(1));
        assertTrue(CarPartSetting.isValidBrake(1));
        assertTrue(CarPartSetting.isValidSteering(1));

        assertFalse(CarPartSetting.isValidCarType(4));
        assertFalse(CarPartSetting.isValidEngine(5));
        assertFalse(CarPartSetting.isValidBrake(4));
        assertFalse(CarPartSetting.isValidSteering(3));
    }

    @Test
    @DisplayName("부품  max id 체크")
    public void testMaxIdMethods() {
        assertEquals(3, CarPartSetting.getMaxCarTypeId());
        assertEquals(4, CarPartSetting.getMaxEngineId());
        assertEquals(3, CarPartSetting.getMaxBrakeId());
        assertEquals(2, CarPartSetting.getMaxSteeringId());
    }
}