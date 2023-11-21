package com.hb.storage.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author: zhaochengshui
 * @description 文件工具类
 * @date: 2023/11/17 15:34
 */
public class FileHelper {

    public static String generateFilePathByDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(date);
    }


    public static String generateFileByUUID(String fileName) {
        String filePath = generateFilePathByDate();
        String uuid = UUID.randomUUID().toString();
        return filePath + uuid + "." + getFileSuffix(fileName);
    }

    public static String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    public static String getFileSuffix(String fileName) {
        String[] split = fileName.split("\\.");
        return split[split.length - 1];
    }

    public static String getFilePath(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("/"));
    }

}
