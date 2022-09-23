package com.hb.oss.config;

import com.hb.oss.properties.OssProperties;
import com.hb.oss.service.impl.AliyunOssServiceImpl;
import com.hb.oss.service.OssService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author zhaochengshui
 * @description
 * @date 2022/9/22
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfig {
    @Resource
    private OssProperties ossProperties;

    @ConditionalOnProperty(prefix = "aliyun", name = "enable-oss", havingValue = "true")
    @Bean(name = "aliyunOssService")
    public OssService ossService() {
        return new AliyunOssServiceImpl(ossProperties);
    }
}
