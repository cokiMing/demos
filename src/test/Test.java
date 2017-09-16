package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by feng on 2017/4/25.
 */
public class Test {


    public static void main(String args[]) throws Exception{
        List<String> dateList = new ArrayList<>();
        dateList.add("aaa");
        dateList.add("bbb");
        dateList.add("ccc");
        dateList.add("adda");

        List<String> list = CopyUtil.deepCopy(dateList);
        list.remove("aaa");

        System.out.println(dateList);
        System.out.println(list);
    }
}
