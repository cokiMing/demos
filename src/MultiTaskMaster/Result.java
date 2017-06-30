package MultiTaskMaster;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class Result {

    private String code;

    private String message;

    private Map<String,Object> content = new HashMap<>();

    public String getCode() {
        return code;
    }

    private Result(String code,String message){
        this.code = code;
        this.message = message;
    }

    public static Result fail(String message){
        return new Result("0",message);
    }

    public static Result success(){
        return new Result("1","成功");
    }

    public void put(Object content){
        this.content.put("content",content);
    }

    @Override
    public String toString() {
        return "{ \"code\":"+code+",\"content\":"+content+",\"message\":"+message+"}";
    }
}
