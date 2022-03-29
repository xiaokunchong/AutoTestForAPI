package mfapisdk.utils;


import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;




/**
 * 公共助手类
 * @author dou7
 *
 */
public class GlobalHelper {
	
    /**
	 * 基本类型
	 */
	 private static final ArrayList<String> BASE_TYPES = new ArrayList<String>(Arrays.asList("java.lang.String",
				"int","double","long","short","byte","boolean","char","float",
				"java.util.Date",
				"java.lang.Boolean",
				"java.lang.Integer",
		        "java.lang.Double",
		        "java.lang.Float",
		        "java.lang.Long",
		        "java.lang.Short",
		        "java.lang.Byte",
		        "java.lang.Character"));

	private GlobalHelper() { }
    private static class GlobalHelperInner {
    	private static GlobalHelper instance = new GlobalHelper();
    }
    public static GlobalHelper shareHelper()
    {
    	return GlobalHelperInner.instance;
    }
    

	 
	/**
	 * 判断类型是否为 基本类型、包装类型、String类型
	 * @return
	 */
	public  boolean isBaseType(Class<?> objClass)
	{
		return BASE_TYPES.contains(objClass.getName());
	}
    
    /**
     * 获取格式化当前时间
     */
    public String getNow(String format)
    {
    	SimpleDateFormat dateFormat =   new SimpleDateFormat(format);
		String nowstr = dateFormat.format(new Date());
		return nowstr;
    }
    
    /**
     * 时间格式化
     * @param date
     * @param format
     * @return
     */
    public String dateFormat(Date date,String format)
    {
    	SimpleDateFormat dateFormat =   new SimpleDateFormat(format);
		String nowstr = dateFormat.format(date);
		return nowstr;
    }
    
