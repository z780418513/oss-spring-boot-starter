package com.hb.storage.autoconfigure;

import com.hb.storage.factory.CosClientFactory;
import com.hb.storage.properties.CosProperties;
import com.qcloud.cos.COSClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhaochengshui
 * @description cos自定配置类
 * @date: 2023/11/17 17:34
 */
@Configuration
@ConditionalOnProperty(prefix = "storage.cos", name = "enable", havingValue = "true")
@EnableConfigurationProperties(CosProperties.class)
public class CosAutoConfiguration {

    /**
     * cos客户端
     * <p>
     * COSClient 是线程安全的类，允许多线程访问同一实例。因为实例内部维持了一个连接池，创建多个实例可能导致程序资源耗尽，
     * 请确保程序生命周期内实例只有一个，并在不再需要使用时，调用 shutdown 方法将其关闭。如果需要新建实例，请先将之前的实例关闭。
     *
     * @param properties properties
     * @return COSClient
     * @see <a href="https://cloud.tencent.com/document/product/436/10199#.E5.88.9D.E5.A7.8B.E5.8C.96.E5.AE.A2.E6.88.B7.E7.AB.AF">腾讯云<a/>
     */
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean({COSClient.class})
    public COSClient cosClient(CosProperties properties) {
        return CosClientFactory.createClient(properties);
    }
}
