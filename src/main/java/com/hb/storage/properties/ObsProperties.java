package com.hb.storage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: zhaochengshui
 * @description obs配置类
 * @date: 2023/11/17 17:34
 */
@ConfigurationProperties(prefix = "storage.obs")
public class ObsProperties {
    /**
     * 开启obs
     */
    private boolean enable;

    /**
     * AK
     */
    private String accessKey;
    /**
     * SK
     */
    private String secretKey;
    /**
     * endPoint
     */
    private String endPoint;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

}
