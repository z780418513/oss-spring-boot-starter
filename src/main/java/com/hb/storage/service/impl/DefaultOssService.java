package com.hb.storage.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.hb.storage.enums.StorageEnum;
import com.hb.storage.enums.StoreOperationEnum;
import com.hb.storage.exception.StoreException;
import com.hb.storage.service.OssService;
import com.hb.storage.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import java.io.File;
import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author zhaochengshui
 * @description
 * @date 2022/9/22
 */
public class DefaultOssService extends AbstractStorageService implements OssService {
    private static final Logger log = LoggerFactory.getLogger(DefaultOssService.class);

    /**
     * bucketName
     */
    private final String bucketName;

    /**
     * ossClient
     */
    private final OSSClient client;

    private static final CannedAccessControlList DEFAULT_CANNED_ACCESS = CannedAccessControlList.PublicRead;


    public DefaultOssService(String bucketName, OSSClient ossClient) {
        this.bucketName = bucketName;
        this.client = ossClient;
    }

    @Override
    public void createBucket() throws StoreException {
        doOssRequest(StoreOperationEnum.CREATE_BUCKET,
                () -> {
                    // 创建存储空间。
                    client.createBucket(bucketName);
                    // 默认公共读
                    return client.setBucketAcl(bucketName, DEFAULT_CANNED_ACCESS);
                });

    }

    @Override
    public void deleteBuket() throws StoreException {
        // 删除存储空间。
        doOssRequest(StoreOperationEnum.DELETE_BUCKET, () -> client.deleteBucket(bucketName));
    }

    @Override
    public boolean existBuket() throws StoreException {
        return doOssRequest(StoreOperationEnum.QUERY_BUCKET, () -> client.doesBucketExist(bucketName));
    }

    @Override
    public String uploadFile(String localFilePath) throws StoreException {
        return uploadFile(localFilePath, null);
    }

    @Override
    public String uploadFile(String localFilePath, @Nullable String remoteFileDir) throws StoreException {
        return doOssRequest(StoreOperationEnum.UPLOAD_FILE,
                () -> {
                    String fileName = FileHelper.getFileName(localFilePath);
                    String objectName = getObjectName(fileName, remoteFileDir, true);

                    return uploadFileWithObjectName(localFilePath, objectName);
                });
    }

    @Override
    public String uploadFileWithObjectName(String localFilePath, String objectName) throws StoreException {
        return doOssRequest(StoreOperationEnum.UPLOAD_FILE,
                () -> {
                    File file = new File(localFilePath);
                    // 创建PutObjectRequest对象。
                    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);
                    // 上传文件。
                    client.putObject(putObjectRequest);

                    return objectName;
                });
    }

    @Override
    public String uploadInputStream(InputStream inputStream, String fileName) throws StoreException {
        return uploadInputStream(inputStream, fileName, null);
    }

    @Override
    public String uploadInputStream(InputStream inputStream, String fileName, @Nullable String remoteFileDir) throws StoreException {
        return doOssRequest(StoreOperationEnum.UPLOAD_FILE,
                () -> {
                    String objectName = getObjectName(fileName, remoteFileDir, true);
                    return uploadInputStreamWithObjectName(inputStream, objectName);
                });
    }

    @Override
    public String uploadInputStreamWithObjectName(InputStream inputStream, String objectName) throws StoreException {
        return doOssRequest(StoreOperationEnum.UPLOAD_FILE,
                () -> {
                    // 创建PutObjectRequest对象。
                    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
                    // 上传文件。
                    client.putObject(putObjectRequest);

                    return objectName;
                });
    }

    @Override
    public void deleteFile(String objectName) throws StoreException {
        // 删除文件或目录。如果要删除目录，目录必须为空。
        doOssRequest(StoreOperationEnum.DELETE_FILE, () -> client.deleteObject(bucketName, objectName));
    }

    @Override
    public boolean fileExist(String objectName) throws StoreException {
        return doOssRequest(StoreOperationEnum.QUERY_FILE, () -> client.doesObjectExist(bucketName, objectName));
    }

    @Override
    public InputStream downLoadFile(String objectName) throws StoreException {
        return doOssRequest(StoreOperationEnum.DOWNLOAD_FILE,
                () -> {
                    // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
                    OSSObject ossObject = client.getObject(bucketName, objectName);
                    return ossObject.getObjectContent();
                });
    }

    @Override
    public boolean downLoadFile(String objectName, String localFileDir) throws StoreException {
        return doOssRequest(StoreOperationEnum.DOWNLOAD_FILE,
                () -> {
                    GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
                    ObjectMetadata object = client.getObject(request, new File(localFileDir));
                    return true;
                });

    }


    private <T> T doOssRequest(StoreOperationEnum operationEnum, Supplier<T> request) {
        try {
            return request.get();
        } catch (OSSException e) {
            throw new StoreException(StorageEnum.ALI_OSS, operationEnum, e.getErrorMessage());
        } catch (ClientException e) {
            throw new StoreException(StorageEnum.ALI_OSS, operationEnum, e.getErrorMessage());
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, operationEnum, e.getMessage());
        }
    }

}
