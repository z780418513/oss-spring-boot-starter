package com.hb.storage.service.impl;

import com.hb.storage.enums.StorageEnum;
import com.hb.storage.enums.StoreOperationEnum;
import com.hb.storage.exception.StoreException;
import com.hb.storage.service.BucketService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.CreateBucketRequest;

import java.util.function.Supplier;

/**
 * @author: zhaochengshui
 * @description
 * @date: 2023/11/20 13:46
 */
public class DefaultCosService implements BucketService {


    /**
     * bucketName
     */
    private final String realBucketName;

    /**
     * cos客户端
     */
    private final COSClient cosClient;

    public DefaultCosService(String bucketName, String appId, COSClient cosClient) {
        // cos 存储桶名称为 bucketName-appId
        this.realBucketName = bucketName + "-" + appId;
        this.cosClient = cosClient;
    }

    @Override
    public void createBucket() throws StoreException {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(realBucketName);
        // 设置 bucket 的权限为 Private(私有读写), 其他可选有公有读私有写, 公有读写
        createBucketRequest.setCannedAcl(CannedAccessControlList.PublicReadWrite);

        doCosRequest(StoreOperationEnum.CREATE_BUCKET, () -> cosClient.createBucket(realBucketName));
    }

    @Override
    public void deleteBuket() throws StoreException {
        doCosRequest(StoreOperationEnum.DELETE_BUCKET, () -> cosClient.deleteBucket(realBucketName));

    }

    @Override
    public boolean existBuket() throws StoreException {
        return doCosRequest(StoreOperationEnum.QUERY_BUCKET, () -> cosClient.doesBucketExist(realBucketName));
    }


    private <T> T doCosRequest(StoreOperationEnum operationEnum, Supplier<T> request) {
        try {
            return request.get();
        } catch (CosServiceException e) {
            throw new StoreException(StorageEnum.TENCENT_COS, operationEnum, e.getErrorMessage());
        } catch (Exception e) {
            throw new StoreException(StorageEnum.TENCENT_COS, operationEnum, e.getMessage());
        }
    }

    private void doCosRequest(StoreOperationEnum operationEnum, Runnable request) {
        try {
            request.run();
        } catch (CosServiceException e) {
            throw new StoreException(StorageEnum.TENCENT_COS, operationEnum, e.getErrorMessage());
        } catch (Exception e) {
            throw new StoreException(StorageEnum.TENCENT_COS, operationEnum, e.getMessage());
        }
    }

}
