package com.hb.storage.autoconfigure;

import com.aliyun.oss.OSSClient;
import com.hb.storage.factory.OssClientFactory;
import com.hb.storage.properties.OssProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaochengshui
 * @description oss自动配置类
 * @date 2022/9/22
 */
@Configuration
@ConditionalOnProperty(prefix = "storage.oss", name = "enable", havingValue = "true")
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(OssAutoConfiguration.class);

    /**
     * OSSClient是线程安全的，允许多线程访问同一实例。您可以结合业务需求，复用同一个OSSClient实例，也可以创建多个OSSClient实例，分别使用。
     * <br/>
     * OSSClient实例内部维持一个连接池。当OSSClient实例不再使用时，请调用shutdown方法将其关闭，避免创建过多的OSSClient实例导致资源耗尽。
     *
     * @param ossProperties oss配置
     * @return OSSClient
     * @link <a href="https://help.aliyun.com/zh/oss/developer-reference/initialization-3?spm=a2c4g.11186623.0.0.28dfdf3aroY1EU">oss<a/>
     */
    @Bean(name = "ossClient", destroyMethod = "shutdown")
    @ConditionalOnMissingBean({OSSClient.class})
    public OSSClient ossClient(OssProperties ossProperties) {
        return OssClientFactory.createClient(ossProperties);
    }
}
