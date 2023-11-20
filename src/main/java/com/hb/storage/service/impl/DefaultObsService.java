package com.hb.storage.service.impl;

import com.hb.storage.enums.StorageEnum;
import com.hb.storage.enums.StoreOperationEnum;
import com.hb.storage.exception.StoreException;
import com.hb.storage.service.ObsService;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

/**
 * @author: zhaochengshui
 * @description OBS默认服务类
 * @date: 2023/11/18 14:44
 */
public class DefaultObsService implements ObsService {
    private static final Logger log = LoggerFactory.getLogger(DefaultObsService.class);

    /**
     * bucketName
     */
    private final String bucketName;

    /**
     * ossClient
     */
    private final ObsClient client;

    /**
     * location
     */
    @Nullable
    private String location;

    public DefaultObsService(String bucketName, ObsClient client) {
        this.bucketName = bucketName;
        this.client = client;
    }

    @Override
    public void createBucket() throws StoreException {
        try {
            // 创建存储空间，需要指定location
            client.createBucket(bucketName, location);
        } catch (ObsException e) {
            throw new StoreException(StorageEnum.HUAWEI_OBS, StoreOperationEnum.CREATE_BUCKET, e.getErrorMessage());
        }
    }

    @Override
    public void deleteBuket() throws StoreException {
        try {
            client.deleteBucket(bucketName);
        } catch (ObsException e) {
            throw new StoreException(StorageEnum.HUAWEI_OBS, StoreOperationEnum.DELETE_BUCKET, e.getErrorMessage());
        }
    }

    @Override
    public boolean existBuket() throws StoreException {
        try {
           return client.headBucket(bucketName);
        } catch (ObsException e) {
            throw new StoreException(StorageEnum.HUAWEI_OBS, StoreOperationEnum.QUERY_BUCKET, e.getErrorMessage());
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
