package mission2;

public class Engine {
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    static Engine instance = null;
    private Engine(){}
    static Engine getInstance(){  //싱글톤이구나
        if(instance ==null) instance = new Engine(); //없으면 생성
        return instance;
    }
    private int engineId;

    public int getEngineId() {
        return engineId;
    }

    public void setEngineId(int engineId) {
        this.engineId = engineId;
    }
    public String getEngineName() {
        String name = CarPartSetting.ENGINES.get(engineId);
        return name != null ? name : "알 수 없는 엔진";
    }
}
