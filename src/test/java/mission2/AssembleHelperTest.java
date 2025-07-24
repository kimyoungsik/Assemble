package mission2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssembleHelperTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private AssembleHelper assembleHelper;

    @Mock
    private Menu mockMenu;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outputStream));
        assembleHelper = new AssembleHelper();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("isExit Test")
    public void testIsExit() {
        assertTrue(AssembleHelper.isExit("exit"));
        assertTrue(AssembleHelper.isExit("EXIT"));
        assertFalse(AssembleHelper.isExit("quit"));
    }

    @Test
    @DisplayName("숫자 변환 Test")
    public void testUpdateToNumber() {
        assertEquals(1, AssembleHelper.updateToNumber("1"));
        assertEquals(-1, AssembleHelper.updateToNumber("-1"));
        assertNull(AssembleHelper.updateToNumber("abc"));
    }

    @Test
    @DisplayName("cartype ValidRange Test")
    public void testIsValidRange_cartype() {
        assertTrue(AssembleHelper.isValidRange(0, 1));
        assertTrue(AssembleHelper.isValidRange(0, 2));
        assertTrue(AssembleHelper.isValidRange(0, 3));
        assertFalse(AssembleHelper.isValidRange(0, 4));
    }

    @Test
    @DisplayName("엔진 ValidRange Test")
    public void testIsValidRange_engine() {
        assertTrue(AssembleHelper.isValidRange(1, 0)); // 뒤로가기
        assertTrue(AssembleHelper.isValidRange(1, 1));
        assertTrue(AssembleHelper.isValidRange(1, 4));
        assertFalse(AssembleHelper.isValidRange(1, 5));
        assertFalse(AssembleHelper.isValidRange(1, -1));

    }


    @Test
    @DisplayName("brake ValidRange Test")
    public void testIsValidRange_Brake() {
        assertTrue(AssembleHelper.isValidRange(2, 0)); // 뒤로가기
        assertTrue(AssembleHelper.isValidRange(2, 1));
        assertTrue(AssembleHelper.isValidRange(2, 2));
        assertTrue(AssembleHelper.isValidRange(2, 3));
    }

    @Test
    @DisplayName("brake ValidRange Test Invalid")
    public void testIsValidRange_Brake_InvalidRange() {
        assertFalse(AssembleHelper.isValidRange(2, -1));
        assertFalse(AssembleHelper.isValidRange(2, 4));
        assertFalse(AssembleHelper.isValidRange(2, 999));
    }

    @Test
    @DisplayName("Steering ValidRange Test")
    public void testIsValidRange_Steering() {
        assertTrue(AssembleHelper.isValidRange(3, 0)); // 뒤로가기
        assertTrue(AssembleHelper.isValidRange(3, 1));
        assertTrue(AssembleHelper.isValidRange(3, 2));
    }

    @Test
    @DisplayName("Steering ValidRange Test Invalid")
    public void testIsValidRange_Steering_Invalid() {
        assertFalse(AssembleHelper.isValidRange(3, -1));
        assertFalse(AssembleHelper.isValidRange(3, 3));
        assertFalse(AssembleHelper.isValidRange(3, 999));
    }

    @Test
    @DisplayName("run, test ValidRange Test")
    public void testIsValidRange_run_test() {
        assertTrue(AssembleHelper.isValidRange(4, 0));
        assertTrue(AssembleHelper.isValidRange(4, 1));
        assertTrue(AssembleHelper.isValidRange(4, 2));
        assertFalse(AssembleHelper.isValidRange(4, 3));
        assertFalse(AssembleHelper.isValidRange(4, -1));
    }

    @Test
    @DisplayName("backStep Test")
    public void testUpdateBackStep() {
        assertEquals(0, AssembleHelper.updateBackStep(1));
        assertEquals(1, AssembleHelper.updateBackStep(2));
        assertEquals(2, AssembleHelper.updateBackStep(3));
        assertEquals(0, AssembleHelper.updateBackStep(4));
        assertEquals(0, AssembleHelper.updateBackStep(0));
    }

    @Test
    @DisplayName("cartype 화면 수행")
    public void testShowCarParts_CarTypeMenu() {
        AssembleHelper helper = new AssembleHelper();
        helper.showCarParts(0);
    }

    @Test
    @DisplayName("선택 display 정상 수행")
    public void testShowCarParts_AllMenus() {
        AssembleHelper helper = new AssembleHelper();

        assertDoesNotThrow(() -> helper.showCarParts(0));
        assertDoesNotThrow(() -> helper.showCarParts(1));
        assertDoesNotThrow(() -> helper.showCarParts(2));
        assertDoesNotThrow(() -> helper.showCarParts(3));
        assertDoesNotThrow(() -> helper.showCarParts(4));
    }

    @Test
    @DisplayName("화면 clear 정상 수행")
    public void testClearDisplay() {
        AssembleHelper helper = new AssembleHelper();

        assertDoesNotThrow(() -> helper.clearDisplay());
    }

    @Test
    @DisplayName("Input, 프롬프트 정상 동작")
    public void testGetChooseNumber_NormalInput() {
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        String result = AssembleHelper.getChooseNumber(scanner);
        assertEquals("1", result);

        String output = outputStream.toString();
        assertTrue(output.contains("INPUT >"));
    }

    @Test
    @DisplayName("잘놋된 단계에서 error 발생")
    public void testIsValidRange_ErrorMessages() {
        outputStream.reset();
        AssembleHelper.isValidRange(7, 1);
        assertTrue(outputStream.toString().contains("ERROR :: 잘못된 step입니다"));
    }


}
