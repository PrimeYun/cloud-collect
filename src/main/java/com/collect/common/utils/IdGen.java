package com.collect.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

import cn.hutool.core.util.IdUtil;


/**
 * 
 * 封装各种生成唯一性ID算法的工具类.
 */
public class IdGen implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private static SecureRandom random = new SecureRandom();

    // 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 使用SecureRandom随机生成Long.
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    // 基于Base62编码的SecureRandom随机生成bytes.
    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return CryptoUtils.encodeBase62(randomBytes);
    }
    
    // 基于 MongoDB ObjectId 算法
    public static String getObjectId() {
    	return IdUtil.objectId();
    }
    
}
