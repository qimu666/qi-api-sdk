package icu.qimuu.qiapisdk.client;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import icu.qimuu.qiapisdk.common.BaseResponse;
import icu.qimuu.qiapisdk.model.QiApiRequest;
import icu.qimuu.qiapisdk.model.User;
import icu.qimuu.qiapisdk.utils.SignUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiMu
 * @Date: 2023/09/15 08:41:51
 * @Version: 1.0
 * @Description: qi api客户端
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiApiClient {
    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 秘密密钥
     */
    private String secretKey;

    public static final String GATEWAY_HOST = "http://localhost:8090/api";

    /**
     * 被json职位名称
     *
     * @param qiApiRequest api请求
     * @return {@link BaseResponse}<{@link String}>
     */
    public BaseResponse<User> getNameByJsonPost(QiApiRequest qiApiRequest) {
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/name/json")
                .addHeaders(getHeaders(JSONUtil.toJsonStr(qiApiRequest)))
                .body(JSONUtil.toJsonStr(qiApiRequest))
                .execute();

        int status = httpResponse.getStatus();
        System.out.println(status);
        System.out.println(httpResponse.body());
        TypeReference<BaseResponse<User>> typeRef = new TypeReference<BaseResponse<User>>() {
        };

        System.out.println("typeRef = " + typeRef);
        return JSONUtil.toBean(httpResponse.body(), typeRef, false);
    }

    private Map<String, String> getHeaders(String body) {
        Map<String, String> hashMap = new HashMap<>(4);
        hashMap.put("accessKey", accessKey);
        String encodedBody = SecureUtil.md5(body);
        hashMap.put("body", encodedBody);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtils.getSign(encodedBody, secretKey));
        return hashMap;
    }
}
