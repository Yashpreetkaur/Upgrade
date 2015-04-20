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
import com.bsb.hike.objectlocator.SettingsScreen;
import com.bsb.hike.objectlocator.StatusScreen;
import com.bsb.hike.objectlocator.TimelineScreen;
import com.bsb.hike.objectlocator.WelcomeScreen;
import com.bsb.hike.popup.screen.ConfirmYourNumberPopUpScreen;
import com.bsb.hike.qa.apisupport.Hike2HikeMessageSupport;

public class UpdateLibrary_2_9_6 extends UpdateLibrary {

	public void createNewUser(String version) throws UiObjectNotFoundException, InterruptedException, RemoteException   {
		System.out.println("CREATING NEW USER");

		try {
			//setting pin for current user
			setPin();
			//launching app
			launchHikeWithoutWaitingForPopup();
			//				takeScreenshot();
			System.out.println("#############STARTING TEST WWWWWWWWWWWWWWWWWWWWW");

			clickOnElement(Locators.NAME , "Welcome to"+"\n"+"Hike Messenger");
			clickOnElement(Locators.NAME, WelcomeScreen.GET_STARTED_BTN);



			waitForElement(Locators.NAME,LoginPhoneNumberScreen.PHONE_NUMBER_TXT, 10);
			enterText(Locators.NAME, LoginPhoneNumberScreen.PHONE_NUMBER_TXT, getDEFAULT_MSISDN_Create());
			clickOnElement(Locators.NAME, LoginPhoneNumberScreen.NEXT_BTN);

			clickOnElement(Locators.NAME, ConfirmYourNumberPopUpScreen.CONFIRM_BTN);
			Thread.sleep(10000);

			waitForElement(Locators.NAME,PinScreen.PIN_TXT,MAX_TIMEOUT);	

			enterText(DEFAULT_PIN);		    
			clickOnElement(Locators.NAME , PinScreen.NEXT_BTN);
			enterText(DEFAULT_NAME);
			clickOnElementContinueOnFail(Locators.NAME , LoginAboutYouScreen.NEXT_BTN);
			Thread.sleep(20000);
			clickOnElement(Locators.NAME, "I am a boy");
			clickOnElementContinueOnFail(Locators.NAME , LoginAboutYouScreen.NEXT_BTN);

			//		    getUiDevice().setOrientationLeft();
			//			    getUiDevice().setOrientationNatural();
			//			    clickOnElementContinueOnFail(Locators.NAME, objLocator.start_Adding_Favourites_BTN);
			//			    clickOnElementContinueOnFail(Locators.NAME, objLocator.skip_BTN);
			//Thread.sleep(10000);
			//getUiDevice().pressBack();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to create new user");
		}

	}
	public void updateProfile(){
		try {
			System.out.println("UPDATING PROFILE");


			openOverflowMenu();
			clickOnElement(Locators.NAME, "My Profile");

			clickOnElement(Locators.CONTENT_DESCRIPTION, MyProfileScreen.OVERFLOW_MENU_LBL);
			clickOnElement(Locators.NAME, "Edit Profile");

			clickOnElement(Locators.NAME,DEFAULT_NAME);
			clearFocussedElementText(DEFAULT_NAME.length());
			enterText(updatedName);

			clickOnElement(Locators.NAME, EditProfileScreen.EDIT_PROFILE_TITLE_LBL);
			Assert.assertTrue("the name did not get updated",isElementPresentOnScreen(Locators.NAME,updatedName));
			clickOnElement(Locators.NAME, "Me");
			if(isElementPresentOnScreen(Locators.NAME, "Start adding favorites")){
				clickOnElement(Locators.NAME, "Start adding favorites");
				clickOnElement(Locators.NAME, "Skip");


			}
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
		clickOnElement(Locators.NAME, "Tap to start chat");
		//	    	clickOnElement(Locators.NAME,"1");

		sendMessage(message);
	}

	public void startSingleChatAndSendMessageToHikeUser(String name , String message  ) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("STARTING CHAT WITH HIKE USER");

		goToHome();
		clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		//		enterText(msisdn);
		clickOnElement(Locators.NAME, name);
		//	    	clickOnElement(Locators.NAME,"1");

		sendMessage(message);
	}

