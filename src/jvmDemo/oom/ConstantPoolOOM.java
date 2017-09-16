package jvmDemo.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:PermSize=1M
 * -XX:MaxPermSize=1M
 * -XX:+PrintGC
 * Created by wuyiming on 2017/8/16.
 */
public class ConstantPoolOOM {

    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        int i = 1;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
