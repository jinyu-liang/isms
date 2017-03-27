package com.is.utils.keyUtils;

import java.util.Random;

import com.is.utils.StringUtils;
import com.is.utils.date.DateUtil;

/**
 * 根据规则获取主键序列号
 * 
 * @author 
 * 
 */
public class KeyGen {
    /**
     * 传入键值定义规则的名称,返回键值。使用方法，直接在文件中调用 使用于普通id生成
     * 
     * @param keyName
     * @return
     * @author 
     */
    public static String getCommonKeyGen(String keyName) {
        if (StringUtils.isEmpty(keyName)) {
            throw new NullPointerException("主键规则名称不能为空");
        }
        return keyName + DateUtil.getCurDateTimeMil() + randomValue(8);
    }
    
    /**
     * 传入键值定义规则的名称,返回键值。使用方法，直接在文件中调用 使用于普通id生成,计划表出门单生成的图片id
     * 
     * @param keyName
     * @return
     * @author WYS
     */
    public static String getCommonKeyGenImage() {
         
        return "IM" + DateUtil.getCurDateTimeMil() + randomValue(4);
    }
    
  
    /**
     * 获取传入参数长度的随机数字字符串
     * 
     * @param length
     * @return
     */
    public static String randomValue(int length) {
        String randomValue = ""; 
        if (length <= 0) {
            return randomValue;
        }
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomValue = randomValue + random.nextInt(10);
        }
        return randomValue;
    }
}
