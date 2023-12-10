package cn.doanything.commons.response;

/**
 * 返回结果
 * @param <T>
 */
public class ResponseResult<T> {
    /**
     * 执行状态，是否成功
     */
    private boolean success;

    /**
     * 结果码
     */
    private String code;

    /**
     * 结果消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public ResponseResult(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(boolean success, ResultCode resultCode, T data) {
        this.success = success;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 返回成功，不带数据
     * @return
     * @param <T>
     */
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    /**
     * 返回成功并带数据
     * @param data  数据
     * @return
     * @param <T>
     */
    public static <T> ResponseResult<T> success(T data) {
        return success(BaseResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 构造成功结果
     * @param message   消息提示
     * @param data      返回数据
     * @return  结果
     * @param <T>   数据类型
     */
    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<T>(true, BaseResultCode.SUCCESS.getCode(), message, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
