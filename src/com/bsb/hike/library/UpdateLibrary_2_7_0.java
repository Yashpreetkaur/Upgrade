package com.bsb.hike.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.bsb.hike.common.AndroidClassNames;
import com.bsb.hike.common.Locators;
import com.bsb.hike.objectlocator.EditProfileScreen;
import com.bsb.hike.objectlocator.HomeScreen;
import com.bsb.hike.objectlocator.LoginAboutYouScreen;
import com.bsb.hike.objectlocator.LoginPhoneNumberScreen;
import com.bsb.hike.objectlocator.MyProfileScreen;
import com.bsb.hike.objectlocator.OverFlowListScreen;
import com.bsb.hike.objectlocator.PinScreen;
import com.bsb.hike.objectlocator.StatusScreen;
import com.bsb.hike.objectlocator.TimelineScreen;
import com.bsb.hike.popup.screen.ConfirmYourNumberPopUpScreen;
import com.bsb.hike.qa.apisupport.Hike2HikeMessageSupport;

public class UpdateLibrary_2_7_0 extends UpdateLibrary{
	public void createNewUser(String version) throws UiObjectNotFoundException, InterruptedException, RemoteException   {

		try {
			//setting pin for current user
			setPin();
			//launching app
			launchHikeWithoutWaitingForPopup();
			//takeScreenshot();
			System.out.println("#############STARTING TEST WWWWWWWWWWWWWWWWWWWWW");
			System.out.println("CREATING NEW USER");

			clickOnElement(Locators.NAME, "Keep close friends close");
			clickOnElement(Locators.NAME,"Accept and Continue");
			waitForElement(Locators.NAME,LoginPhoneNumberScreen.PHONE_NUMBER_TXT, 10);
			enterText(Locators.NAME, LoginPhoneNumberScreen.PHONE_NUMBER_TXT, getDEFAULT_MSISDN_Create());
			clickOnElement(Locators.NAME, LoginPhoneNumberScreen.NEXT_BTN);

			clickOnElement(Locators.NAME, ConfirmYourNumberPopUpScreen.CONFIRM_BTN);
			Thread.sleep(60000);
			waitForElement(Locators.NAME,"Pin",120);

			enterText(DEFAULT_PIN);		    
			clickOnElement(Locators.NAME , PinScreen.NEXT_BTN);
			enterText(DEFAULT_NAME);
			clickOnElementContinueOnFail(Locators.NAME , LoginAboutYouScreen.NEXT_BTN);
			Thread.sleep(20000);
			clickOnElement(Locators.NAME, "Cancel");
			Thread.sleep(5000);

			if(isElementPresentOnScreen(Locators.NAME,"Open gift...")){
				clickOnElement(Locators.NAME,"Open gift...");
				Thread.sleep(5000);
				clickOnElement(Locators.NAME,"Give it a spin");
			}





			setLandscape();
			setPotrait();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to create new user");
		}

	}


