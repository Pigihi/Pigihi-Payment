/**
 * 
 */
package com.pigihi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.pigihi.service.FiatPaymentServiceInterface;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller class for handling fiat payments
 * 
 * @author Ashish Sam T George
 *
 */
@RestController
public class FiatPaymentController {
	
	@Autowired
	private FiatPaymentServiceInterface paytmPaymentService;
	
	//TODO Service for creating order has not been implemented. Maybe, do it in a separate microservice
	//TODO Service for storing address has not been implemented. Maybe, do it in a separate microservice
	
	@CrossOrigin("*")
	@PostMapping("/makePayment")
	public String makePayment() throws Exception {
		//TODO Accept orderId and amount
		String orderId = "";
		String amount = "";
		String response = paytmPaymentService.makePayment(orderId, amount);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(response);
		jsonElement.getAsJsonObject().addProperty("orderId", orderId);
		return gson.toJson(jsonElement);
	}
	
	/**
	 * 
	 * @author Ashish Sam T George
	 */
	@CrossOrigin("*")
	@PostMapping("/paymentResponse")
	public void paymentResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String responseMsg = request.getParameter("RESPMSG");
		String responseCode = request.getParameter("RESPCODE");
		String orderId = request.getParameter("ORDERID");
		
		if(responseCode.equalsIgnoreCase("01")) {
			response.sendRedirect("http://localhost:3000/cart/success?orderId=" + orderId);
		}
		else {
			response.sendRedirect("http://localhost:3000/cart/failure?errorMessage=" + responseMsg);
		}
	}
}
