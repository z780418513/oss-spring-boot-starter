package com.hb.storage.service;

import com.hb.storage.exception.StoreException;
import org.springframework.lang.Nullable;

import java.io.InputStream;

public interface FileOpeService {

    //------------------------------------------- 上传 -------------------------------------------

    /**
     * 上传文件
     *
     * @param localFilePath 本地文件地址
     * @return 上传路径objectName
     */
    String uploadFile(String localFilePath) throws StoreException;

    /**
     * 上传文件
     *
     * @param localFilePath 本地文件地址
     * @param remoteFileDir 远程文件目录
     * @return 上传路径objectName
     */
    String uploadFile(String localFilePath, @Nullable String remoteFileDir) throws StoreException;

    /**
     * 上传文件
     *
     * @param localFilePath 本地文件地址
     * @param objectName    上传后objectName
     * @return 上传路径objectName
     */
    String uploadFileWithObjectName(String localFilePath, String objectName) throws StoreException;

    /**
     * 上传文件流
     *
     * @param inputStream 文件流
     * @param fileName    上传文件名
     * @return 上传路径objectName
     */
    String uploadInputStream(InputStream inputStream, String fileName) throws StoreException;

    /**
     * 上传文件流
     *
     * @param inputStream   文件流
     * @param fileName      上传文件名
     * @param remoteFileDir 远程目录
     * @return 上传路径objectName
     */
    String uploadInputStream(InputStream inputStream, String fileName, @Nullable String remoteFileDir) throws StoreException;

    /**
     * 上传文件
     *
     * @param inputStream 文件流
     * @param objectName  上传后objectName
     * @return 上传路径objectName
     */
    String uploadInputStreamWithObjectName(InputStream inputStream, String objectName) throws StoreException;


    //------------------------------------------- 下载 -------------------------------------------

    /**
     * 下载文件到流
     *
     * @param objectName bucket中的地址
     * @return InputStream流
     */
    InputStream downLoadFile(String objectName) throws StoreException;

    /**
     * 下载文件到本地
     *
     * @param objectName bucket中的地址
     * @return true= 成功
     */
    boolean downLoadFile(String objectName, String localFileDir) throws StoreException;

    /**
     * 下载文件，并解析成字符串文本
     *
     * @param objectName bucket中的地址
     * @return 下载文件内容
     */
    String downLoadFileContext(String objectName) throws StoreException;


    //------------------------------------------- 删除 -------------------------------------------

    /**
     * 删除文件
     *
     * @param objectName bucket中的地址
     */
    void deleteFile(String objectName) throws StoreException;


    //------------------------------------------- 查询 -------------------------------------------

    /**
     * 文件是否存在
     *
     * @param objectName bucket中的地址
     * @return true = 存在
     * @throws StoreException
     */
    boolean fileExist(String objectName) throws StoreException;

}