	public void updateProfile(){
		try {
			System.out.println("UPDATING PROFILE");

			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, "My Profile");

			clickOnElement(Locators.CONTENT_DESCRIPTION, MyProfileScreen.OVERFLOW_MENU_LBL);
			clickOnElement(Locators.NAME, "Edit Profile");

			clickOnElement(Locators.NAME,DEFAULT_NAME);
			clearFocussedElementText(DEFAULT_NAME.length());
			enterText(updatedName);

			clickOnElement(Locators.NAME, EditProfileScreen.EDIT_PROFILE_TITLE_LBL);
			Assert.assertTrue("the name did not get updated",isElementPresentOnScreen(Locators.NAME,updatedName));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update profile info..");
		}
	}

	public void startSingleChatAndSendMessageToUnsavedNumber(String msisdn , String message  ) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("STARTING CHAT WITH UNSAVED USER");

		goToHome();
		clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		enterText(msisdn);
		clickOnElement(Locators.NAME, "Tap here to add this person");
		clickOnElement(Locators.NAME,"1");

		sendMessage(message);
	}

	public void startSingleChatAndSendMessageToHikeUser(String name , String message  ) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("STARTING CHAT WITH HIKE USER");

		goToHome();
		clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		//	enterText(msisdn);
		clickOnElement(Locators.NAME, name);
		clickOnElement(Locators.NAME,"1");

		sendMessage(message);
	}

	public void sendMessage() throws UiObjectNotFoundException{
		System.out.println("Sending Message ");
		UiObject FrameLayout = getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT, 0);
		UiObject RelativeLayout = getChild(FrameLayout,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
		UiObject Llayout = getChild(RelativeLayout,Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
		UiObject Rlayout = getChild(Llayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 3);
		UiObject R0layout= getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);

		UiObject sendButton = getChild(R0layout, Locators.CLASSNAME, AndroidClassNames.IMAGE_BUTTON, 3);
		clickOnElement(sendButton);
		//	UiDevice.getInstance().pressBack();
	}

	public void sendHikeMessage(String version){
		try {
			System.out.println("SENDING HIKE MESSAGE");
			//		setSMSCredit(100);
			//		setDEFAULT_MSISDN();
			List<String> listOfMessages = new ArrayList<String>();
			//goToHome();
			String messageSent = "automation" + RandomStringUtils.randomNumeric(5);
			startSingleChatAndSendMessageToHikeUser(HIKE_CONTACT_NAME , messageSent);	
			listOfMessages.add(messageSent);

			Hike2HikeMessageSupport hikeMessage = new Hike2HikeMessageSupport();
			String messageReceived = "auto h2h#" + RandomStringUtils.randomNumeric(4);
			System.out.println("aaaaaaaaaaaaaaaaaaaaaa"+getDEFAULT_MSISDN());
			hikeMessage.sendHikeMessage(HIKE_NUMBER_1,getDEFAULT_MSISDN() , messageReceived);
			System.out.println(getDEFAULT_MSISDN());
			listOfMessages.add(messageReceived);

			hikeMsgHm.put(HIKE_CONTACT_NAME, listOfMessages);
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,messageSent));
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,messageReceived));

			goToHome();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to send message");
		}
	}

	public void sendHike2SmsMessage(String version){
		try {
			System.out.println("SENDING MESSAGE TO SMS USER");

			List<String> listOfMessages = new ArrayList<String>();
			goToHome();
			smsUser = "+9111"+RandomStringUtils.randomNumeric(8);
			String messageSent = "automation" + RandomStringUtils.randomNumeric(5);
			startSingleChatAndSendMessageToUnsavedNumber(smsUser , messageSent);	
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,messageSent));

			listOfMessages.add(messageSent);
			hikeMsgSm.put(smsUser, listOfMessages);
			goToHome();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to send message");
		}
	}
	public void goToHome(){
		int counter =0;

		System.out.println("GOING BACK TO HOME SCREEN");

		UiObject startChat = getElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		while(!startChat.exists() && counter <5){
			UiDevice.getInstance().pressBack();

			startChat= getElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			counter++;
		}	

	}

	public void captureSmsCountBeforeUpgrade(String version){
		System.out.println("CAPTURING SMS COUNT BEFORE UPGRADE");

		goToHome();
		try {
			openOverflowMenu();
			clickOnElement(Locators.NAME, "Free SMS");
			UiObject hiketoSMS_LBL=getElement(Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,2);
			UiObject count=getChild(hiketoSMS_LBL, Locators.CLASSNAME, AndroidClassNames.TEXT_VIEW,3);
			smsCountOnUiBeforeUpgrade=count.getText();
			System.out.println("smsCountOnUiBeforeUpgrade"+smsCountOnUiBeforeUpgrade);

		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void openOverflowMenu(){
		try {
			System.out.println("Opening Overflow menu");

			UiObject View =getElement(Locators.CLASSNAME, AndroidClassNames.VIEW,0);
			UiObject LLayout=getChild(View, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 1);
			UiObject OverflowMenu=getChild(LLayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,1);

			clickOnElement(OverflowMenu);
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void toggleAutoDownloadCheckbox(){
		System.out.println("TOGGLE AUTODOWNLOAD CHECKBOX Doesn't exist for this version");

	}

	public void addHikeContactAsFavorite(String version){
		try {
			System.out.println("ADDING FAVORITE");

			goToHome();

			clickOnElement(Locators.NAME, "FRIENDS");
			UiObject add=getElement(Locators.NAME,"Add");
			
			UiObject name=add.getFromParent(new UiSelector().className(AndroidClassNames.TEXT_VIEW).index(0));
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm"+name.getText());
			favorite=name.getText();
			
			clickOnElement(add);
			Assert.assertTrue(searchElementInListWithouTAsserting(Locators.NAME, "Friend request pending"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setStatusUpdate() throws UiObjectNotFoundException, InterruptedException {
		try {

			System.out.println("SETTING STATUS UPDATE");

			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME,"My Profile");
			clickOnElement(Locators.CONTENT_DESCRIPTION,TimelineScreen.POST_A_NEW_UPDATE_ICON);
			enterText(Locators.NAME,StatusScreen.WHATS_UP_LBL+updatedName+"?", STATUS_UPDATE);
			clickOnElement(Locators.NAME,StatusScreen.POST_BTN);
			goToHome();
			clickOnElement(Locators.NAME, "UPDATES");

			Assert.assertTrue("Failed to Update Status", isElementPresentOnScreen(Locators.NAME,STATUS_UPDATE));
			clickOnElement(Locators.NAME, "CHATS");
			openOverflowMenu();
			clickOnElement(Locators.NAME,"My Profile");
			Assert.assertTrue("Failed to Update Status", isElementPresentOnScreen(Locators.NAME,STATUS_UPDATE));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update status..");
		}
	}

}
