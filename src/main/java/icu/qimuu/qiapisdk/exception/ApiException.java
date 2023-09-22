package icu.qimuu.qiapisdk.exception;


/**
 * @Author: QiMu
 * @Date: 2023/09/15 09:31:43
 * @Version: 1.0
 * @Description: 自定义异常类
 */
public class ApiException extends Exception {

    private static final long serialVersionUID = 2942420535500634982L;
    private int code;

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public ApiException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
