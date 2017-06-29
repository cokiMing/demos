package test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 2017/5/16.
 */
public class Test5 {
    //数字转换
    private static Map<Integer,String> numberMap = new HashMap<Integer,String>(){
        {
            put(0, "零");
            put(1, "壹");
            put(2, "贰");
            put(3, "叁");
            put(4, "肆");
            put(5, "伍");
            put(6, "陆");
            put(7, "柒");
            put(8, "捌");
            put(9, "玖");
        }
    };
    //单位转换
    private static Map<Integer,String> orderMap = new HashMap<Integer,String>(){
        {
            put(0, "");
            put(1, "拾");
            put(2, "佰");
            put(3, "仟");
            put(4, "萬");
            put(5,"拾");
            put(6,"佰");
            put(7,"仟");
            put(8,"亿");
            put(9,"拾");
            put(10,"佰");
            put(11,"仟");
            put(12,"萬");
            put(13,"亿");
        }
    };

    public static void main(String[] args){
        double num = 1428231.32;

        long start = System.currentTimeMillis();
        String numResult = formatNum(num);
        long end = System.currentTimeMillis();
        long spendTime = end - start;

        System.out.println("总计耗时:"+spendTime+"毫秒");
        System.out.print(numResult);
    }

    private static String formatNum(double num){
        String numStr = num + "";
        int dotIndex = numStr.indexOf(".");
        int length = numStr.length();
        StringBuilder IntegerBuilder = new StringBuilder();
        StringBuilder decimalBuilder = new StringBuilder();
        char[] IntegerArray;
        char[] decimalArray = new char[0];

        if(dotIndex != -1){
            IntegerArray = numStr.substring(0,dotIndex).toCharArray();
            decimalArray = numStr.substring(dotIndex+1,length).toCharArray();
        }else{
            IntegerArray = numStr.toCharArray();
        }

        for(int i = IntegerArray.length - 1; i >= 0; i--){
            int j = IntegerArray.length - 1 - i;
            int numValue = Integer.parseInt(IntegerArray[j]+"");
            IntegerBuilder.append(numberMap.get(numValue)+orderMap.get(i));
        }

        for(int i = 0; i < decimalArray.length; i++){
            int numValue = Integer.parseInt(decimalArray[i]+"");
            decimalBuilder.append(numberMap.get(numValue));
        }

        return  IntegerBuilder.append("点").append(decimalBuilder).append("圆").toString();
    }
}
