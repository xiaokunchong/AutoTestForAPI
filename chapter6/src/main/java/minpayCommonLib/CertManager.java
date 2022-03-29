package mfipsgetway.test.cmm;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;


/**
 * 证书管理
 * @author dou7
 *
 */
public class CertManager {
	
	
	private CertManager () { }
    private static class CertManagerInner {
    	private static CertManager instance = new CertManager();
    }
    public static CertManager shareManager()
    {
    	return CertManagerInner.instance;
    }
    
    
    /**
	 * 加载X509证书对象
	 * @param certPath
	 * @return
	 * @throws CertificateException 
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	public X509Certificate loadCertificate(String certPath) throws CertificateException, FileNotFoundException 
	{
		 CertificateFactory certificatefactory=CertificateFactory.getInstance("X.509");
	     FileInputStream in=new FileInputStream(certPath);
	     X509Certificate Cert = (X509Certificate)certificatefactory.generateCertificate(in);
	     return Cert;
	}
	public X509Certificate loadCertificateinputStream(String certPath) throws CertificateException, FileNotFoundException 
	{
		 CertificateFactory certificatefactory=CertificateFactory.getInstance("X.509");
	     InputStream in= CertManager.class.getResourceAsStream(certPath);
	     X509Certificate Cert = (X509Certificate)certificatefactory.generateCertificate(in);
	     return Cert;
	}
	
	
	/**
     * 从HEX公钥证书数据中加载公钥
     * @param certHexString HEX公钥证书数据
     * @return
     * @throws CertificateException
     */
  	 public  X509Certificate loadCertificateFromHexData(String certHexString) throws CertificateException  {
  		
  		byte[] certData = GlobalHelper.shareHelper().hexStringToBytes(certHexString);
    	X509Certificate x509Cert = loadCertificateFromData(certData);
    	return x509Cert;
  		 
     }
  	 
  	 /**
  	  * 从Base64公钥证书数据中加载公钥
  	  * @param certBase64String
  	  * @return
  	  * @throws CertificateException
  	  */
  	public X509Certificate LoadCertificateByBase64(String certBase64String) throws CertificateException {
        
  		byte [] certData = Base64.getDecoder().decode( certBase64String);
  		X509Certificate x509Cert = loadCertificateFromData(certData);
    	return x509Cert;
  	}
  	 
  	 /**
  	  * 从公钥证书数据中加载公钥X509Certificate
  	  * @param certData
  	  * @return
  	  * @throws CertificateException
  	  */
  	 public  X509Certificate loadCertificateFromData(byte[] certData) throws CertificateException  {
  		
  		CertificateFactory cf = CertificateFactory.getInstance("X.509");
  		ByteArrayInputStream in=new ByteArrayInputStream(certData);
    	X509Certificate x509Cert = (X509Certificate)cf.generateCertificate(in);
    	return x509Cert;
  		 
     }
	
	
	/**
	 * 获取证书ID
	 * @param cert
	 * @return
	 */
	public String getCertID(X509Certificate cert)
	{
		return cert.getSerialNumber().toString(16);
	}
	

	
	
