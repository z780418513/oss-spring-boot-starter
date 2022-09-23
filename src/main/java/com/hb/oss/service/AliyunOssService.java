package com.hb.oss.service;

import java.io.InputStream;

/**
 * @author zhaochengshui
 * @description 阿里云OSS服务类
 * @date 2022/9/22
 */
public interface AliyunOssService extends OssService {

    /**
     * 创建Bucket
     *
     * @param bucketName Bucket名
     */
    void createBucket(String bucketName);

    /**
     * 上传文件
     *
     * @param filePath   本地文件地址
     * @param bucketName 指定bucket，传空或""为默认 hb-admin-oss
     * @return 上传路径bucketPath
     */
    String upLoadFile(String filePath, String bucketName);


    /**
     * 删除文件
     *
     * @param bucketPath bucket中的地址
     * @param bucketName 指定bucket，传空或""为默认 hb-admin-oss
     * @return true = 删除成功
     */
    boolean deleteFile(String bucketPath, String bucketName);


    /**
     * 下载文件到流
     *
     * @param bucketPath bucket中的地址
     * @param bucketName 指定bucket，传空或""为默认 hb-admin-oss
     * @return InputStream流
     */
    InputStream downFile(String bucketPath, String bucketName);
}
