package com.hb.storage.factory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.hb.storage.properties.OssProperties;

/**
 * @author: zhaochengshui
 * @description oss客户端工厂
 * @date: 2023/11/17 11:30
 */
public class OssClientFactory {

    public static OSSClient createClient(OssProperties ossProperties) {
        DefaultCredentialProvider credentialProvider = new DefaultCredentialProvider(ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        return new OSSClient(ossProperties.getEndpoint(), credentialProvider, null);
    }

    private OssClientFactory() {
    }
}
