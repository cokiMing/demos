package test;

import java.util.*;

/**
 * Created by chen on 2017/5/9.
 */
public class Test4 {

    private static Map<Integer,String> A = new HashMap<Integer,String>(){
        {
            put(0,"red");
            put(1,"red");
        }
    };
    private static Map<Integer,String> B = new HashMap<Integer,String>(){
        {
            put(0,"red");
            put(1,"blue");
        }
    };
    private static Map<Integer,String> C = new HashMap<Integer,String>(){
        {
            put(0,"blue");
            put(1,"blue");
        }
    };

    private static List<Map<Integer,String>> cardPool = new ArrayList<Map<Integer,String>>(){
        {
            add(A);
            add(B);
            add(C);
        }
    };



    public static void main(String[] args){
        int Acount = 0;
        int Bcount = 0;
        Random random = new Random();
        for(int i=0; i<10000; i++){
            int n = random.nextInt(3);
            Map<Integer, String> card = cardPool.get(n);
            int m = random.nextInt(2);
            if(card.get(m).equals("red")){
                int l = 1 - m;
                if(card.get(l).equals("red")){
                    Acount++;
                }else{
                    Bcount++;
                }
            }
        }

        System.out.print("A:"+Acount+";"+"B:"+Bcount);
    }


}
