/**
 * 
 */
package com.pigihi.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import org.json.simple.JSONObject;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.paytm.pg.merchant.PaytmChecksum;


/**
 * Implementation class for fiat payment service interface
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class PaytmPaymentService implements FiatPaymentServiceInterface {

	//STAGING
//	private String MERCHANT_ID = "RUoKbo86921897956224";
//	private String MERCHANT_KEY = "pvwPY0ez8d7fxsGs";
	
	//PRODUCTION
	private String MERCHANT_ID = "SApWAl73540535701479";
	private String MERCHANT_KEY = "37MuHFVCCXnpK1ER";
	
	/**
	 * Make payment through the payment gateway
	 * 
	 * @param orderId
	 * @param amount
	 * @return JSON string
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@Override
	public String makePayment(String orderId, String amount) throws Exception {
		System.out.println("------------Started the Initiate Transaction Process----------------");
		
		Gson gson = new Gson();
		
//		JSONObject paytmParams = new JSONObject();
		
//		JSONObject body = new JSONObject();
		
		JsonObject json = new JsonObject();
		
		JsonObject head = new JsonObject();
		
		JsonObject body = new JsonObject();
		
		body.addProperty("requestType", "Payment");
		//TODO This is Test MID
		body.addProperty("mid", MERCHANT_ID);
//		body.put("websiteName", "CloudStore");
//		body.put("websiteName", "WEBSTAGING");
		body.addProperty("websiteName", "DEFAULT");
		//TODO ORDERID should be unique. Use id of the order document in mongodb
		body.addProperty("orderId", orderId);
		//TODO Update the callbackUrl
//		body.put("callbackUrl", "http://localhost:3000/cart/success");
		body.addProperty("callbackUrl", "http://localhost:8080/user/customer/paymentResponse");
		
		JsonObject txnAmount = new JsonObject();
		//TODO Update the amount
		txnAmount.addProperty("value", amount);
		txnAmount.addProperty("currency", "INR");
		
//		//These are optional
		JsonObject userInfo = new JsonObject();
		userInfo.addProperty("custId", "CUST_001");
		userInfo.addProperty("mobile", "9446028121");
		
		body.add("txnAmount", txnAmount);
		body.add("userInfo", userInfo);
		
		String checksum = PaytmChecksum.generateSignature(body.toString(), MERCHANT_KEY);
		
		head.addProperty("signature", checksum);
		
		json.add("body", body);
		json.add("head", head);
		
		System.out.println("Paytm POST BODY: " + json);
		
//		String post_data = paytmParams.toString();
		String post_data = json.toString();
		
		String stageUrl = "https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?";
		String prodUrl = "https://securegw.paytm.in/theia/api/v1/initiateTransaction?";
		
		URL url = new URL(prodUrl + "mid="+MERCHANT_ID+"&orderId="+orderId);
		
		String responseData = "";
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			
			DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
			requestWriter.writeBytes(post_data);
			requestWriter.close();
//			String responseData = "";
			InputStream is = connection.getInputStream();
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
			if((responseData = responseReader.readLine()) != null) {
				System.out.append("Response: " + responseData);
			}
			responseReader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("--------Finshed the Initiate Transaction Process--------------");
		
		return responseData;
	}
}

