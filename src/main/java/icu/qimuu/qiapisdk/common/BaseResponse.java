package icu.qimuu.qiapisdk.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: QiMu
 * @Date: 2023/08/17 09:14:26
 * @Version: 1.0
 * @Description: 通用返回类
 */
@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -6486005224268968744L;
    private int code;

    private String data;

    private String message;

    public BaseResponse(int code, String data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, String data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}