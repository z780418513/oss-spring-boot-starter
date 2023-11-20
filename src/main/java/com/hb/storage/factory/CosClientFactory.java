package com.hb.storage.factory;

import com.hb.storage.properties.CosProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;

/**
 * @author: zhaochengshui
 * @description oss客户端工厂
 * @date: 2023/11/17 11:30
 */
public class CosClientFactory {

    public static COSClient createClient(CosProperties properties) {
        COSCredentials cred = new BasicCOSCredentials(properties.getSecretId(), properties.getSecretKey());
        // 2 设置 bucket 的地域, COS 地域的简称请参见 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(properties.getCosRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    private CosClientFactory() {
    }
}
