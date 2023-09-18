package icu.qimuu.qiapisdk.model.request;

import icu.qimuu.qiapisdk.model.RequestMethod;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.PoisonousChickenSoupResponse;

import java.util.Map;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description:
 */
public class PoisonousChickenSoupRequest implements BaseRequest<PoisonousChickenSoupResponse> {

    @Override
    public String getPath() {
        return "/name/poisonousChickenSoup";
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link BaseResponse}>
     */
    @Override
    public Class<PoisonousChickenSoupResponse> getResponseClass() {
        return PoisonousChickenSoupResponse.class;
    }

    /**
     * 获取请求参数
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> getRequestParams() {
        return null;
    }

    /**
     * 设置请求参数
     *
     * @param requestParams 请求参数
     */
    public void setRequestParams(Map<String, Object> requestParams) {

    }

    @Override
    public String getMethod() {
        return RequestMethod.GET.getValue();
    }
}
