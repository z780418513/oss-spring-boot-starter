package com.hb.storage.service.impl;

import cn.hutool.core.io.IoUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.hb.storage.enums.StorageEnum;
import com.hb.storage.enums.StoreOperationEnum;
import com.hb.storage.exception.StoreException;
import com.hb.storage.service.OssService;
import com.hb.storage.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author zhaochengshui
 * @description
 * @date 2022/9/22
 */
public class DefaultOssService implements OssService {
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
        try {
            // 创建存储空间。
            client.createBucket(bucketName);
            // 默认公共读
            client.setBucketAcl(bucketName, DEFAULT_CANNED_ACCESS);
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, StoreOperationEnum.CREATE_BUCKET, e.getMessage());
        }
    }

    @Override
    public void deleteBuket() throws StoreException {
        try {
            // 删除存储空间。
            client.deleteBucket(bucketName);
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, StoreOperationEnum.DELETE_BUCKET, e.getMessage());
        }
    }

    @Override
    public boolean existBuket() throws StoreException {
        try {
            return client.doesBucketExist(bucketName);
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, StoreOperationEnum.QUERY_BUCKET, e.getMessage());
        }
    }

    @Override
    public String upLoadFile(String filePath) throws StoreException {
        try {
            File file = new File(filePath);

            String objectName = FileHelper.generateFileByUUID(filePath);
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);
            // 上传文件。
            client.putObject(putObjectRequest);

            return objectName;
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, StoreOperationEnum.UPLOAD_FILE, e.getMessage());
        }
    }

    @Override
    public void deleteFile(String objectName) throws StoreException {
        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            client.deleteObject(bucketName, objectName);
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, StoreOperationEnum.DELETE_FILE, e.getMessage());
        }
    }

    @Override
    public boolean fileExist(String objectName) throws StoreException {
        try {
            return client.doesObjectExist(bucketName, objectName);
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, StoreOperationEnum.QUERY_FILE, e.getMessage());
        }
    }

    @Override
    public InputStream downLoadFile(String objectName) throws StoreException {
        try {
            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = client.getObject(bucketName, objectName);
            return ossObject.getObjectContent();
        } catch (Exception e) {
            throw new StoreException(StorageEnum.ALI_OSS, StoreOperationEnum.DOWNLOAD_FILE, e.getMessage());
        }
    }

    @Override
    public String downLoadFileContext(String objectName) throws StoreException {
        InputStream inputStream = downLoadFile(objectName);
        return IoUtil.read(inputStream, Charset.defaultCharset());
    }


}
