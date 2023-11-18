package com.hb.storage.service;

import com.hb.storage.exception.StoreException;

/**
 * 一般情况下，不会由开发人员在代码中直接调用BucketService的接口方法
 *
 * @author: zhaochengshui
 * @description 桶创建服务类
 * @date: 2023/11/18 14:48
 */
public interface BucketService {

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
}
