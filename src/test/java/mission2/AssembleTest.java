package mission2;

import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class AssembleTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private AssembleHelper mockAssembleHelper;

    @Mock
    private Scanner mockScanner;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStream));

        CarType.getInstance().setCarTypeId(0);
        Engine.getInstance().setEngineId(0);
        Brake.getInstance().setBrakeId(0);
        Steering.getInstance().setSteeringId(0);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("cartype 선택")
    public void testUpdateAssemble_CarTypeSelection() {
        int result = callUpdateAssemble(0, 1);

        assertEquals(1, Assemble.carType.getCarTypeId());
        assertEquals("Sedan", Assemble.carType.getCarTypeName());

        String output = outputStream.toString();
        assertTrue(output.contains("Sedan을 선택하셨습니다"));
    }

    @Test
    @DisplayName("Engine 선택")
    public void testUpdateAssemble_EngineSelection() {

        Assemble.carType = CarPartFactory.createCarType(1);

        int result = callUpdateAssemble(1, 2);

        assertEquals(2, Assemble.engine.getEngineId());
        assertEquals("TOYOTA", Assemble.engine.getEngineName());

        String output = outputStream.toString();
        assertTrue(output.contains("TOYOTA 엔진을 선택하셨습니다"));
    }

    @Test
    @DisplayName("Brake 선택")
    public void testUpdateAssemble_BrakeSelection() {

        Assemble.carType = CarPartFactory.createCarType(1);
        Assemble.engine = CarPartFactory.createEngine(1);

        int result = callUpdateAssemble(2, 3);

        assertEquals(3, Assemble.brake.getBrakeId());
        assertEquals("Bosch", Assemble.brake.getBrakeName());

        String output = outputStream.toString();
        assertTrue(output.contains("Bosch 제동장치를 선택하셨습니다"));
    }

    @Test
    @DisplayName("Steering 선택")
    public void testUpdateAssemble_SteeringSelection() {
        Assemble.carType = CarPartFactory.createCarType(1);
        Assemble.engine = CarPartFactory.createEngine(1);
        Assemble.brake = CarPartFactory.createBrake(1);

        int result = callUpdateAssemble(3, 1);

        assertEquals(1, Assemble.steering.getSteeringId());
        assertEquals("Bosch", Assemble.steering.getSteeringName());

        String output = outputStream.toString();
        assertTrue(output.contains("Bosch 조향장치를 선택하셨습니다"));
    }

    @Test
    @DisplayName("RUN 테스트")
    public void testUpdateAssemble_Run() {
        settingValidParts();

        int result = callUpdateAssemble(4, 1);

        assertEquals(4, result);

        String output = outputStream.toString();
        assertTrue(output.contains("Car Type : Sedan"));
        assertTrue(output.contains("Engine   : GM"));
        assertTrue(output.contains("Brake    : Mando"));
        assertTrue(output.contains("Steering : Bosch"));
        assertTrue(output.contains("자동차가 동작됩니다"));
    }

    @Test
    @DisplayName("조합 Test")
    public void testUpdateAssemble_Test() {
        settingValidParts();

        int result = callUpdateAssemble(4, 2);

        assertEquals(4, result); // 같은 단계에 머물러야 함

        String output = outputStream.toString();
        assertTrue(output.contains("Test..."));
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : PASS"));
    }

    @Test
    @DisplayName("valid parts Test")
    public void testRunProducedCar_ValidCombination() {
        settingValidParts();

        callRunProducedCar();

        String output = outputStream.toString();
        assertTrue(output.contains("Car Type : Sedan"));
        assertTrue(output.contains("Engine   : GM"));
        assertTrue(output.contains("Brake    : Mando"));
        assertTrue(output.contains("Steering : Bosch"));
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : PASS"));
        assertTrue(output.contains("자동차가 동작됩니다"));
    }

    @Test
    @DisplayName("Invalid parts Test")
    public void testRunProducedCar_InvalidCombination() {
        settingInvalidParts();

        callRunProducedCar();

        String output = outputStream.toString();
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : FAIL"));
        assertTrue(output.contains("Sedan에는 Continental 제동장치 사용 불가"));
        assertTrue(output.contains("자동차가 동작되지 않습니다"));
    }

    @Test
    public void testCarPartCombinationTest_ValidCombination() {
        settingValidParts();

        boolean result = callCarPartCombinationTest();

        assertTrue(result);
        String output = outputStream.toString();
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : PASS"));
    }

    @Test
    public void testCarAssembleList() {
        settingValidParts();

        callCarAssembleList();

        String output = outputStream.toString();
        assertTrue(output.contains("Car Type : Sedan"));
        assertTrue(output.contains("Engine   : GM"));
        assertTrue(output.contains("Brake    : Mando"));
        assertTrue(output.contains("Steering : Bosch"));
    }

    @Test
    public void testDisplaySelection() {
        callDisplaySelection("GM", "엔진을");

        String output = outputStream.toString();
        assertTrue(output.contains("GM 엔진을 선택하셨습니다"));
    }

    @Test
    public void testDisplayPass() {
        callDisplayPass();

        String output = outputStream.toString();
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : PASS"));
    }

    @Test
    public void testDisplayFail() {
        callDisplayFail("테스트 에러 메시지");

        String output = outputStream.toString();
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : FAIL"));
        assertTrue(output.contains("테스트 에러 메시지"));
    }

    // 여러 규칙이 동시에 위반되는 경우 테스트
    @Test
    public void testCarPartCombinationTest_MultipleViolations() {
        // 고장난 엔진 + SUV + TOYOTA (두 개의 규칙 위반)
        Assemble.carType = CarPartFactory.createCarType(2); // SUV
        Assemble.engine = CarPartFactory.createEngine(4);   // 고장난 엔진
        Assemble.brake = CarPartFactory.createBrake(1);     // Mando
        Assemble.steering = CarPartFactory.createSteering(1); // Bosch

        boolean result = callCarPartCombinationTest();

        assertFalse(result);
        String output = outputStream.toString();
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : FAIL"));
        // 첫 번째 규칙 위반만 출력되어야 함 (고장난 엔진)
        assertTrue(output.contains("엔진이 고장나있습니다"));
    }

    @Test
    public void testValidComplexCombination() {
        // SUV + GM + Bosch + Bosch (유효한 복합 조합)
        Assemble.carType = CarPartFactory.createCarType(2); // SUV
        Assemble.engine = CarPartFactory.createEngine(1);   // GM
        Assemble.brake = CarPartFactory.createBrake(3);     // Bosch
        Assemble.steering = CarPartFactory.createSteering(1); // Bosch

        boolean result = callCarPartCombinationTest();

        assertTrue(result);
        String output = outputStream.toString();
        assertTrue(output.contains("자동차 부품 조합 테스트 결과 : PASS"));
    }

    // Helper methods for accessing private methods using reflection
    private int callUpdateAssemble(int step, int chooseNumber) {
        try {
            var method = Assemble.class.getDeclaredMethod("updateAssemble", int.class, Integer.class);
            method.setAccessible(true);
            return (int) method.invoke(null, step, chooseNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void callRunProducedCar() {
        try {
            var method = Assemble.class.getDeclaredMethod("runProducedCar");
            method.setAccessible(true);
            method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean callCarPartCombinationTest() {
        try {
            var method = Assemble.class.getDeclaredMethod("carPartCombinationTest");
            method.setAccessible(true);
            return (boolean) method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void callCarAssembleList() {
        try {
            var method = Assemble.class.getDeclaredMethod("carAssembleList");
            method.setAccessible(true);
            method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void callDisplaySelection(String first, String second) {
        try {
            var method = Assemble.class.getDeclaredMethod("displaySelection", String.class, String.class);
            method.setAccessible(true);
            method.invoke(null, first, second);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void callDisplayPass() {
        try {
            var method = Assemble.class.getDeclaredMethod("displayPass");
            method.setAccessible(true);
            method.invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void callDisplayFail(String msg) {
        try {
            var method = Assemble.class.getDeclaredMethod("displayFail", String.class);
            method.setAccessible(true);
            method.invoke(null, msg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void settingValidParts() {
        Assemble.carType = CarPartFactory.createCarType(1);
        Assemble.engine = CarPartFactory.createEngine(1);
        Assemble.brake = CarPartFactory.createBrake(1);
        Assemble.steering = CarPartFactory.createSteering(1);
    }

    private void settingInvalidParts() {
        Assemble.carType = CarPartFactory.createCarType(1);
        Assemble.engine = CarPartFactory.createEngine(1);
        Assemble.brake = CarPartFactory.createBrake(2); // Continental + Sedan
        Assemble.steering = CarPartFactory.createSteering(1);
    }
}
