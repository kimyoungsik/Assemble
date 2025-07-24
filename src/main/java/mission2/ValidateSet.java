package mission2;


public class ValidateSet {
    private int carTypeId;
    private int engineId;
    private int brakeId;
    private int steeringId;
    private String errorMessage;

    public ValidateSet(int carTypeId, int engineId, int brakeId, int steeringId, String errorMessage) {
        this.carTypeId = carTypeId;
        this.engineId = engineId;
        this.brakeId = brakeId;
        this.steeringId = steeringId;
        this.errorMessage = errorMessage;
    }

    public boolean isViolated(int carType, int engine, int brake, int steering) {

        if (carTypeId != 0 && carType != carTypeId) return false;
        if (engineId != 0 && engine != engineId) return false;
        if (brakeId != 0 && brake != brakeId) return false;
        if (steeringId == -1) {
            return steering != 1;
        }
        return true;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}