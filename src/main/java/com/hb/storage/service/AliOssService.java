package com.hb.storage.service;

import com.hb.storage.exception.StoreException;

import java.io.InputStream;

/**
 * @author zhaochengshui
 * @description 阿里云OSS服务类
 * @date 2022/9/22
 */
public interface AliOssService {

    /**
     * 创建Bucket,如果Bucket已存在则不做任何操作
     */
    void createBucket() throws StoreException;


    /**
     * 删除Bucket
     */
    void deleteBuket() throws StoreException;

    /**
     * 判断Bucket是否存在
     *
     * @return true = 存在
     */
    boolean existBuket() throws StoreException;


    /**
     * 上传文件
     *
     * @param filePath 本地文件地址
     * @return 上传路径objectName
     */
    String upLoadFile(String filePath) throws StoreException;


    /**
     * 删除文件
     *
     * @param objectName bucket中的地址
     */
    void deleteFile(String objectName) throws StoreException;

    /**
     * 文件是否存在
     *
     * @param objectName bucket中的地址
     * @return true = 存在
     * @throws StoreException
     */
    boolean fileExist(String objectName) throws StoreException;


    /**
     * 下载文件到流
     *
     * @param objectName bucket中的地址
     * @return InputStream流
     */
    InputStream downLoadFile(String objectName) throws StoreException;

    /**
     * 下载文件，并解析成字符串文本
     *
     * @param objectName bucket中的地址
     * @return 下载文件内容
     */
    String downLoadFileContext(String objectName) throws StoreException;


}
