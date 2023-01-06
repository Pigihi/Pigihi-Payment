/**
 * 
 */
package com.pigihi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ashish Sam T George
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaytmRequestModel {
	Head head;
	Body body;

}

@Data
class Head{
	String signature;
}

@Data
class Body{
	String requestType;
	String mid;
	String websiteName;
	String orderId;
	String callbackUrl;
	
	TxnAmount txnAmount;
	
}

@Data
class UserInfo{
	String custId;
	String mobile;
}

@Data
class TxnAmount{
	String value;
	String currency;
}
