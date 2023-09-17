package icu.qimuu.qiapisdk.model.request;

import icu.qimuu.qiapisdk.model.RequestMethod;
import icu.qimuu.qiapisdk.model.response.BaseResponse;
import icu.qimuu.qiapisdk.model.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: QiMu
 * @Date: 2023年09月17日 08:38
 * @Version: 1.0
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRequest extends BaseRequest<UserResponse> {
    private static final long serialVersionUID = -3794078102542594424L;
    /**
     * 名称
     */
    private String name;

    @Override
    public String getPath() {
        return "/name/json";
    }

    /**
     * 获取响应类
     *
     * @return {@link Class}<{@link BaseResponse}>
     */
    @Override
    public Class<UserResponse> getResponseClass() {
        return UserResponse.class;
    }

    @Override
    public String getMethod() {
        return RequestMethod.POST.getValue();
    }
}