	public void sendMessage() throws UiObjectNotFoundException{
		System.out.println("Sending Message");
		UiObject FrameLayout = getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT, 0);
		UiObject RelativeLayout = getChild(FrameLayout,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
		UiObject Llayout = getChild(RelativeLayout,Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
		UiObject Rlayout = getChild(Llayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 2);
		UiObject R0layout= getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);

		UiObject sendButton = getChild(R0layout, Locators.CLASSNAME, AndroidClassNames.IMAGE_BUTTON, 2);
		clickOnElement(sendButton);
		//		UiDevice.getInstance().pressBack();
	}

	public void sendHikeMessage(String version){
		try {
			System.out.println("SENDING HIKE MESSAGE");
			//			setSMSCredit(100);
			//			setDEFAULT_MSISDN();
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
		try {
			System.out.println("CAPTURING SMS COUNT BEFORE UPGRADE");

			goToHome();
			openOverflowMenu();

			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			UiObject smsElement = new UiObject(new UiSelector().textStartsWith("SMS"));
			System.out.println(smsElement.getText());
			int smsTextLen = smsElement.getText().length();
			smsCountOnUiBeforeUpgrade = smsElement.getText().substring(smsTextLen-3, smsTextLen-1).trim();
			System.out.println(smsCountOnUiBeforeUpgrade);


			//			smsCountFromRedisBeforeUpgrade = captureSmsCountFromServerBeforeUpgrade();
			//			System.out.println(smsCountFromRedisBeforeUpgrade);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to capture sms count before upgrade");
		}
	}

	public void openOverflowMenu(){
		try {
			System.out.println("Opening Overflow Menu");
			UiObject View =getElement(Locators.CLASSNAME, AndroidClassNames.VIEW,0);
			UiObject LLayout=getChild(View, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 2);
			UiObject OverflowMenu=getChild(LLayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,1);

			clickOnElement(OverflowMenu);
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void toggleAutoDownloadCheckbox(){
		try {
			System.out.println("CHANGING AUTO DOWNLOAD SETTINGS ");

			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME, "Auto download media");

			//			

			UiObject imagesMobile = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(0));
			imagesMobile.click();
			autoDownloadCheckboxStatus.put(0, imagesMobile.isChecked());

			UiObject videoMobile = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(1));
			videoMobile.click();
			autoDownloadCheckboxStatus.put(1, videoMobile.isChecked());

			UiObject audioMobile = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(2));
			audioMobile.click();
			autoDownloadCheckboxStatus.put(2, audioMobile.isChecked());

			UiObject imagesWifi = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(3));
			imagesWifi.click();
			autoDownloadCheckboxStatus.put(3, imagesWifi.isChecked());

			UiObject videoWifi = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(4));
			videoWifi.click();
			autoDownloadCheckboxStatus.put(4,videoWifi.isChecked());

			UiObject audioWifi = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(5));
			audioWifi.click();
			autoDownloadCheckboxStatus.put(5, audioWifi.isChecked());

			Iterator iterator = autoDownloadCheckboxStatus.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				System.out.println("The key is: " + mapEntry.getKey()
						+ ",value is :" + mapEntry.getValue());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addHikeContactAsFavorite(String version){
		try {
			System.out.println("ADDING AS FAVORITE");
			goToHome();

			clickOnElement(Locators.NAME, "CONTACTS");
UiObject add=getElement(Locators.NAME,"Add");
			
			UiObject name=add.getFromParent(new UiSelector().className(AndroidClassNames.TEXT_VIEW).index(0));
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm"+name.getText());
			favorite=name.getText();
			
			clickOnElement(add);
			if(isElementPresentOnScreen(Locators.NAME, "Yes")){
				clickOnElement(Locators.NAME, "Yes");
			}
			Assert.assertTrue(searchElementInListWithouTAsserting(Locators.NAME,"Added as a favorite"));

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
	public void toggleNotificationCheckbox(){
		try {
			System.out.println("CHANGING NOTIFICATION SETTINGS");

			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME , SettingsScreen.NOTIFICATIONS_LBL);
			int count=0;
			for(int i=0 ; i<5; i++){

				if(i==0){
					clickOnElement(Locators.NAME, "Vibrate on new message");
					clickOnElement(Locators.NAME, "Off");
					notificationCheckboxStatus.put(i, false);

				}
				else if(i==3){
					clickOnElement(Locators.NAME, "Select a sound to play for a new notification message");
					clickOnElement(Locators.NAME, "Off");
					notificationCheckboxStatus.put(i, false);

				}
				else{
					UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(count));
					object.click();
					System.out.println("CLICKING ON CHECKBOX");
					count++;

					notificationCheckboxStatus.put(i, object.isChecked());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
