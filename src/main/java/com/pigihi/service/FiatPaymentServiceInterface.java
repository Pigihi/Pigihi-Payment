/**
 * 
 */
package com.pigihi.service;

/**
 * Interface for fiat payment service
 * 
 * @author Ashish Sam T George
 *
 */
public interface FiatPaymentServiceInterface {

	String makePayment(String orderId, String amount) throws Exception;

}
