package mission1;
import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_Q      = 0;
    private static final int Engine_Q       = 1;
    private static final int BrakeSystem_Q  = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int Run_Test       = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;

    private static int[] stack = new int[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int step = CarType_Q;

        while (true) {
            clearDisplay();

            //화면 출력
            showCarParts(step);

            //입력 받기
            String choosed = getChooseNumber(sc);

            //종료 조건
            if (isExit(choosed)) break;

            //숫자 변환
            Integer choosedNumber = updateToNumber(choosed);
            if (choosedNumber == null) continue;

            //범위 체크
            if (isValidRange(step, choosedNumber)) {
            } else {
                delay(800);
                continue;
            }

            //뒤로가기
            if (choosedNumber == 0) {
                step = updateBackStep(step);
                continue;
            }

            //선택
            step = updateAssemble(step, choosedNumber);
        }

        sc.close();
    }

    private static void clearDisplay() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    private static void showCarParts(int step) {

        switch (step) {
            case CarType_Q:
                showCarTypeMenu(); break;
            case Engine_Q:
                showEngineMenu(); break;
            case BrakeSystem_Q:
                showBrakeMenu(); break;
            case SteeringSystem_Q:
                showSteeringMenu(); break;
            case Run_Test:
                showRunTestMenu(); break;
        }

    }

    private static int updateAssemble(int step, Integer chooseNumber) {
        switch (step) {
            case CarType_Q:
                selectCarType(chooseNumber);
                delay(800);
                step = Engine_Q;
                break;
            case Engine_Q:
                selectEngine(chooseNumber);
                delay(800);
                step = BrakeSystem_Q;
                break;
            case BrakeSystem_Q:
                selectBrakeSystem(chooseNumber);
                delay(800);
                step = SteeringSystem_Q;
                break;
            case SteeringSystem_Q:
                selectSteeringSystem(chooseNumber);
                delay(800);
                step = Run_Test;
                break;
            case Run_Test:
                if (chooseNumber == 1) {
                    runProducedCar();
                    delay(2000);
                } else if (chooseNumber == 2) {
                    System.out.println("Test...");
                    delay(1500);
                    carPartCombinationTest();
                    delay(2000);
                }
                break;
        }
        return step;
    }

    private static int updateBackStep(int step) {
        if (step == Run_Test) {
            step = CarType_Q;
        } else if (step > CarType_Q) {
            step--;
        }
        return step;
    }

    private static Integer updateToNumber(String choosedValue) {
        int convertedNumber;
        try {
            convertedNumber = Integer.parseInt(choosedValue);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delay(800);
            return null;
        }
        return convertedNumber;
    }

    private static boolean isExit(String choosedValue) {
        if (choosedValue.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    private static String getChooseNumber(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }
    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }
    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }
    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }
    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }


/*
    private static boolean isValidRange2(int step, int ans) {
        switch (step) {
            case CarType_Q:
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_Q:
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_Q:
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_Q:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }*/

    private static final int[] MIN_VALUES = {1, 0, 0, 0, 0};
    private static final int[] MAX_VALUES = {3, 4, 3, 2, 2};

    private static final String[] ERROR_MESSAGES = {
            "ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능",
            "ERROR :: 엔진은 1 ~ 4 범위만 선택 가능",
            "ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능",
            "ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능",
            "ERROR :: Run 또는 Test 중 하나를 선택 필요"
    };

    private static boolean isValidRange(int step, int ans) {
        if (step < 0 || step >= MIN_VALUES.length) {
            System.out.println("ERROR :: 잘못된 step입니다.");
            return false;
        }

        int min = MIN_VALUES[step];
        int max = MAX_VALUES[step];

        if (ans < min || ans > max) {
            System.out.println(ERROR_MESSAGES[step]);
            return false;
        }

        return true;
    }

