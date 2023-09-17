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
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.service.ApiService;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static icu.qimuu.qiapisdk.client.QiApiClient.GATEWAY_HOST;


/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:42
 * @Version: 1.0
 * @Description:
 */
@Data
public class ApiServiceImpl implements ApiService {
    private QiApiClient qiApiClient;

    /**
     * 获取用户
     *
     * @param request 要求
     * @return {@link T}
     * @throws BusinessException 业务异常
     */
    @Override
    public <T extends BaseResponse> T getUser(BaseRequest<T> request) throws BusinessException {
        try {
            return res(request);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    private <T extends BaseResponse> T res(BaseRequest<T> request) throws BusinessException {
        T rsp;
        try {
            Class<T> clazz = request.getResponseClass();
            rsp = clazz.newInstance();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
        String body = doRequest(request).body();
        Map<String, Object> data = new Gson().fromJson(body, new TypeToken<Map<String, T>>() {
        }.getType());
        rsp.setData(data);
        return rsp;
    }


    @Override
    public <T extends BaseResponse> T request(BaseRequest<T> request) throws BusinessException {
        try {
            return res(request);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }


    public <T extends BaseResponse> HttpResponse doRequest(BaseRequest<T> request) throws BusinessException {
        try (HttpResponse httpResponse = getHttpRequestByMethod(request)) {
            return httpResponse;
        } catch (BusinessException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    private <T extends BaseResponse> HttpResponse getHttpRequestByMethod(BaseRequest<T> request) throws BusinessException {
        if (ObjectUtils.isEmpty(request)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请求参数错误");
        }
        if (ObjectUtils.isEmpty(request.getMethod())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请求方法不存在");
        }
        if (StringUtils.isBlank(request.getPath())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "请求路径不存在");
        }
        System.err.println(request.getMethod());
        System.err.println(request.getPath());
        HttpRequest httpRequest;
        switch (request.getMethod()) {
            case "GET": {
                httpRequest = HttpRequest.get(GATEWAY_HOST + request.getPath());
                break;
            }
            case "POST": {
                httpRequest = HttpRequest.post(GATEWAY_HOST + request.getPath());
                break;
            }
            default: {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "不支持该请求");
            }
        }
        return httpRequest
                .addHeaders(request.getHeaders(JSONUtil.toJsonStr(request), qiApiClient))
                .body(JSONUtil.toJsonStr(request))
                .execute();
    }
}
