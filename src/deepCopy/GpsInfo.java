package deepCopy;

import java.util.List;

/**
 * Created by wuyiming on 2017/7/26.
 */
public class GpsInfo {
    private double altitude;
    private double lat;
    private double lng;
    private List<String> peoples;

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<String> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<String> peoples) {
        this.peoples = peoples;
    }
}
