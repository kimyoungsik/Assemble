package mission2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;

public class AssembleMainTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    @DisplayName("EXIT 입력")
    public void testExitCommand() {
        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assemble.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("바이바이"));
    }

    @Test
    @DisplayName("범위 벗어남 입력")
    public void testOutRangeInput() {
        String input = "10\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assemble.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능"));
        assertTrue(output.contains("바이바이"));
    }

    @Test
    @DisplayName("BACK STEP 입력")
    public void test_BackStep() {
        // 뒤로가기 기능 테스트
        String input = "1\n0\nexit\n"; // 차량 선택 후 뒤로가기
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Assemble.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Sedan을 선택하셨습니다"));
        assertTrue(output.contains("바이바이"));
    }


}