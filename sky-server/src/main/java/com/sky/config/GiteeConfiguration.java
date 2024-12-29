package com.sky.config;

import com.sky.properties.GiteeProperties;
import com.sky.utils.GiteeImgsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/26  16:57
 * @description
 */
@Slf4j
@Configuration
public class GiteeConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GiteeImgsUtil giteeImgsUtil(GiteeProperties giteeProperties) {
        log.info("开始创建码云文件上传工具类对象：{}", giteeProperties);
        return new GiteeImgsUtil(giteeProperties.getAccessToken(),
                giteeProperties.getOwner(),
                giteeProperties.getPero());


    }
}
