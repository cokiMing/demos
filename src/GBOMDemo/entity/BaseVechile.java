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

    public BaseVechile(Builder builder) {
        GVW = builder._GVW;
        CVW = builder._CVW;
        engine = builder._engine;
        color = builder._color;
        wheel = builder._wheel;
        wheelBase = builder._wheelBase;
        country = builder._country;
        displacement = builder._displacement;
        outerRearview = builder._outerRearview;
        innerRearview = builder._innerRearview;
        brand = builder._brand;
        glassColor = builder._glassColor;
        seatNum = builder._seatNum;
        CopilotSeatNum = builder._CopilotSeatNum;
        rudder = builder._rudder;
        airBag = builder._airBag;
        gearBox = builder._gearBox;
        effluent = builder._effluent;
        innerColor = builder._innerColor;
        length = builder._length;
        width = builder._width;
        height = builder._height;
        electricStep = builder._electricStep;
        sideDoorModel = builder._sideDoorModel;
    }

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

    public static class Builder {
        private Integer _GVW;
        private Integer _CVW;
        private String _engine;
        private String _color;
        private String _wheel;
        private Integer _wheelBase;
        private String _country;
        private Double _displacement;
        private String _outerRearview;
        private String _innerRearview;
        private String _brand;
        private String _glassColor;
        private Integer _seatNum;
        private Integer _CopilotSeatNum;
        private String _rudder;
        private String _airBag;
        private String _gearBox;
        private String _effluent;
        private String _innerColor;
        private Integer _length;
        private Integer _width;
        private Integer _height;
        private String _electricStep;
        private String _sideDoorModel;

        public Builder() {
        }

        public Builder GVW(Integer GVW) {
            _GVW = GVW;
            return this;
        }

        public Builder CVW(Integer CVW) {
            _CVW = CVW;
            return this;
        }

        public Builder engine(String engine) {
            _engine = engine;
            return this;
        }

        public Builder color(String color) {
            _color = color;
            return this;
        }

        public Builder wheel(String wheel) {
            _wheel = wheel;
            return this;
        }

        public Builder wheelBase(Integer wheelBase) {
            _wheelBase = wheelBase;
            return this;
        }

        public Builder country(String country) {
            _country = country;
            return this;
        }

        public Builder displacement(Double displacement) {
            _displacement = displacement;
            return this;
        }

        public Builder outerRearview(String outerRearview) {
            _outerRearview = outerRearview;
            return this;
        }

        public Builder innerRearview(String innerRearview) {
            _innerRearview = innerRearview;
            return this;
        }

        public Builder brand(String brand) {
            _brand = brand;
            return this;
        }

        public Builder glassColor(String glassColor) {
            _glassColor = glassColor;
            return this;
        }

        public Builder seatNum(Integer seatNum) {
            _seatNum = seatNum;
            return this;
        }

        public Builder CopilotSeatNum(Integer CopilotSeatNum) {
            _CopilotSeatNum = CopilotSeatNum;
            return this;
        }

        public Builder rudder(String rudder) {
            _rudder = rudder;
            return this;
        }

        public Builder airBag(String airBag) {
            _airBag = airBag;
            return this;
        }

        public Builder gearBox(String gearBox) {
            _gearBox = gearBox;
            return this;
        }

        public Builder effluent(String effluent) {
            _effluent = effluent;
            return this;
        }

        public Builder innerColor(String innerColor) {
            _innerColor = innerColor;
            return this;
        }

        public Builder length(Integer length) {
            _length = length;
            return this;
        }

        public Builder width(Integer width) {
            _width = width;
            return this;
        }

        public Builder height(Integer height) {
            _height = height;
            return this;
        }

        public Builder electricStep(String electricStep) {
            _electricStep = electricStep;
            return this;
        }

        public Builder sideDoorModel(String sideDoorModel) {
            _sideDoorModel = sideDoorModel;
            return this;
        }

        public BaseVechile builder() {
            return new BaseVechile(this);
        }
    }
}
