package jvmDemo.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyiming on 2017/8/17.
 */
public class OOMConsole {

    /**
     * 内存占位符对象，单位64KB
     */
    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception{
        fillHeap(1000);
    }
}