	/** 
	 * 检查证书的有效性
	 * @param cert 证书文件
	 * @param certChain 上级证书链文件
	 * @return
	 */
	public  boolean valifyCert(X509Certificate cert, X509Certificate... certChain) 
	{
	    try {
	        cert.checkValidity(new Date());
	        for(X509Certificate parentcert : certChain)
	        {
	        	 cert.verify(parentcert.getPublicKey());
	        	 cert = parentcert;
	        }
	        return true;
	    } catch (Exception e) {
	 
	    }
	    return false;
	} 
	
	
	/**
	 * 加载密钥库
	 * @param pfxPath p12证书路径 
	 * @param pfxPass  p12证书密码
	 * @return
	 * @throws Exception
	 */
	public  KeyStore loadKeyStoreFromP12(String pfxPath, String pfxPass) throws Exception{
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream in = new FileInputStream(pfxPath);
        char [] keystorePassData = pfxPass==null || pfxPass.equals("") ? null :pfxPass.toCharArray();
        keyStore.load(in, keystorePassData);
        return keyStore;
      
    }
	
	
	public  KeyStore loadKeyStoreFromP12InputStream(String pfxPath, String pfxPass) throws Exception{
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        InputStream in = CertManager.class.getClassLoader().getResourceAsStream(pfxPath);
        char [] keystorePassData = pfxPass==null || pfxPass.equals("") ? null :pfxPass.toCharArray();
        keyStore.load(in, keystorePassData);
        return keyStore;
      
    }
	
	
	/**
	 * 从p12证书文件中读取私钥
	 * @param pfxPath p12证书路径 
	 * @param pfxPass  p12证书密码
	 * @return
	 * @throws Exception
	 */
	public  PrivateKey getPrivateKey(String pfxPath, String pfxPass) throws Exception
	{
		 KeyStore keyStore = loadKeyStoreFromP12(pfxPath,pfxPass);
		 return getPrivateKey(keyStore,pfxPass);
	}
	
	
	/**
	 * 从p12证书文件中读取公钥
	 * @param pfxPath p12证书路径 
	 * @param pfxPass  p12证书密码
	 * @return
	 * @throws Exception
	 */
	public  PublicKey getPublicKey(String pfxPath, String pfxPass) throws Exception
	{
		 KeyStore keyStore = loadKeyStoreFromP12(pfxPath,pfxPass);
		 return getPublicKey(keyStore);
	}
	
	
	/**
	 * 从p12证书文件中读取公钥
	 * @param pfxPath p12证书路径 
	 * @param pfxPass  p12证书密码
	 * @return
	 * @throws Exception
	 */
	public  X509Certificate getCertificate(String pfxPath, String pfxPass) throws Exception
	{
		 KeyStore keyStore = loadKeyStoreFromP12(pfxPath,pfxPass);
		 return getCertificate(keyStore);
	}
	
	
	/**
	 * 从证书库获取私钥
	 * @param keyStore 证书库
	 * @param keyPassword 密钥密码，没有则为空
	 * @return
	 * @throws Exception
	 */
	public  PrivateKey getPrivateKey(KeyStore keyStore, String keyPassword) throws Exception{
    	
    	String alias = "";
    	Enumeration<String> aliases = keyStore.aliases();
		if (aliases.hasMoreElements())  
        {  
			//取第一个存储别名
			alias = (String)aliases.nextElement();  
        }   
    	char[] keyPwd = keyPassword ==null || keyPassword.equals("") ?  null : keyPassword.toCharArray();
    	return (PrivateKey)keyStore.getKey(alias,keyPwd);
    }
	

	/**
	 * 从证书库获取公钥
	 * @param keyStore 证书库
	 * @return
	 * @throws Exception
	 */
	public X509Certificate getCertificate(KeyStore keyStore) throws Exception
	{
		String alias = "";
    	Enumeration<String> aliases = keyStore.aliases();
		if (aliases.hasMoreElements())  
        {  
			//取第一个存储别名
			alias = (String)aliases.nextElement();  
        } 
		return  (X509Certificate)keyStore.getCertificate(alias);
	}
	
	/**
	 * 从证书库获取公钥
	 * @param keyStore 证书库
	 * @return
	 * @throws Exception
	 */
	public PublicKey getPublicKey(KeyStore keyStore) throws Exception
	{
		return  getCertificate(keyStore).getPublicKey();
	}
    
	/**
	 * 获取公钥十六进制字符串
	 * @param cert 公钥
	 * @return
	 * @throws Exception
	 */
	public String GetCertHexData(X509Certificate cert) throws Exception
	{
		String hexData = GlobalHelper.shareHelper().bytesToHexString(cert.getEncoded(), true);
		return hexData;
	}
	
	/**
	 * 获取公钥十六进制字符串
	 * @param publicKey 公钥
	 * @return
	 */
	public String GetCertHexData(PublicKey publicKey)
	{
		String hexData = GlobalHelper.shareHelper().bytesToHexString(publicKey.getEncoded(), true);
		return hexData;
	}
	

}
