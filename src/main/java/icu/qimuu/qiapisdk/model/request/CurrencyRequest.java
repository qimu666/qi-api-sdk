package icu.qimuu.qiapisdk.model.request;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import icu.qimuu.qiapisdk.model.response.BaseResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 13:59
 * @Version: 1.0
 * @Description:
 */


public class CurrencyRequest implements BaseRequest<BaseResponse> {

    private String method;
    private String path;

    private Map<String, Object> requestParams = new HashMap<>();

    @Override
    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String params) {
        this.requestParams = new Gson().fromJson(params, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * get方法
     *
     * @return {@link String}
     */
    @Override
    public String getMethod() {
        return method;
    }

    /**
     * 获取路径
     *
     * @return {@link String}
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link BaseResponse}>
     */
    @Override
    public Class<BaseResponse> getResponseClass() {
        return BaseResponse.class;
    }
}
