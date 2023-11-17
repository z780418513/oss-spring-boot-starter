package com.hb.storage.enums;

/**
 * @author: zhaochengshui
 * @description 云存枚举
 * @date: 2023/11/17 14:05
 */
public enum StorageEnum {
    /**
     * 阿里OSS
     */
    ALI_OSS(1),
    OTHER(0);

    StorageEnum(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return code;
    }

}
