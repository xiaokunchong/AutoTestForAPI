package com.minpay.api.bos;

//import com.minpay.facade.cashier.pay.dto.base.BaseRequestDTO;

import javax.validation.constraints.NotBlank;

public class SignPaySmsRequestDTO extends BaseBO {

    @NotBlank
    private String payOrderNo;
    /**
     * 閾惰鍗″彿
     */
    @NotBlank
    private String bankCardNo;

    /**
     * 鎸佸崱浜哄鍚�
     */
    @NotBlank
    private String bankCardOwner;

    /**
     * 璇佷欢鍙�
     */
    @NotBlank
    private String idCardNo;

    /**
     * 璇佷欢绫诲瀷
     */
    @NotBlank
    private String idCardType;

    /**
     * 鍗＄被鍨�
     */
//    private String cardType;

    @NotBlank
    private String phone;

    @NotBlank
    private String payerIp;

    @NotBlank
    private String payProduct;
    private String requestId;
    private String  payFlowNo;
    private String smsCode;
    private String remitDate;
    private String notifyPhone;
    private String userIp;
    private String cardType;
    private String bankCode;
    private String payProductCode;
    private String business;
    
    private String payerAccount;
    private String payPassword;
    private String verificationCode;
    private String merchantRequestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }


    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardOwner() {
        return bankCardOwner;
    }

    public void setBankCardOwner(String bankCardOwner) {
        this.bankCardOwner = bankCardOwner;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getPayerIp() {
        return payerIp;
    }
    

    public void setPayerIp(String payerIp) {
        this.payerIp = payerIp;
    }

    public String getPayProduct() {
        return payProduct;
    }

    public void setPayProduct(String payProduct) {
        this.payProduct = payProduct;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }

	public void settRequestId(String string) {
		// TODO Auto-generated method stub
		
	}
	 public String getPayFlowNo() {
	        return payFlowNo;
	    }

	public void setPayFlowNo(String payFlowNo) {
		// TODO Auto-generated method stub
		
		this.payFlowNo = payFlowNo;
	}
	
	 public String getSmsCode() {
	        return smsCode;
	    }

	public void setSmsCode(String smsCode) {
		// TODO Auto-generated method stub
		
		this.smsCode = smsCode;
	}

	public void setRemitDate(String remitDate) {
		// TODO Auto-generated method stub
		this.remitDate= remitDate;
	}
	
	 public String getRemitDate() {
	        return remitDate;
	    }

	public void setNotifyPhone(String notifyPhone) {
		// TODO Auto-generated method stub
		this.notifyPhone=notifyPhone;
	}
	
	 public String getNotifyPhone() {
	        return notifyPhone;
	    }

	public void setUserIp(String userIp) {
		// TODO Auto-generated method stub
		this.userIp = userIp;
	}
	
	public String getUserIp() {
        return userIp;
    }

	public void setCardType(String cardType) {
		// TODO Auto-generated method stub
		this.cardType = cardType;
	}
	
	public String getCardType() {
        return cardType;
    }

	public void setBankCode(String bankCode) {
		// TODO Auto-generated method stub
		this.bankCode = bankCode;
	}
	
	public String getBankCode() {
        return bankCode;
    }

	public void setPayProductCode(String payProductCode) {
		// TODO Auto-generated method stub
		this.payProductCode = payProductCode;
	}

	public String getPayProductCode() {
        return payProductCode;
    }

	public void setBusiness(String business) {
		// TODO Auto-generated method stub
		this.business = business;
	}
	
	public String getBusiness() {
        return business;
    }

	
	public String getPayerAccount() {
        return payerAccount;
    }
	public void setPayerAccount(String payerAccount) {
		// TODO Auto-generated method stub
		this.payerAccount=payerAccount;
	}

	
	public String getPayPassword() {
        return payPassword;
    }
	public void setPayPassword(String payPassword) {
		// TODO Auto-generated method stub
		this.payPassword = payPassword;
	
	}
	
	
	public String getVerificationCode() {
        return verificationCode;
    }
	
	public void setVerificationCode(String verificationCode) {
		// TODO Auto-generated method stub
		 this.verificationCode = verificationCode;
	}
	
	public String getMerchantRequestId() {
        return merchantRequestId;
    }

	public void setMerchantRequestId(String merchantRequestId) {
		// TODO Auto-generated method stub
		this.merchantRequestId = merchantRequestId;
	}
	
	
	

	
	
}