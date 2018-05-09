package cn.fts.vo;

/**
 * Created by Ww on 2018/4/18.
 */
public class ResponseData<T> {

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
