package icu.qimuu.qiapisdk.service.impi;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.common.ErrorCode;
import icu.qimuu.qiapisdk.exception.BusinessException;
import icu.qimuu.qiapisdk.model.request.BaseRequest;
import icu.qimuu.qiapisdk.model.request.GetUserRequest;
import icu.qimuu.qiapisdk.model.request.PoisonousChickenSoupRequest;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.PoisonousChickenSoupResponse;
import icu.qimuu.qiapisdk.model.response.UserResponse;
import icu.qimuu.qiapisdk.service.ApiService;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:42
 * @Version: 1.0
 * @Description:
 */
@Data
public class ApiServiceImpl implements ApiService {
    public static final String GATEWAY_HOST = "http://localhost:8090/api";

    private QiApiClient qiApiClient;

    @Override
    public UserResponse getUser(GetUserRequest request) throws BusinessException {
        return request(request);
    }

    @Override
    public UserResponse getUser(QiApiClient qiApiClient, GetUserRequest request) throws BusinessException {
        return request(qiApiClient, request);
    }

    @Override
    public <T extends BaseResponse> T request(BaseRequest<T> request) throws BusinessException {
        try {
            return res(request);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    @Override
    public <T extends BaseResponse> T request(QiApiClient qiApiClient, BaseRequest<T> request) throws BusinessException {
        checkConfig(qiApiClient);
        return request(request);
    }

    @Override
    public PoisonousChickenSoupResponse getPoisonousChickenSoup(PoisonousChickenSoupRequest request) throws BusinessException {
        return request(request);
    }

    @Override
    public PoisonousChickenSoupResponse getPoisonousChickenSoup(QiApiClient qiApiClient, PoisonousChickenSoupRequest request) throws BusinessException {
        return request(qiApiClient, request);
    }

    private void checkConfig(QiApiClient qiApiClient) throws BusinessException {
        if (qiApiClient == null && this.getQiApiClient() == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "请先配置密钥AccessKey/SecretKey");
        }
        if (qiApiClient != null && !StringUtils.isAnyBlank(qiApiClient.getAccessKey(), qiApiClient.getSecretKey())) {
            this.setQiApiClient(qiApiClient);
        }
    }

    private <T extends BaseResponse> HttpResponse doRequest(BaseRequest<T> request) throws BusinessException {
        try (HttpResponse httpResponse = getHttpRequestByMethod(request)) {
            return httpResponse;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    private <T extends BaseResponse> HttpResponse getHttpRequestByMethod(BaseRequest<T> request) throws BusinessException {
        if (ObjectUtils.isEmpty(request)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请求参数错误");
        }
        String path = request.getPath().trim();
        String method = request.getMethod().trim().toUpperCase();

        if (ObjectUtils.isEmpty(method)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请求方法不存在");
        }
        if (StringUtils.isBlank(path)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请求路径不存在");
        }

        if (path.startsWith(GATEWAY_HOST)) {
            path = path.substring(GATEWAY_HOST.length());
        }

        System.err.println(method);
        System.err.println(path);

        HttpRequest httpRequest;
        switch (method) {
            case "GET": {
                httpRequest = HttpRequest.get(GATEWAY_HOST + path);
                break;
            }
            case "POST": {
                httpRequest = HttpRequest.post(GATEWAY_HOST + path);
                break;
            }
            default: {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "不支持该请求");
            }
        }
        return httpRequest
                .addHeaders(request.getHeaders(JSONUtil.toJsonStr(request), qiApiClient))
                .body(JSONUtil.toJsonStr(request.getRequestParams()))
                .execute();
    }

    private <T extends BaseResponse> T res(BaseRequest<T> request) throws BusinessException {
        if (qiApiClient == null || StringUtils.isAnyBlank(qiApiClient.getAccessKey(), qiApiClient.getSecretKey())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "请先配置密钥AccessKey/SecretKey");
        }
        T rsp;
        try {
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        HttpResponse httpResponse = doRequest(request);
        String body = httpResponse.body();
        Map<String, Object> data = new Gson().fromJson(body, new TypeToken<Map<String, T>>() {
        }.getType());
        rsp.setData(data);
        return rsp;
    }
}
