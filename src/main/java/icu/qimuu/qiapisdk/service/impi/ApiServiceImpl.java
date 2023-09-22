package icu.qimuu.qiapisdk.service.impi;

import icu.qimuu.qiapisdk.client.QiApiClient;
import icu.qimuu.qiapisdk.exception.BusinessException;
import icu.qimuu.qiapisdk.model.request.PoisonousChickenSoupRequest;
import icu.qimuu.qiapisdk.model.request.RandomWallpaperRequest;
import icu.qimuu.qiapisdk.model.response.PoisonousChickenSoupResponse;
import icu.qimuu.qiapisdk.model.response.RandomWallpaperResponse;
import icu.qimuu.qiapisdk.service.ApiService;
import icu.qimuu.qiapisdk.service.BaseService;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:42
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class ApiServiceImpl extends BaseService implements ApiService {

    @Override
    public PoisonousChickenSoupResponse getPoisonousChickenSoup() throws BusinessException {
        PoisonousChickenSoupRequest request = new PoisonousChickenSoupRequest();
        return request(request);
    }

    @Override
    public PoisonousChickenSoupResponse getPoisonousChickenSoup(QiApiClient qiApiClient) throws BusinessException {
        PoisonousChickenSoupRequest request = new PoisonousChickenSoupRequest();
        return request(qiApiClient, request);
    }

    @Override
    public RandomWallpaperResponse getRandomWallpaper(RandomWallpaperRequest request) throws BusinessException {
        return request(request);
    }

    @Override
    public RandomWallpaperResponse getRandomWallpaper(QiApiClient qiApiClient, RandomWallpaperRequest request) throws BusinessException {
        return request(qiApiClient, request);
    }
}
