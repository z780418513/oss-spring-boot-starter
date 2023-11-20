package com.hb.storage.service.impl;

import cn.hutool.core.io.IoUtil;
import com.hb.storage.exception.StoreException;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author: zhaochengshui
 * @description AbstractStorageService
 * @date: 2023/11/20 10:36
 */
public abstract class AbstractStorageService {

    /**
     * 下载文件，并解析成字符串文本
     *
     * @param objectName bucket中的地址
     * @return 下载文件内容
     */
    public String downLoadFileContext(String objectName) throws StoreException{
        InputStream inputStream = downLoadFile(objectName);
        return IoUtil.read(inputStream, Charset.defaultCharset());
    }


    public abstract InputStream downLoadFile(String objectName);
}
