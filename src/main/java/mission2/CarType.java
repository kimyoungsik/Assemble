package mission2;

public class CarType {

    static CarType instance = null;
    private CarType(){}
    static CarType getInstance(){  //싱글톤이구나
        if(instance ==null) instance = new CarType(); //없으면 생성
        return instance;
    }

    private int carTypeId;

    public int getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarTypeName() {
        String name = CarPartSetting.CAR_TYPES.get(carTypeId);
        return name != null ? name : "알 수 없는 차종";
    }


}
