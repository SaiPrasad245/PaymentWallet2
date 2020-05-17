package com.cg.sprint2.transferamount.ms.testservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import com.cg.sprint2.transferamount.ms.service.TransferAmountService;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestService {
	@Autowired
	TransferAmountService taservice;

	@Test
    public void testTransferAmountToAnotherWallet_Positive() throws Exception
    {
		String response =taservice.transferAmountToAnotherWalletUser("7799208319", "9398165142", 100);
		Assertions.assertEquals("Transaction Sucess---->Amount Transferred", response);
		
    }
	@Test
    public void testTransferAmountToAnotherWallet_Negative() throws Exception
    {
		String response =taservice.transferAmountToAnotherWalletUser("7799208319", "9398165142", 10000);
		Assertions.assertEquals("Transaction Failed : Err --- Insufficient Balance",response);
		
    }
	@Test
    public void testTransferAmountToAnotherWallet_Negative1() throws Exception
    {
		String response =taservice.transferAmountToAnotherWalletUser("7799208319", "9398165242", 100);
		Assertions.assertEquals("Transaction Failed : Err ---  The Number that the money is to be sent has to be Registerd on the Application", response);
		
    }
	@Test
    public void testUpiTransfer_Positive() throws Exception
    {
		String response =taservice.upiTransfer("9398165142","7799208319",100,8521 );
		Assertions.assertEquals("Transaction Sucess:Amount Transferred", response);
		
    }
	@Test
    public void testUpiTransfer_Negative() throws Exception
    {
		String response =taservice.upiTransfer("9398165142","7799208319",8523,100 );
		Assertions.assertEquals("Transaction Failed : Err ---> Incorrect PIN", response);
		
    }
	@Test
    public void testUpiTransfer_Negative2() throws Exception
    {
		String response =taservice.upiTransfer("9398165142","7799208319",852000,8521 );
		Assertions.assertEquals("Transaction Failed : Err --->Insufficient Balance", response);
		
    }
       @Test
    public void testUpiTransfer_Negative3() throws Exception
    {
    	   String response =taservice.upiTransfer("9398165142","7799208329",8521,100 );
		Assertions.assertEquals("Transaction Failed : Err --->The Number that the money is to be sent has to be Registerd on the Application", response);
		
    }

}
