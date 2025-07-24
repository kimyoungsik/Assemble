package mission2;

import java.util.*;

public class CarPartSetting {

    public static final Map<Integer, String> CAR_TYPES = new HashMap<Integer, String>() {{
        put(1, "Sedan");
        put(2, "SUV");
        put(3, "Truck");
    }};

    public static final Map<Integer, String> ENGINES = new HashMap<Integer, String>() {{
        put(1, "GM");
        put(2, "TOYOTA");
        put(3, "WIA");
        put(4, "고장난 엔진");
    }};

    public static final Map<Integer, String> BRAKES = new HashMap<Integer, String>() {{
        put(1, "Mando");
        put(2, "Continental");
        put(3, "Bosch");
    }};

    public static final Map<Integer, String> STEERINGS = new HashMap<Integer, String>() {{
        put(1, "Bosch");
        put(2, "Mobis");
    }};


    public static final List<ValidateSet> COMPATIBILITY_RULES = Arrays.asList(
            new ValidateSet(0, 4, 0, 0, "엔진이 고장나있습니다."),
            new ValidateSet(1, 0, 2, 0, "Sedan에는 Continental 제동장치 사용 불가"),
            new ValidateSet(2, 2, 0, 0, "SUV에는 TOYOTA 엔진 사용 불가"),
            new ValidateSet(3, 3, 0, 0, "Truck에는 WIA 엔진 사용 불가"),
            new ValidateSet(3, 0, 1, 0, "Truck에는 Mando 제동장치 사용 불가"),
            new ValidateSet(0, 0, 3, -1, "Bosch 제동장치에는 Bosch 조향장치 이외 사용 불가")

    );

    public static boolean isValidCarType(int id) { return CAR_TYPES.containsKey(id); }
    public static boolean isValidEngine(int id) { return ENGINES.containsKey(id); }
    public static boolean isValidBrake(int id) { return BRAKES.containsKey(id); }
    public static boolean isValidSteering(int id) { return STEERINGS.containsKey(id); }

    public static int getMaxCarTypeId() { return Collections.max(CAR_TYPES.keySet()); }
    public static int getMaxEngineId() { return Collections.max(ENGINES.keySet()); }
    public static int getMaxBrakeId() { return Collections.max(BRAKES.keySet()); }
    public static int getMaxSteeringId() { return Collections.max(STEERINGS.keySet()); }
}