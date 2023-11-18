package com.hb.storage.exception;

import com.hb.storage.enums.StorageEnum;
import com.hb.storage.enums.StoreOperationEnum;

/**
 * @author: zhaochengshui
 * @description 云存异常类
 * @date: 2023/11/17 14:01
 */
public class StoreException extends RuntimeException {

    /**
     * 错误消息
     */
    private final String message;
    /**
     * 存储类型code
     */
    private final int storeCode;
    /**
     * 操作类型
     */
    private final int operationType;


    public StoreException(StorageEnum storageEnum, StoreOperationEnum operationEnum, String message) {
        super(message);
        this.message = message;
        this.storeCode = storageEnum.getCode();
        this.operationType = operationEnum.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public int getOperationType() {
        return operationType;
    }
}
