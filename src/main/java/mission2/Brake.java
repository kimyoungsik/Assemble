package mission2;

public class Brake {

    static Brake instance = null;

    private Brake() {
    }

    static Brake getInstance() {
        if (instance == null) instance = new Brake(); //없으면 생성
        return instance;
    }

    private int brakeId;

    public int getBrakeId() {
        return brakeId;
    }

    public void setBrakeId(int brakeId) {
        this.brakeId = brakeId;
    }

    public String getBrakeName() {
        String name = CarPartSetting.BRAKES.get(brakeId);
        return name != null ? name : "알 수 없는 제동장치";
    }


}
