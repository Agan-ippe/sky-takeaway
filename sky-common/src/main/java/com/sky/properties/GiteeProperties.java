package com.sky.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/26  15:44
 * @description 码云图床配置类
 */
@Component()
@ConfigurationProperties(prefix = "sky.giett")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiteeProperties {
    /**
     * 码云私人访问令牌
     */
    private String accessToken;

    /**
     * 码云个人空间名
     */
    private String owner;

    /**
     * 码云仓库路径
     */
    private String pero;
}
