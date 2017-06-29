package MultiTaskMaster;

/**
 * Created by wuyiming on 2017/6/29.
 */
public class Result {

    private String code;

    public String getCode() {
        return code;
    }

    private Result(String code){
        this.code = code;
    }

    public static Result fail(){
        return new Result("fail");
    }

    public static Result success(){
        return new Result("success");
    }
}
