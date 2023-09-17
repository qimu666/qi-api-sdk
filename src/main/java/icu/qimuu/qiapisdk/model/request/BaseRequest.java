package icu.qimuu.qiapisdk.model.request;

import cn.hutool.crypto.SecureUtil;
import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.model.RequestMethod;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.utils.SignUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiMu
 * @Date: 2023/09/17 08:13:07
 * @Version: 1.0
 * @Description: 基本api请求
 */
@Accessors(chain = true)
@Data
public abstract class BaseRequest<T extends BaseResponse> implements Serializable {
    private static final long serialVersionUID = -4766915659779847060L;
    private String path;
    /**
     * 方法
     */
    private String method;

    /**
     * 获取请求头
     *
     * @param body 请求体
     * @return {@link Map}<{@link String}, {@link String}>
     */
    public Map<String, String> getHeaders(String body, QiApiClient qiApiClient) {
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
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取路径
     *
     * @return {@link String}
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link T}>
     */
    public abstract Class<T> getResponseClass();
}