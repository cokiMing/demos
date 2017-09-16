package xiaoxiaole;

import java.util.*;

/**
 * Created by wuyiming on 2017/9/13.
 */
public class XiaoxiaoleDemo {

    private final static int COMMON_POINTS = 100;

    private final static int SINGLE_POINTS = 100;

    private final static int DOUBLE_POINTS = 200;

    private final static int SENIOR_POINTS = 300;

    private static List<String> stringList = new ArrayList<>();

    private enum CLEAN_TYPE {
        NONE(0,"无状态"),
        SINGLE(1,"单个消除"),
        COMMON(2,"普通消除"),
        DOUBLE(3,"连续消除"),
        SENIOR(4,"三个及以上消除");

        int code;
        String name;

        CLEAN_TYPE(int code,String name){
            this.code = code;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        String array = "1233132211";
        handleArray(array,array);
        Map<String,Integer> map = new HashMap<>();

        for (String str:stringList) {
            List<Character> characterList = str2CList(str);
            int points = calculatePoints(characterList, CLEAN_TYPE.SINGLE);
            points += (array.length() - str.length())*SINGLE_POINTS;
            map.put(str,points);
        }

        int max = 0;
        String solve = "";
        for (Map.Entry<String,Integer> entry: map.entrySet()) {
            Integer value = entry.getValue();
            if (value >= max) {
                max = value;
                solve = entry.getKey();
            }
        }

        System.out.println(solve + ": " + max);

//        System.out.println(calculatePoints(str2CList(array),CLEAN_TYPE.SINGLE));
    }

    public static void handleArray(String array,String backup) {
        char[] chars = array.toCharArray();
        for (char c: chars) {
            String next = array.substring(array.indexOf(c)+1);
            String plan = selectOne(backup, backup.indexOf(c));
            stringList.add(plan);
            handleArray(next,plan);
        }
    }

    public static String selectOne(String str, int index) {
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i ++) {
            if (i != index){
                stringBuilder.append(chars[i]);
            }
        }

        return stringBuilder.toString();
    }

    public static List<Character> str2CList(String str) {
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i ++) {
            list.add(chars[i]);
        }
        return list;
    }

    /**
     * 消除一个后计算分数
     * @param chars
     * @param last
     * @return
     */
    public static int calculatePoints(List<Character> chars,CLEAN_TYPE last) {
        if (chars.size() == 0) {
            return 0;
        }

        int total = 0;
        char type = chars.get(0);
        CLEAN_TYPE thisRound = last;
        List<List<Character>> characters = new ArrayList<>();
        List<Character> characterList = new ArrayList<>();
        List<Character> result = new ArrayList<>();

        for (Character c: chars) {
            if (c.equals(type)) {
                characterList.add(c);
            } else {
                characters.add(characterList);
                characterList = new ArrayList<>();
                characterList.add(c);
                type = c;
            }
        }
        characters.add(characterList);

        for (List<Character> list : characters) {
            if (list.size() >= 3) {
                switch (last) {
                    case NONE: total += COMMON_POINTS * list.size();break;
                    case SINGLE: total += COMMON_POINTS * list.size();thisRound=CLEAN_TYPE.COMMON;break;
                    case COMMON: total += DOUBLE_POINTS * list.size();thisRound=CLEAN_TYPE.DOUBLE;break;
                    case DOUBLE: total += SENIOR_POINTS * list.size();thisRound=CLEAN_TYPE.SENIOR;break;
                    case SENIOR: total += SENIOR_POINTS * list.size();thisRound=CLEAN_TYPE.SENIOR;break;
                }
            } else {
                result.addAll(list);
            }
        }

        if (result.size() == chars.size()) {
            return total;
        }

        return total + calculatePoints(result,thisRound);
    }
}
