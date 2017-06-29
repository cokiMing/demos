package GBOMDemo.entity;

/**
 * Created by wuyiming on 2017/6/23.
 */
public class BaseVechile {
    private Integer GVW;             //整车最大整备质量
    private Integer CVW;             //整车最大空载质量
    private String engine;           //发动机型号
    private String color;            //车身颜色
    private String wheel;            //车轮品牌
    private Integer wheelBase;       //轴距
    private String country;          //销售国家
    private Double displacement;     //排量
    private String outerRearview;    //外后视镜
    private String innerRearview;    //内后视镜
    private String brand;            //品牌
    private String glassColor;       //玻璃颜色
    private Integer seatNum;         //座位数量
    private Integer CopilotSeatNum;  //副驾座位数量
    private String rudder;           //左右舵 LEFT|RIGHT
    private String airBag;           //安全气囊
    private String gearBox;          //变速箱
    private String effluent;         //排放标准
    private String innerColor;       //内饰颜色
    private Integer length;          //车身长度
    private Integer width;           //车身宽度
    private Integer height;          //车身高度
    private String electricStep;     //电动踏步
    private String sideDoorModel;    //侧移门形式

    public Integer getGVW() {
        return GVW;
    }

    public void setGVW(Integer GVW) {
        this.GVW = GVW;
    }

    public Integer getCVW() {
        return CVW;
    }

    public void setCVW(Integer CVW) {
        this.CVW = CVW;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    public Integer getWheelBase() {
        return wheelBase;
    }

    public void setWheelBase(Integer wheelBase) {
        this.wheelBase = wheelBase;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Double displacement) {
        this.displacement = displacement;
    }

    public String getOuterRearview() {
        return outerRearview;
    }

    public void setOuterRearview(String outerRearview) {
        this.outerRearview = outerRearview;
    }

    public String getInnerRearview() {
        return innerRearview;
    }

    public void setInnerRearview(String innerRearview) {
        this.innerRearview = innerRearview;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGlassColor() {
        return glassColor;
    }

    public void setGlassColor(String glassColor) {
        this.glassColor = glassColor;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Integer getCopilotSeatNum() {
        return CopilotSeatNum;
    }

    public void setCopilotSeatNum(Integer copilotSeatNum) {
        CopilotSeatNum = copilotSeatNum;
    }

    public String getRudder() {
        return rudder;
    }

    public void setRudder(String rudder) {
        this.rudder = rudder;
    }

    public String getAirBag() {
        return airBag;
    }

    public void setAirBag(String airBag) {
        this.airBag = airBag;
    }

    public String getGearBox() {
        return gearBox;
    }

    public void setGearBox(String gearBox) {
        this.gearBox = gearBox;
    }

    public String getEffluent() {
        return effluent;
    }

    public void setEffluent(String effluent) {
        this.effluent = effluent;
    }

    public String getInnerColor() {
        return innerColor;
    }

    public void setInnerColor(String innerColor) {
        this.innerColor = innerColor;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getElectricStep() {
        return electricStep;
    }

    public void setElectricStep(String electricStep) {
        this.electricStep = electricStep;
    }

    public String getSideDoorModel() {
        return sideDoorModel;
    }

    public void setSideDoorModel(String sideDoorModel) {
        this.sideDoorModel = sideDoorModel;
    }
}
