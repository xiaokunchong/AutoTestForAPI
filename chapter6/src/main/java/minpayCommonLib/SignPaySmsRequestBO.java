package minpayCommonLib;

import com.minpay.facade.mcorder.dto.SignPaySmsRequestDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 *短验支付
 * @author jiahongming
 */
public class SignPaySmsRequestBO extends BaseBO implements Serializable {

    private SignPaySmsRequestDTO msgBody;

    public SignPaySmsRequestDTO getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(SignPaySmsRequestDTO msgBody) {
        this.msgBody = msgBody;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
