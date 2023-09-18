package icu.qimuu.qiapisdk.service;

import cn.hutool.http.HttpResponse;
import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.exception.BusinessException;
import icu.qimuu.qiapisdk.model.request.BaseRequest;
import icu.qimuu.qiapisdk.model.request.GetUserRequest;
import icu.qimuu.qiapisdk.model.request.PoisonousChickenSoupRequest;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.PoisonousChickenSoupResponse;
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
     * @return {@link UserResponse}
     * @throws BusinessException 业务异常
     */
    UserResponse getUser(GetUserRequest request) throws BusinessException;

    /**
     * 获取用户
     *
     * @param request     要求
     * @param qiApiClient qi api客户端
     * @return {@link UserResponse}
     * @throws BusinessException 业务异常
     */
    UserResponse getUser(QiApiClient qiApiClient, GetUserRequest request) throws BusinessException;


    /**
     * 请求
     *
     * @param request 要求
     * @return {@link HttpResponse}
     * @throws BusinessException 业务异常
     */

    <T extends BaseResponse> T request(BaseRequest<T> request) throws BusinessException;

    /**
     * 请求
     *
     * @param qiApiClient qi api客户端
     * @param request     要求
     * @return {@link T}
     * @throws BusinessException 业务异常
     */
    <T extends BaseResponse> T request(QiApiClient qiApiClient, BaseRequest<T> request) throws BusinessException;

    /**
     * 随机毒鸡汤
     *
     * @param request 要求
     * @return {@link PoisonousChickenSoupResponse}
     * @throws BusinessException 业务异常
     */
    PoisonousChickenSoupResponse getPoisonousChickenSoup(PoisonousChickenSoupRequest request) throws BusinessException;

    /**
     * 喝毒鸡汤
     *
     * @param qiApiClient qi api客户端
     * @param request     请求
     * @return {@link PoisonousChickenSoupResponse}
     * @throws BusinessException 业务异常
     */
    PoisonousChickenSoupResponse getPoisonousChickenSoup(QiApiClient qiApiClient, PoisonousChickenSoupRequest request) throws BusinessException;
}
