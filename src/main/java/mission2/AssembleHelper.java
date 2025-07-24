package mission2;

import java.util.Scanner;

public class AssembleHelper {

    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final int CarType_Q      = 0;
    private static final int Engine_Q       = 1;
    private static final int BrakeSystem_Q  = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int Run_Test       = 4;


    Menu menu;

    public AssembleHelper() {
        menu = new Menu();
    }

    public void clearDisplay() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    void showCarParts(int step) {
        switch (step) {
            case CarType_Q:
                this.menu.showCarTypeMenu(); break;
            case Engine_Q:
                menu.showEngineMenu(); break;
            case BrakeSystem_Q:
                menu.showBrakeMenu(); break;
            case SteeringSystem_Q:
                menu.showSteeringMenu(); break;
            case Run_Test:
                menu.showRunTestMenu(); break;
        }
    }

    static String getChooseNumber(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    static boolean isExit(String choosedValue) {
        if (choosedValue.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    static Integer updateToNumber(String choosedValue) {
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
    static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CarType_Q:
                if (ans < 1 || !CarPartSetting.isValidCarType(ans)) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ " + CarPartSetting.getMaxCarTypeId() + " 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_Q:
                if (ans < 0 || (ans > 0 && !CarPartSetting.isValidEngine(ans))) {
                    System.out.println("ERROR :: 엔진은 1 ~ " + CarPartSetting.getMaxEngineId() + " 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_Q:
                if (ans < 0 || (ans > 0 && !CarPartSetting.isValidBrake(ans))) {
                    System.out.println("ERROR :: 제동장치는 1 ~ " + CarPartSetting.getMaxBrakeId() + " 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_Q:
                if (ans < 0 || (ans > 0 && !CarPartSetting.isValidSteering(ans))) {
                    System.out.println("ERROR :: 조향장치는 1 ~ " + CarPartSetting.getMaxSteeringId() + " 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
            default:
                System.out.println("ERROR :: 잘못된 step입니다.");
                return false;
        }
        return true;
    }


    static int updateBackStep(int step) {
        if (step == Run_Test) {
            step = CarType_Q;
        } else if (step > CarType_Q) {
            step--;
        }
        return step;
    }



}
