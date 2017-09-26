package com.yhp.seckill.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yuhuiping
 *
 */
public class MD5Utils {

	private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);  
	   
    /** 
     * 对一段Long类型的数据生成MD5加密信息 
     *  
     * @param message 
     *            要加密的String 
     * @return 生成的MD5信息 
     */  
    public static String getMD5(String message) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            byte[] b = md.digest(message.getBytes());  
            return byteToHexStringSingle(b);// byteToHexString(b);  
        } catch (NoSuchAlgorithmException e) {  
        	logger.error(e.getMessage());
        } 
        return null;  
    }  
	  
    /** 
     * 独立把byte[]数组转换成十六进制字符串表示形式 
     * @param byteArray 
     * @return 
     */  
    public static String byteToHexStringSingle(byte[] byteArray) {  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {  
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(  
                        Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }  

}
