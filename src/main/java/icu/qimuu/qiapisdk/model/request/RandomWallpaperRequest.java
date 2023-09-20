package icu.qimuu.qiapisdk.model.request;

import icu.qimuu.qiapisdk.model.enums.RequestMethodEnum;
import icu.qimuu.qiapisdk.model.params.RandomWallpaperParams;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.RandomWallpaperResponse;
import lombok.experimental.Accessors;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description:
 */
@Accessors(chain = true)
public class RandomWallpaperRequest extends BaseRequest<RandomWallpaperParams, RandomWallpaperResponse> {
    @Override
    public String getPath() {
        return "/randomWallpaper";
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link BaseResponse}>
     */
    @Override
    public Class<RandomWallpaperResponse> getResponseClass() {
        return RandomWallpaperResponse.class;
    }


    @Override
    public String getMethod() {
        return RequestMethodEnum.GET.getValue();
    }
}
