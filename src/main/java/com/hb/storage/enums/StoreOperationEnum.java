package com.hb.storage.enums;

/**
 * @author: zhaochengshui
 * @description 云存储操作枚举
 * @project: spring-boot-storage
 * @date: 2023/11/17 14:54
 */
public enum StoreOperationEnum {
    /**
     * 创建桶
     */
    CREATE_BUCKET(1),
    /**
     * 查询桶
     */
    QUERY_BUCKET(2),
    /**
     * 删除桶
     */
    DELETE_BUCKET(3),


    /**
     * 上传文件
     */
    UPLOAD_FILE(11),
    /**
     * 删除文件
     */
    DELETE_FILE(12),
    /**
     * 下载文件
     */
    DOWNLOAD_FILE(13),
    /**
     * 文件查询
     */
    QUERY_FILE(14),


    /**
     * 其他操作
     */
    OTHER(0);

    StoreOperationEnum(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }


}
