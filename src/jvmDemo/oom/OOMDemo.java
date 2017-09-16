package jvmDemo.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by wuyiming on 2017/8/16.
 */
public class OOMDemo {

    static class OOMObject{

    }

    public static void main(String[] args){
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }

}