    /**
	 * @see 生成AES256随机密钥
	 */
	public String randomEncryptKey()
	{
		String stringSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < 32; i++) {     
            int number = random.nextInt(stringSet.length());     
            sb.append(stringSet.charAt(number));     
        }     
        return sb.toString(); 
	}
	
	/**
	 * 16进制字符串转字节数据
	 * @param hexString 16进制字符串
	 * @return
	 */
	 public  byte[] hexStringToBytes(String hexString) 
	 {
        byte[] bytes = new byte[hexString.length() / 2];
        for(int i = 0; i < hexString.length() / 2; i++) {
            String subStr = hexString.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
     }
	 
	 /**
	  * 字节数据转16进制字符串 [大写]
	  * @param data 字节数据
	  * @return
	  */
	 public  String bytesToHexString(byte [] data)
	 {
		 return bytesToHexString(data,true);
	 }
	 
	 /**
		 * Base64编码
		 * @param data
		 * @param encoding
		 * @return
		 * @throws UnsupportedEncodingException 
		 */
		public String base64Encode(String data,String encoding) throws UnsupportedEncodingException
		{
			return base64Encode(data.getBytes(encoding));
		}
		
		
		/**
		 * Base64编码
		 * @param data
		 * @return
		 */
		public String base64Encode(byte[] data )
		{
			return Base64.encode(data).replaceAll("[\\s*\t\n\r]", "");
		}
		
		/**
		 * Base64解码
		 * @param base64String
		 * @param encoding
		 * @return
		 * @throws Base64DecodingException 
		 * @throws IPSException 
		 * @throws UnsupportedEncodingException
		 */
		public String base64Decode(String base64String,String encoding) throws UnsupportedEncodingException, Base64DecodingException  
		{
			return new String(base64Decode(base64String),encoding);
		}
		
		/**
		 * Base64解码
		 * @param base64String
		 * @return
		 * @throws Base64DecodingException 
		 */
		public byte[] base64Decode(String base64String) throws Base64DecodingException
		{
			byte[] data = Base64.decode(base64String);
			return data;
		}
	 
	/**
     * 字节数据转16进制字符串
     * @param data 字节数据
     * @param useUpperCase 是否使用大写字母
     * @return
     */
	public  String bytesToHexString(byte [] data,boolean useUpperCase)
	{
		StringBuilder buf = new StringBuilder(data.length * 2);
        for(byte b : data) { 
            buf.append(String.format(useUpperCase ? "%02X" :"%02x",
            		new Integer(b & 0xff)));
        }
        return buf.toString();
	}
	
	
	
	/**
	 * AES 256 ECB 加密
	 * @param plaintext
	 * @param key
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String plaintext,String key,String encoding) throws Exception
	{
		if(plaintext == null || plaintext.equals(""))
		{
			return "";
		}
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    	SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(encoding), "AES");
		cipher.init(Cipher.ENCRYPT_MODE , skeySpec);
		 byte[] encrypted = cipher.doFinal(plaintext.getBytes(encoding));
	    return  base64Encode(encrypted); 
        	
	}
	
	
	/**
	 * AES 256 ECB 解密
	 * @param ciphertext
	 * @param key
	 * @param encoding
	 * @return
	 */
	public String decrypt(String ciphertext,String key,String encoding) throws Exception {
    	
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    	SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(encoding), "AES");
		cipher.init(Cipher.DECRYPT_MODE , skeySpec);
		byte[] encrypted = base64Decode(ciphertext);
		byte[] original = cipher.doFinal(encrypted);
        String originalString = new String(original, encoding);
        return originalString;
    }
	
	
	/** 
     * RSA签名 
     * @param content 待签名数据 
     * @param privateKey 私钥 
     * @param encode 字符集编码 
     * @return 签名值 
     */  
    public  String sign(String content, PrivateKey privateKey,String encoding) throws Exception  
    {
    	Signature signature = Signature.getInstance("SHA256withRSA");  
        signature.initSign(privateKey);  
        signature.update(content.getBytes(encoding));  
        byte[] signed = signature.sign();  
        return base64Encode(signed);
    }
    
    /** 
     * RSA验签名检查 
     * @param content 待签名数据 
     * @param sign 签名值 
     * @param publicKey 公钥 
     * @param encode 字符集编码 
     * @return 布尔值 
     */  
     public  boolean verifySign(String content, String signValue, PublicKey publicKey,String encoding) throws Exception
     {
    	 Signature signature = java.security.Signature.getInstance("SHA256withRSA");  
         signature.initVerify(publicKey);  
         signature.update( content.getBytes(encoding));  
         byte[] signData = base64Decode(signValue);		
         boolean isOk = signature.verify(signData );  
         return isOk;  
     }
     
     
     /**
      * RSA 公钥加密
      * @param plaintext
      * @param publicKey
      * @param encoding
      * @return
      * @throws Exception
      */
     public  String rsaEncrypt(String plaintext,PublicKey publicKey,String encoding) throws Exception
     {
     	 Cipher cipher = Cipher.getInstance("RSA","SunJCE");  
         cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
         byte[] encrypted = cipher.doFinal(plaintext.getBytes(encoding));  
         return base64Encode(encrypted);
     }
     
     /**
      * RSA私钥加密
      * @param plaintext
      * @param privateKey
      * @param encoding
      * @return
      * @throws Exception
      */
     public  String rsaEncrypt(String plaintext,PrivateKey privateKey,String encoding) throws Exception
     {
    	 Cipher cipher = Cipher.getInstance("RSA","SunJCE");  
         cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
         byte[] encrypted = cipher.doFinal(plaintext.getBytes(encoding));  
         return base64Encode(encrypted);
     }
     
     /** 
      * RSA私钥解密
      * @param ciphertext
      * @param privateKey
      * @param encoding
      * @return
      * @throws Exception
      */
     public  String rsaDecrypt(String ciphertext,PrivateKey privateKey,String encoding) throws Exception  
     {  
    	 byte[] cipherData = base64Decode(ciphertext);
     	 Cipher cipher = Cipher.getInstance("RSA","SunJCE");   
         cipher.init(Cipher.DECRYPT_MODE, privateKey);  
         byte[] original = cipher.doFinal(cipherData);  
	     String originalString = new String(original, encoding);
	     return originalString;
     }  
     
     
     /** 
      * RSA公钥 解密
      * @param ciphertext
      * @param publicKey
      * @param encoding
      * @return
      * @throws Exception
      */
     public  String rsaDecrypt(String ciphertext,PublicKey publicKey,String encoding) throws Exception  
     {  
    	 byte[] cipherData = base64Decode(ciphertext);
     	 Cipher cipher = Cipher.getInstance("RSA","SunJCE");   
         cipher.init(Cipher.DECRYPT_MODE, publicKey);  
         byte[] original = cipher.doFinal(cipherData);  
	     String originalString = new String(original, encoding);
	     return originalString;
     }  
     
     
     
    
	
    
}
