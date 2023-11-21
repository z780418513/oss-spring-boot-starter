package com.hb.storage.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.CharSequenceUtil;
import com.hb.storage.exception.StoreException;
import com.hb.storage.util.FileHelper;
import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author: zhaochengshui
 * @description AbstractStorageService
 * @date: 2023/11/20 10:36
 */
public abstract class AbstractStorageService {

    /**
     * 获取上传文件ObjectName
     * @param originFileName
     * @param remoteFileDir
     * @param useOriginFileName
     * @return
     */
    public String getObjectName(String originFileName, @Nullable String remoteFileDir, boolean useOriginFileName) {
        String fileDir = remoteFileDir;
        if (CharSequenceUtil.isBlank(remoteFileDir)) {
            // 远程文件目录
            fileDir = FileHelper.generateFilePathByDate();
        }
        String fileName = originFileName;
        if (!useOriginFileName) {
            String fileSuffix = FileHelper.getFileSuffix(originFileName);
            fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileSuffix;
        }
        return fileDir + "/" + fileName;
    }

    /**
     * 下载文件，并解析成字符串文本
     *
     * @param objectName bucket中的地址
     * @return 下载文件内容
     */
    public String downLoadFileContext(String objectName) throws StoreException {
        InputStream inputStream = downLoadFile(objectName);
        return IoUtil.read(inputStream, Charset.defaultCharset());
    }


    public abstract InputStream downLoadFile(String objectName);


}
