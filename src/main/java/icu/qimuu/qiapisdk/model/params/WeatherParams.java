package icu.qimuu.qiapisdk.model.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: QiMu
 * @Date: 2023/09/22 10:11:04
 * @Version: 1.0
 * @Description: 获取天气请求参数
 */
@Data
@Accessors(chain = true)
public class WeatherParams implements Serializable {
    private static final long serialVersionUID = 3815188540434269370L;
    private String ip;
    private String city;
    private String type;
}
