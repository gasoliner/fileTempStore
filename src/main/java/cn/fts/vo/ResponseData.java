package cn.fts.vo;

/**
 * Created by Ww on 2018/4/18.
 */
public class ResponseData<T> {

    /**
     * 0 成功
     * 1 失败
     * 2 异常
     */
    private int state = 0;

    private String message = "";

    private T data;

    public ResponseData() {
    }

    public ResponseData(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseData success() {
        return success(null);
    }

    public static <T> ResponseData success(T data) {
        return new ResponseData(0,"操作成功",data);
    }

    public static <T> ResponseData failed() {
        return failed(null);
    }

    public static <T> ResponseData failed(T data) {
        return new ResponseData(1,"操作失败",data);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
