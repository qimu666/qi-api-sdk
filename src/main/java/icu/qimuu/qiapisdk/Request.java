package icu.qimuu.qiapisdk;

/**
 * @Author: QiMu
 * @Date: 2023年09月16日 23:13
 * @Version: 1.0
 * @Description:
 */
public interface Request<T extends QiApiResponse> {
    /**
     * get方法
     *
     * @return {@link String}
     */
    String getMethod();

    /**
     * 集合方法
     *
     * @return {@link String}
     */
    String setMethod();
}
