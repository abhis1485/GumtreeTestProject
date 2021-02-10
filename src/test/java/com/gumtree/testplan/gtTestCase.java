package com.gumtree.testplan;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gumtree.utils.*;

public class gtTestCase {
	
	webGenericLibrary webGenricLib = new webGenericLibrary();
	
	@Test(description="Test Case 1")
	@Parameters({"gumtreeFile"})
	public void testCase1(String gumtreeFile) throws InterruptedException {
		String expectedTitle = "baby bag - test purpose, please ignore - updated | Toys - Indoor | Gumtree Australia Camden Area - Camden | 1266454340";
		String actualTitle = "";
		 // get the actual value of the title
        actualTitle = webGenricLib.driver.getTitle();
		//validate the correct page is loaded
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Title verified!");
        } else {
            System.out.println("Title verification failed");
        }
		//type message in the message section
        String fTextToEnter = "Hey I’m interested in this item. Could you share what’s the colour of the bag please?";
        webGenricLib.sendKeys("Ad_Message_Textarea", fTextToEnter, gumtreeFile);
		//click on send button
        webGenricLib.click("Ad_Message_Send_Button", gumtreeFile);
		//Validate sign in pop up
        webGenricLib.verifyText("Gumtree_Signin_Popup", "Sign In",gumtreeFile);
		//close the pop up
        webGenricLib.click("Gumtree_Signin_Popup_Close_Button", gumtreeFile);
        
        System.out.println("Test case Passed");
	}
	
}