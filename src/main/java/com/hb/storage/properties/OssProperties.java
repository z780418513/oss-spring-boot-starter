package com.hb.storage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhaochengshui
 * @description
 * @date 2022/9/22
 */
@ConfigurationProperties(prefix = "storage.oss")
public class OssProperties {

    /**
     * 开启oss
     */
    private boolean enable;

    /**
     * Endpoint
     */
    private String endpoint;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
