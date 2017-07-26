package deepCopy;

/**
 * Created by wuyiming on 2017/7/26.
 */
public class Address {
    private String addressName;
    private GpsInfo gpsInfo;
    private long id;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public GpsInfo getGpsInfo() {
        return gpsInfo;
    }

    public void setGpsInfo(GpsInfo gpsInfo) {
        this.gpsInfo = gpsInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
