package wechat.app.constant;

/**
 * 前端返回信息枚举
 */
public enum ConstantEnum {

    FAIL("处理失败",500),

    SUCCESS("处理成功",200);

    private String message;

    private int code;

    ConstantEnum(String message,int code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
