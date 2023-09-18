package icu.qimuu.qiapisdk.model.request;

import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.model.RequestMethod;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiMu
 * @Date: 2023/09/17 08:13:07
 * @Version: 1.0
 * @Description: 基本api请求
 */
public interface BaseRequest<T extends BaseResponse> {

    /**
     * 获取请求头
     *
     * @param body        请求体
     * @param qiApiClient qi api客户端
     * @return {@link Map}<{@link String}, {@link String}>
     */
    default Map<String, String> getHeaders(String body, QiApiClient qiApiClient) {
        Map<String, String> hashMap = new HashMap<>(4);
        hashMap.put("accessKey", qiApiClient.getAccessKey());
        String encodedBody = SecureUtil.md5(body);
        hashMap.put("body", encodedBody);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.getSign(encodedBody, qiApiClient.getSecretKey()));
        return hashMap;
    }

    /**
     * get方法
     *
     * @return {@link RequestMethod}
     */
    String getMethod();

    /**
     * 获取路径
     *
     * @return {@link String}
     */
    String getPath();

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link T}>
     */
    Class<T> getResponseClass();

    /**
     * 获取请求参数
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @JsonAnyGetter
    Map<String, Object> getRequestParams();
}