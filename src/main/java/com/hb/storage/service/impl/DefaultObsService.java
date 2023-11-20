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

import java.util.function.Supplier;

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
        // 创建存储空间，需要指定location
        doObsRequest(StoreOperationEnum.CREATE_BUCKET, () -> client.createBucket(bucketName, location));
    }

    @Override
    public void deleteBuket() throws StoreException {
        doObsRequest(StoreOperationEnum.DELETE_BUCKET, () -> client.deleteBucket(bucketName));
    }

    @Override
    public boolean existBuket() throws StoreException {
        return doObsRequest(StoreOperationEnum.QUERY_BUCKET, () -> client.headBucket(bucketName));
    }






    private <T> T doObsRequest(StoreOperationEnum operationEnum, Supplier<T> request) {
        try {
            return request.get();
        } catch (ObsException e) {
            throw new StoreException(StorageEnum.HUAWEI_OBS, operationEnum, e.getErrorMessage());
        } catch (Exception e) {
            throw new StoreException(StorageEnum.HUAWEI_OBS, operationEnum, e.getMessage());
        }
    }


    public void setLocation(String location) {
        this.location = location;
    }
}
