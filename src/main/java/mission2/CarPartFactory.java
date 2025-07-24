package mission2;

public class CarPartFactory {
    public static CarType createCarType(int typeId) {
        CarType carType = CarType.getInstance();
        carType.setCarTypeId(typeId);
        return carType;
    }

    public static Engine createEngine(int engineId) {
        Engine engine = Engine.getInstance();
        engine.setEngineId(engineId);
        return engine;
    }

    public static Brake createBrake(int brakeId) {
        Brake brake = Brake.getInstance();
        brake.setBrakeId(brakeId);
        return brake;
    }

    public static Steering createSteering(int steeringId) {
        Steering steering = Steering.getInstance();
        steering.setSteeringId(steeringId);
        return steering;
    }
}
