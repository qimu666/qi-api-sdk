package icu.qimuu.qiapisdk.exception;

/**
 * 同一返回错误码
 *
 * @author QiMu
 */
public enum ErrorCode {

    /**
     * 成功
     */
    SUCCESS(0, "ok"),
    /**
     * 请求参数错误
     */
    PARAMS_ERROR(40000, "请求参数错误"),
    /**
     * 未登录
     */
    NOT_LOGIN_ERROR(40100, "未登录"),
    /**
     * 无权限
     */
    NO_AUTH_ERROR(40101, "无权限"),
    /**
     * 请求数据不存在
     */
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    /**
     * 禁止访问
     */
    FORBIDDEN_ERROR(40300, "禁止访问"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(50000, "系统内部异常"),
    /**
     * 操作错误
     */
    OPERATION_ERROR(50001, "操作失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
