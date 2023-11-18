package com.hb.storage.factory;

import com.hb.storage.properties.ObsProperties;
import com.obs.services.ObsClient;

/**
 * @author: zhaochengshui
 * @description oss客户端工厂
 * @date: 2023/11/17 11:30
 */
public class ObsClientFactory {

    public static ObsClient createClient(ObsProperties properties) {
        return new ObsClient(properties.getAccessKey(), properties.getSecretKey(), properties.getEndPoint());
    }

    private ObsClientFactory() {
    }
}
