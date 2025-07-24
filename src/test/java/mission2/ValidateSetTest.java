package mission2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
        Object[][] combinationRules = {
                { stack[Engine_Q] == 4,"엔진이 고장나있습니다."},
                { stack[CarType_Q] == SEDAN && stack[BrakeSystem_Q] == CONTINENTAL, "Sedan에는 Continental 제동장치 사용 불가" },
                { stack[CarType_Q] == SUV && stack[Engine_Q] == TOYOTA, "SUV에는 TOYOTA 엔진 사용 불가" },
                { stack[CarType_Q] == TRUCK && stack[Engine_Q] == WIA, "Truck에는 WIA 엔진 사용 불가" },
                { stack[CarType_Q] == TRUCK && stack[BrakeSystem_Q] == MANDO, "Truck에는 Mando 제동장치 사용 불가" },
                { stack[BrakeSystem_Q] == BOSCH_B && stack[SteeringSystem_Q] != BOSCH_S, "Bosch 제동장치에는 Bosch 조향장치 이외 사용 불가" }
        };
 */
public class ValidateSetTest {

    @Test
    @DisplayName("고장난 엔진 검증")
    public void testBrokenEngineRule() {
        ValidateSet rule = new ValidateSet(0, 4, 0, 0, "엔진이 고장나있습니다.");

        assertTrue(rule.isViolated(1, 4, 1, 1));
        //정상 케이스
        assertFalse(rule.isViolated(1, 1, 1, 1));
        assertFalse(rule.isViolated(1, 2, 1, 1));
        assertFalse(rule.isViolated(1, 3, 1, 1));
    }

    @Test
    @DisplayName("Sedan에는 Continental 제동장치 사용 불가")
    public void testSedanContinentalRule() {
        ValidateSet rule = new ValidateSet(1, 0, 2, 0, "Sedan에는 Continental 제동장치 사용 불가");
        assertTrue(rule.isViolated(1, 1, 2, 1));

    }

    @Test
    @DisplayName("Bosch 제동장치에는 Bosch 조향장치 이외 사용 불가")
    public void testBoschBrakeSteeringRule() {
        ValidateSet rule = new ValidateSet(0, 0, 3, -1, "Bosch 제동장치에는 Bosch 조향장치 이외 사용 불가");
        assertTrue(rule.isViolated(1, 1, 3, 2));

    }

    @Test
    @DisplayName("error msg 동작 검증")
    public void testErrorMessage() {
        ValidateSet rule = new ValidateSet(1, 0, 2, 0, "테스트 에러 메시지");
        assertEquals("테스트 에러 메시지", rule.getErrorMessage());
    }


}