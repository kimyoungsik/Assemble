package mission2;
import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_Q      = 0;
    private static final int Engine_Q       = 1;
    private static final int BrakeSystem_Q  = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int Run_Test       = 4;


    private static int[] stack = new int[5];

    static CarType carType;
    static Engine engine;
    static Brake brake;
    static Steering steering;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AssembleHelper assembleHelper = new AssembleHelper();

        int step = CarType_Q;

        while (true) {
            assembleHelper.clearDisplay();

            assembleHelper.showCarParts(step);

            String choosed = assembleHelper.getChooseNumber(sc);

            if (assembleHelper.isExit(choosed)) break;

            Integer choosedNumber = assembleHelper.updateToNumber(choosed);
            if (choosedNumber == null) continue;

            if (assembleHelper.isValidRange(step, choosedNumber)) {
            } else {
                assembleHelper.delay(800);
                continue;
            }
            if (choosedNumber == 0) {
                step = assembleHelper.updateBackStep(step);
                continue;
            }

            step = updateAssemble(step, choosedNumber);
        }
        sc.close();
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

    private static void selectCarType(int chooseNumber) {
        carType = CarPartFactory.createCarType(chooseNumber);
        displaySelection( "차량 타입으로", carType.getCarTypeName()+"을");
    }

    private static void selectEngine(int chooseNumber) {
        engine = CarPartFactory.createEngine(chooseNumber);
        displaySelection(engine.getEngineName(), "엔진을");
    }

    private static void selectBrakeSystem(int chooseNumber) {
        brake =CarPartFactory.createBrake(chooseNumber);
        displaySelection(brake.getBrakeName(),"제동장치를");
    }

    private static void selectSteeringSystem(int chooseNumber) {
        steering = CarPartFactory.createSteering(chooseNumber);
        displaySelection(steering.getSteeringName(),"조향장치를");
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
        System.out.printf("Car Type : %s\n", carType.getCarTypeName());
        System.out.printf("Engine   : %s\n", engine.getEngineName());
        System.out.printf("Brake    : %s\n", brake.getBrakeName());
        System.out.printf("Steering : %s\n", steering.getSteeringName());
    }

    private static boolean carPartCombinationTest() {
        for (ValidateSet rule : CarPartSetting.COMPATIBILITY_RULES) {
            if (rule.isViolated(carType.getCarTypeId(), engine.getEngineId(),
                    brake.getBrakeId(), steering.getSteeringId())) {
                displayFail(rule.getErrorMessage());
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