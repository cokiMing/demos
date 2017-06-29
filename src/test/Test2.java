package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chen on 2017/5/5.
 */
public class Test2 {

    public static void main(String[] args)throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date1 = dateFormat.parse("2017-05-05T05:07");
        Date date2 = dateFormat.parse("2017-05-05T12:07");
        Date date3 = dateFormat.parse("2017-05-05T13:07");
        long parse = date1.getTime();

        System.out.println("转化后的时间:"+date1);
        System.out.println("转化后的时间:"+date2);
        System.out.println("转化后的时间:"+date3);
        System.out.println("毫秒数:"+parse);

        long distance = 1*60*1000;

        Date date = new Date(parse - distance);
        System.out.println("作差:"+date);

        Date date4 = new Date(System.currentTimeMillis());
        System.out.println("当前时间为:"+date4);
    }
}
