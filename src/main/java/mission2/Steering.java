package mission2;

public class Steering {

    static Steering instance = null;

    private Steering() {
    }

    static Steering getInstance() {
        if (instance == null) instance = new Steering();
        return instance;
    }


    private int steeringId;

    public int getSteeringId() {
        return steeringId;
    }

    public void setSteeringId(int steeringId) {
        this.steeringId = steeringId;
    }

    public String getSteeringName() {
        String name = CarPartSetting.STEERINGS.get(steeringId);
        return name != null ? name : "알 수 없는 Steering 시스템";
    }

}
