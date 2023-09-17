package icu.qimuu.qiapisdk.model.request;

import icu.qimuu.qiapisdk.model.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 13:59
 * @Version: 1.0
 * @Description:
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CurrencyRequest extends BaseRequest<BaseResponse> {
    private static final long serialVersionUID = 4408575981700377560L;
    private String name;

    @Override
    public void setMethod(String method) {
        super.setMethod(method);
    }


    @Override
    public void setPath(String path) {
        super.setPath(path);
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link CurrencyRequest}>
     */
    @Override
    public Class<BaseResponse> getResponseClass() {
        return BaseResponse.class;
    }
}
