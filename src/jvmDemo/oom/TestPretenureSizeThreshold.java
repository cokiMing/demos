package jvmDemo.oom;

/**
 * Created by wuyiming on 2017/8/16.
 */
public class TestPretenureSizeThreshold {

    private static int _1MB = 1024 * 1024;

    /**
     * -verbose:gc
     * -Xms20M
     * -Xmx20M
     * -Xmn10M
     * -XX:+UseSerialGC
     * -XX:SurvivorRatio=8
     * -XX:+PrintGCDetails
     * -XX:PretenureSizeThreshold=3145728
     * @param args
     */
    public static void main(String[] args){
        byte[] allocation = new byte[4 * _1MB];
    }
}