/*
    private static void selectCarType(int chooseNumber) {
        stack[CarType_Q] = chooseNumber;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", chooseNumber == 1 ? "Sedan" : chooseNumber == 2 ? "SUV" : "Truck");
    }
    private static void selectEngine(int chooseNumber) {
        stack[Engine_Q] = chooseNumber;
        String name = chooseNumber == 1 ? "GM" : chooseNumber == 2 ? "TOYOTA" : chooseNumber == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
    private static void selectBrakeSystem(int chooseNumber) {
        stack[BrakeSystem_Q] = chooseNumber;
        String name = chooseNumber == 1 ? "MANDO" : chooseNumber == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
    private static void selectSteeringSystem(int chooseNumber) {
        stack[SteeringSystem_Q] = chooseNumber;
        String name = chooseNumber == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }
*/

    private static void selectCarType(int chooseNumber) {
        stack[CarType_Q] = chooseNumber;
        String[] types = { "Sedan", "SUV", "Truck" };
        displaySelection( "차량 타입으로", types[chooseNumber-1]+"을");
    }

    private static void selectEngine(int chooseNumber) {
        stack[Engine_Q] = chooseNumber;
        String[] engines = { "GM", "TOYOTA", "WIA", "고장난엔진" };
        displaySelection(engines[chooseNumber-1], "엔진을");
    }

    private static void selectBrakeSystem(int chooseNumber) {
        stack[BrakeSystem_Q] = chooseNumber;
        String[] brakes = { "MANDO", "CONTINENTAL", "BOSCH" };
        displaySelection(brakes[chooseNumber-1],"제동장치를");
    }

    private static void selectSteeringSystem(int chooseNumber) {
        stack[SteeringSystem_Q] = chooseNumber;
        String[] steerings = { "BOSCH", "MOBIS" };
        displaySelection(steerings[chooseNumber-1],"조향장치를");
    }

    private static void displaySelection(String firstString, String secondString) {
        System.out.printf("%s %s 선택하셨습니다.\n", firstString, secondString);

    }

    private static void runProducedCar() {
        if (!carPartCombinationTest()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        carAssembleList();
        System.out.println("자동차가 동작됩니다.");
    }

    private static void carAssembleList() {
        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[stack[CarType_Q]]);
        System.out.printf("Engine   : %s\n", engNames[stack[Engine_Q]]);
        System.out.printf("Brake    : %s\n",
                stack[BrakeSystem_Q]==1? "Mando":
                        stack[BrakeSystem_Q]==2? "Continental":"Bosch");
        System.out.printf("Steering : %s\n",
                stack[SteeringSystem_Q]==1? "Bosch":"Mobis");
    }

    private static boolean carPartCombinationTest() {
        Object[][] combinationRules = {
                { stack[Engine_Q] == 4,"엔진이 고장나있습니다."},
                { stack[CarType_Q] == SEDAN && stack[BrakeSystem_Q] == CONTINENTAL, "Sedan에는 Continental 제동장치 사용 불가" },
                { stack[CarType_Q] == SUV && stack[Engine_Q] == TOYOTA, "SUV에는 TOYOTA 엔진 사용 불가" },
                { stack[CarType_Q] == TRUCK && stack[Engine_Q] == WIA, "Truck에는 WIA 엔진 사용 불가" },
                { stack[CarType_Q] == TRUCK && stack[BrakeSystem_Q] == MANDO, "Truck에는 Mando 제동장치 사용 불가" },
                { stack[BrakeSystem_Q] == BOSCH_B && stack[SteeringSystem_Q] != BOSCH_S, "Bosch 제동장치에는 Bosch 조향장치 이외 사용 불가" }
        };

        for (Object[] rule : combinationRules) {
            boolean breakRule = (Boolean) rule[0];
            String errorMessage = (String) rule[1];
            if (breakRule) {
                displayFail(errorMessage);
                return false;
            }
        }
        displayPass();
        return true;
    }

    private static void displayPass() {
        System.out.println("자동차 부품 조합 테스트 결과 : PASS");
    }

    private static void displayFail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}