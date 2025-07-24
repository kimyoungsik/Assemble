package mission2;

public class Steering {
    private static final int BOSCH_S = 1, MOBIS = 2;

    static Steering instance = null;
    private Steering(){}
    static Steering getInstance(){  //싱글톤이구나
        if(instance ==null) instance = new Steering(); //없으면 생성
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
