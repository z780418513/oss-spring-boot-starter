package com.hb.storage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: zhaochengshui
 * @description cos配置类
 * @date: 2023/11/20 13:39
 */
@ConfigurationProperties(prefix = "storage.cos")
public class CosProperties {
    /**
     * 开启cos
     */
    private boolean enable;

    /**
     * appId
     */
    private String appId;

    /**
     * 密钥ID
     */
    private String secretId;

    /**
     * 密钥KEY
     */
    private String secretKey;

    /**
     * cosRegion
     *
     * @see <a href="https://cloud.tencent.com/document/product/436/6224">地域和访问域名<a/>
     */
    private String cosRegion;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getCosRegion() {
        return cosRegion;
    }

    public void setCosRegion(String cosRegion) {
        this.cosRegion = cosRegion;
    }
}
