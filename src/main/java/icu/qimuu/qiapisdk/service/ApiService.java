package icu.qimuu.qiapisdk.service;

import cn.hutool.http.HttpResponse;
import icu.qimuu.qiapisdk.exception.BusinessException;
import icu.qimuu.qiapisdk.model.request.BaseRequest;
import icu.qimuu.qiapisdk.model.request.GetUserRequest;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.UserResponse;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:34
 * @Version: 1.0
 * @Description:
 */
public interface ApiService {
    /**
     * 获取用户
     *
     * @param request 要求
     * @return {@link T}
     * @throws BusinessException 业务异常
     */
    <T extends BaseResponse> T getUser(BaseRequest<T> request) throws BusinessException;

    /**
     * 要求
     *
     * @param request 要求
     * @return {@link HttpResponse}
     * @throws BusinessException 业务异常
     */

    <T extends BaseResponse> T request(BaseRequest<T> request) throws BusinessException;

}
