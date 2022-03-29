package minpayCommonLib;


import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;




/**
 * ����������
 * @author dou7
 *
 */
public class GlobalHelper {
	
    /**
	 * ��������
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
	 * �ж������Ƿ�Ϊ �������͡���װ���͡�String����
	 * @return
	 */
	public  boolean isBaseType(Class<?> objClass)
	{
		return BASE_TYPES.contains(objClass.getName());
	}
    
    /**
     * ��ȡ��ʽ����ǰʱ��
     */
    public String getNow(String format)
    {
    	SimpleDateFormat dateFormat =   new SimpleDateFormat(format);
		String nowstr = dateFormat.format(new Date());
		return nowstr;
    }
    
    /**
     * ʱ���ʽ��
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
	 * @see ����AES256�����Կ
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
	 * 16�����ַ���ת�ֽ�����
	 * @param hexString 16�����ַ���
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
	  * �ֽ�����ת16�����ַ��� [��д]
	  * @param data �ֽ�����
	  * @return
	  */
	 public  String bytesToHexString(byte [] data)
	 {
		 return bytesToHexString(data,true);
	 }
	 
	 /**
		 * Base64����
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
		 * Base64����
		 * @param data
		 * @return
		 */
		public String base64Encode(byte[] data )
		{
			return Base64.encode(data).replaceAll("[\\s*\t\n\r]", "");
		}
		
		/**
		 * Base64����
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
		 * Base64����
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
     * �ֽ�����ת16�����ַ���
     * @param data �ֽ�����
     * @param useUpperCase �Ƿ�ʹ�ô�д��ĸ
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
	 * AES 256 ECB ����
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
	 * AES 256 ECB ����
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
     * RSAǩ�� 
     * @param content ��ǩ������ 
     * @param privateKey ˽Կ 
     * @param encode �ַ������� 
     * @return ǩ��ֵ 
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
     * RSA��ǩ����� 
     * @param content ��ǩ������ 
     * @param sign ǩ��ֵ 
     * @param publicKey ��Կ 
     * @param encode �ַ������� 
     * @return ����ֵ 
     */  
     public  boolean verifySign(String content, String signValue, PublicKey publicKey,String encoding) throws Exception
     {
    	 Signature signature = Signature.getInstance("SHA256withRSA");
         signature.initVerify(publicKey);  
         signature.update( content.getBytes(encoding));  
         byte[] signData = base64Decode(signValue);		
         boolean isOk = signature.verify(signData );  
         return isOk;  
     }
     
     
     /**
      * RSA ��Կ����
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
      * RSA˽Կ����
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
      * RSA˽Կ����
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
      * RSA��Կ ����
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
