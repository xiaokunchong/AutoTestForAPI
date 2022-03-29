package minpayCommonLib;

import com.minpay.facade.mcorder.dto.SignPaySmsRequestDTO;

public class SignPaySmsRequestDTO1 extends SignPaySmsRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merchantInfo;
	
	private String separateAccount;

	public String getSeparateAccount() {
		return separateAccount;
	}

	public void setSeparateAccount(String separateAccount) {
		this.separateAccount = separateAccount;
	}

	public String getMerchantInfo() {
		return merchantInfo;
	}

	public void setMerchantInfo(String merchantInfo) {
		this.merchantInfo = merchantInfo;
	}
	
}
