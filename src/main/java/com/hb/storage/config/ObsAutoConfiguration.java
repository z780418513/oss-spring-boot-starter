package com.hb.storage.config;

import com.hb.storage.factory.ObsClientFactory;
import com.hb.storage.properties.ObsProperties;
import com.obs.services.ObsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhaochengshui
 * @description obs自定配置类
 * @date: 2023/11/17 17:34
 */
@Configuration
@ConditionalOnProperty(prefix = "storage.obs", name = "enable", havingValue = "true")
@EnableConfigurationProperties(ObsProperties.class)
public class ObsAutoConfiguration {

    /**
     * obs客户端
     * <p>
     * 建议整个代码工程全局使用一个ObsClient客户端，只在程序初始化时创建一次，因为创建多个ObsClient客户端在高并发场景下会影响性能。
     * 在使用临时aksk时，aksk会有过期时间，可调用ObsClient.refresh("yourAccessKey", "yourSecretKey", "yourSecurityToken")刷新ObsClient的aksk，不必重新创建ObsClient。
     * ObsClient是线程安全的，可在并发场景下使用。
     *
     * @param properties properties
     * @return ObsClient
     * @see <a href="https://support.huaweicloud.com/sdk-java-devg-obs/obs_21_0202.html">华为云<a/>
     */
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean({ObsClient.class})
    public ObsClient obsClient(ObsProperties properties) {
        return ObsClientFactory.createClient(properties);
    }
}
