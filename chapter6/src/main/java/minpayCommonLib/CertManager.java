package minpayCommonLib;

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
 * ֤�����
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
	 * ����X509֤�����
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
     * ��HEX��Կ֤�������м��ع�Կ
     * @param certHexString HEX��Կ֤������
     * @return
     * @throws CertificateException
     */
  	 public  X509Certificate loadCertificateFromHexData(String certHexString) throws CertificateException  {
  		
  		byte[] certData = GlobalHelper.shareHelper().hexStringToBytes(certHexString);
    	X509Certificate x509Cert = loadCertificateFromData(certData);
    	return x509Cert;
  		 
     }
  	 
  	 /**
  	  * ��Base64��Կ֤�������м��ع�Կ
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
  	  * �ӹ�Կ֤�������м��ع�ԿX509Certificate
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
	 * ��ȡ֤��ID
	 * @param cert
	 * @return
	 */
	public String getCertID(X509Certificate cert)
	{
		return cert.getSerialNumber().toString(16);
	}
	

	
	
	/** 
	 * ���֤�����Ч��
	 * @param cert ֤���ļ�
	 * @param certChain �ϼ�֤�����ļ�
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
	 * ������Կ��
	 * @param pfxPath p12֤��·�� 
	 * @param pfxPass  p12֤������
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
	 * ��p12֤���ļ��ж�ȡ˽Կ
	 * @param pfxPath p12֤��·�� 
	 * @param pfxPass  p12֤������
	 * @return
	 * @throws Exception
	 */
	public  PrivateKey getPrivateKey(String pfxPath, String pfxPass) throws Exception
	{
		 KeyStore keyStore = loadKeyStoreFromP12(pfxPath,pfxPass);
		 return getPrivateKey(keyStore,pfxPass);
	}
	
	
	/**
	 * ��p12֤���ļ��ж�ȡ��Կ
	 * @param pfxPath p12֤��·�� 
	 * @param pfxPass  p12֤������
	 * @return
	 * @throws Exception
	 */
	public  PublicKey getPublicKey(String pfxPath, String pfxPass) throws Exception
	{
		 KeyStore keyStore = loadKeyStoreFromP12(pfxPath,pfxPass);
		 return getPublicKey(keyStore);
	}
	
	
	/**
	 * ��p12֤���ļ��ж�ȡ��Կ
	 * @param pfxPath p12֤��·�� 
	 * @param pfxPass  p12֤������
	 * @return
	 * @throws Exception
	 */
	public  X509Certificate getCertificate(String pfxPath, String pfxPass) throws Exception
	{
		 KeyStore keyStore = loadKeyStoreFromP12(pfxPath,pfxPass);
		 return getCertificate(keyStore);
	}
	
	
	/**
	 * ��֤����ȡ˽Կ
	 * @param keyStore ֤���
	 * @param keyPassword ��Կ���룬û����Ϊ��
	 * @return
	 * @throws Exception
	 */
	public  PrivateKey getPrivateKey(KeyStore keyStore, String keyPassword) throws Exception{
    	
    	String alias = "";
    	Enumeration<String> aliases = keyStore.aliases();
		if (aliases.hasMoreElements())  
        {  
			//ȡ��һ���洢����
			alias = (String)aliases.nextElement();  
        }   
    	char[] keyPwd = keyPassword ==null || keyPassword.equals("") ?  null : keyPassword.toCharArray();
    	return (PrivateKey)keyStore.getKey(alias,keyPwd);
    }
	

	/**
	 * ��֤����ȡ��Կ
	 * @param keyStore ֤���
	 * @return
	 * @throws Exception
	 */
	public X509Certificate getCertificate(KeyStore keyStore) throws Exception
	{
		String alias = "";
    	Enumeration<String> aliases = keyStore.aliases();
		if (aliases.hasMoreElements())  
        {  
			//ȡ��һ���洢����
			alias = (String)aliases.nextElement();  
        } 
		return  (X509Certificate)keyStore.getCertificate(alias);
	}
	
	/**
	 * ��֤����ȡ��Կ
	 * @param keyStore ֤���
	 * @return
	 * @throws Exception
	 */
	public PublicKey getPublicKey(KeyStore keyStore) throws Exception
	{
		return  getCertificate(keyStore).getPublicKey();
	}
    
	/**
	 * ��ȡ��Կʮ�������ַ���
	 * @param cert ��Կ
	 * @return
	 * @throws Exception
	 */
	public String GetCertHexData(X509Certificate cert) throws Exception
	{
		String hexData = GlobalHelper.shareHelper().bytesToHexString(cert.getEncoded(), true);
		return hexData;
	}
	
	/**
	 * ��ȡ��Կʮ�������ַ���
	 * @param publicKey ��Կ
	 * @return
	 */
	public String GetCertHexData(PublicKey publicKey)
	{
		String hexData = GlobalHelper.shareHelper().bytesToHexString(publicKey.getEncoded(), true);
		return hexData;
	}
	

}
