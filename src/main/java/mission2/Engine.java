package mission2;

public class Engine {
    static Engine instance = null;

    private Engine() {
    }

    static Engine getInstance() {
        if (instance == null) instance = new Engine();
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
