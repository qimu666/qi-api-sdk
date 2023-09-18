package icu.qimuu.qiapisdk.model.request;

import icu.qimuu.qiapisdk.model.RequestMethod;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.UserResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description:
 */
public class GetUserRequest implements BaseRequest<UserResponse> {
    private Map<String, Object> requestParams = new HashMap<>();
    /**
     * 名称
     */
    private String name;

    @Override
    public String getPath() {
        return "/name/json";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link BaseResponse}>
     */
    @Override
    public Class<UserResponse> getResponseClass() {
        return UserResponse.class;
    }

    /**
     * 获取请求参数
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    /**
     * 设置请求参数
     *
     * @param requestParams 请求参数
     */
    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public String getMethod() {
        return RequestMethod.POST.getValue();
    }
}
