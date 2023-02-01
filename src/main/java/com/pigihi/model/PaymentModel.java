/**
 * 
 */
package com.pigihi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for accepting order and amount
 * 
 * @author Ashish Sam T George
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModel {
	
	String orderId;
	String amount;

}
