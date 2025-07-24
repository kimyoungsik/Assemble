package mission2;

public class Menu {

    public void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        for (int i = 1; i <= CarPartSetting.getMaxCarTypeId(); i++) {
            String name = CarPartSetting.CAR_TYPES.get(i);
            if (name != null) {
                System.out.println(i + ". " + name);
            }
        }
        System.out.println("===============================");
    }

    public void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        for (int i = 1; i <= CarPartSetting.getMaxEngineId(); i++) {
            String name = CarPartSetting.ENGINES.get(i);
            if (name != null) {
                System.out.println(i + ". " + name);
            }
        }
        System.out.println("===============================");
    }

    public void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        for (int i = 1; i <= CarPartSetting.getMaxBrakeId(); i++) {
            String name = CarPartSetting.BRAKES.get(i);
            if (name != null) {
                System.out.println(i + ". " + name);
            }
        }
        System.out.println("===============================");
    }

    public void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    public void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }


}
