package icu.qimuu.qiapisdk.service;

import cn.hutool.http.HttpResponse;
import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.exception.BusinessException;
import icu.qimuu.qiapisdk.model.request.BaseRequest;
import icu.qimuu.qiapisdk.model.request.RandomWallpaperRequest;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.PoisonousChickenSoupResponse;
import icu.qimuu.qiapisdk.model.response.RandomWallpaperResponse;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:34
 * @Version: 1.0
 * @Description:
 */
public interface ApiService {

    /**
     * 随机毒鸡汤
     *
     * @return {@link PoisonousChickenSoupResponse}
     * @throws BusinessException 业务异常
     */
    PoisonousChickenSoupResponse getPoisonousChickenSoup() throws BusinessException;

    /**
     * 随机毒鸡汤
     *
     * @param qiApiClient qi api客户端
     * @return {@link PoisonousChickenSoupResponse}
     * @throws BusinessException 业务异常
     */
    PoisonousChickenSoupResponse getPoisonousChickenSoup(QiApiClient qiApiClient) throws BusinessException;

    /**
     * 获取随机壁纸
     *
     * @param request 要求
     * @return {@link RandomWallpaperResponse}
     * @throws BusinessException 业务异常
     */
    RandomWallpaperResponse getRandomWallpaper(RandomWallpaperRequest request) throws BusinessException;

    /**
     * 获取随机壁纸
     *
     * @param qiApiClient qi api客户端
     * @param request     要求
     * @return {@link RandomWallpaperResponse}
     * @throws BusinessException 业务异常
     */
    RandomWallpaperResponse getRandomWallpaper(QiApiClient qiApiClient, RandomWallpaperRequest request) throws BusinessException;

    /**
     * 请求
     *
     * @param request 要求
     * @return {@link HttpResponse}
     * @throws BusinessException 业务异常
     */

    <O, T extends BaseResponse> T request(BaseRequest<O, T> request) throws BusinessException;

    /**
     * 请求
     *
     * @param qiApiClient qi api客户端
     * @param request     要求
     * @return {@link T}
     * @throws BusinessException 业务异常
     */
    <O, T extends BaseResponse> T request(QiApiClient qiApiClient, BaseRequest<O, T> request) throws BusinessException;
}
