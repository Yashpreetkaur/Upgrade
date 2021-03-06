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
import com.bsb.hike.chatevents.ChatEventsObject;
import com.bsb.hike.common.AndroidClassNames;
import com.bsb.hike.common.Locators;
import com.bsb.hike.objectlocator.AttachmentListScreen;
import com.bsb.hike.objectlocator.AutoDownloadMediaScreen;
import com.bsb.hike.objectlocator.BlockedUserScreen;
import com.bsb.hike.objectlocator.ChatThreadScreen;
import com.bsb.hike.objectlocator.EditGroupNameScreen;
import com.bsb.hike.objectlocator.EditProfileScreen;
import com.bsb.hike.objectlocator.FavoriteScreen;
import com.bsb.hike.objectlocator.GroupChatThreadOverflowListScreen;
import com.bsb.hike.objectlocator.GroupInfoScreen;
import com.bsb.hike.objectlocator.HomeScreen;
import com.bsb.hike.objectlocator.ImageSelectionScreen;
import com.bsb.hike.objectlocator.LoginAboutYouScreen;
import com.bsb.hike.objectlocator.LoginPhoneNumberScreen;
import com.bsb.hike.objectlocator.MyProfileOverflowOptionsScreen;
import com.bsb.hike.objectlocator.MyProfileScreen;
import com.bsb.hike.objectlocator.NewChatContactSelectScreen;
import com.bsb.hike.objectlocator.NewGroupParticipantSelectionScreen;
import com.bsb.hike.objectlocator.NewGroupScreen;
import com.bsb.hike.objectlocator.OverFlowListScreen;
import com.bsb.hike.objectlocator.PinScreen;
import com.bsb.hike.objectlocator.PrivacyScreen;
import com.bsb.hike.objectlocator.SettingsScreen;
import com.bsb.hike.objectlocator.StatusScreen;
import com.bsb.hike.objectlocator.StickerShop;
import com.bsb.hike.objectlocator.TimelineScreen;
import com.bsb.hike.objectlocator.WelcomeScreen;
import com.bsb.hike.popup.screen.ChooseImageQualityScreen;
import com.bsb.hike.popup.screen.ConfirmYourNumberPopUpScreen;
import com.bsb.hike.qa.apisupport.Hike2HikeMessageSupport;

public class UpdateLibrary_3_9_8_81 extends UpdateLibrary{
	public void createNewUser(String version) throws UiObjectNotFoundException, InterruptedException, RemoteException   {
		System.out.println("CREATING NEW USER... "+newAppVersion);

		try {
			//setting pin for current user
			//	setPin();
			//launching app
			launchHikeWithoutWaitingForPopup();
			//			takeScreenshot();
			System.out.println("#############STARTING TEST WWWWWWWWWWWWWWWWWWWWW");
			//			UiObject hike_logo=getElement(Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 2);
			clickOnElement(Locators.NAME,WelcomeScreen.HIKE_MESSENGER_LOGO_ICON);
			Thread.sleep(2000);
			//				clickOnElement(Locators.CONTENT_DESCRIPTION , WelcomeScreen.HIKE_MESSENGER_LOGO_ICON);
			clickOnElement(Locators.NAME, WelcomeScreen.GET_STARTED_BTN);
			waitForElement(Locators.NAME,LoginPhoneNumberScreen.PHONE_NUMBER_TXT, 10);
			clearFocussedElementText(DEFAULT_CHARACTER_COUNT);
			enterText(Locators.NAME, LoginPhoneNumberScreen.PHONE_NUMBER_TXT, getDEFAULT_MSISDN_Create());
			clickOnElement(Locators.NAME, LoginPhoneNumberScreen.NEXT_BTN);
			clickOnElement(Locators.NAME, ConfirmYourNumberPopUpScreen.CONFIRM_BTN);
			Thread.sleep(10000);
			enterText(getPin());		  
			waitForElement(Locators.NAME,PinScreen.NEXT_BTN,MAX_TIMEOUT);	
			clickOnElement(Locators.NAME , PinScreen.NEXT_BTN);
			Thread.sleep(1000*15);
			enterText(DEFAULT_NAME);
			clickOnElementContinueOnFail(Locators.NAME , LoginAboutYouScreen.NEXT_BTN);
			clickOnElement(Locators.NAME, "I am a boy");
			clickOnElement(Locators.NAME , LoginAboutYouScreen.NEXT_BTN);
			UiObject f_layout=getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT, 1);
			UiObject l_layout=getChild(f_layout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
			UiObject l_layout2=getChild(l_layout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 1);
			UiObject sticker=getChild(l_layout2, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 0);
			if(isElementPresentOnScreen(sticker)){
				clickOnElement(sticker);
				clickOnElement(Locators.NAME, "Skip");
				Thread.sleep(10000);
			}
			if(isElementPresentOnScreen(Locators.NAME,"AWESOME")){
				clickOnElement(Locators.NAME, "AWESOME");
			}

		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to create new user");
		}

	}
	public void updateProfile(){
		try {
			System.out.println("UPDATING PROFILE.... "+newAppVersion);
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
//			clickOnElement(Locators.NAME, HomeScreen.NEW_CHAT_LBL);

			enterText(HIKE_NUMBER_1);
			clickOnElement(Locators.NAME,NewChatContactSelectScreen.TAP_TO_START_CHAT_LBL);
			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.CONTENT_DESCRIPTION, SettingsScreen.VIEW_PROFILE);
			clickOnElement(Locators.CONTENT_DESCRIPTION, MyProfileScreen.OVERFLOW_MENU_LBL);
			clickOnElement(Locators.NAME, MyProfileOverflowOptionsScreen.EDIT_PROFILE_LBL);
			clickOnElement(Locators.NAME,DEFAULT_NAME);
			clearFocussedElementText(DEFAULT_NAME.length());
			enterText(updatedName);
			clickOnElement(Locators.NAME, EditProfileScreen.EDIT_PROFILE_TITLE_LBL);
			Assert.assertTrue("the name did not get updated",isElementPresentOnScreen(Locators.NAME,updatedName));

			clickOnElement(Locators.CONTENT_DESCRIPTION, MyProfileScreen.OVERFLOW_MENU_LBL);
			clickOnElement(Locators.NAME, MyProfileOverflowOptionsScreen.EDIT_PROFILE_LBL);

			UiObject email_txt=getElement(Locators.NAME, "Email");
			UiObject email=email_txt.getFromParent(new UiSelector().className(AndroidClassNames.EDIT_TEXT));
			clickOnElement(email);
			email.setText(PROFILE_EMAIL);
			clickOnElement(Locators.NAME, EditProfileScreen.EDIT_PROFILE_TITLE_LBL);
			clickOnElement(Locators.CONTENT_DESCRIPTION, MyProfileScreen.OVERFLOW_MENU_LBL);
			clickOnElement(Locators.NAME, MyProfileOverflowOptionsScreen.EDIT_PROFILE_LBL);
			Assert.assertTrue("the email did not get updated",isElementPresentOnScreen(Locators.NAME,PROFILE_EMAIL));
			clickOnElement(Locators.NAME, EditProfileScreen.EDIT_PROFILE_TITLE_LBL);

			UiObject view = getElement(Locators.CLASSNAME, "android.view.View");
			UiObject flayout = getChild(view, Locators.CLASSNAME, "android.widget.FrameLayout", 1);
			UiObject llayout = getChild(flayout, Locators.CLASSNAME, "android.widget.LinearLayout", 1);
			UiObject Me = getChild(llayout, Locators.CLASSNAME, "android.widget.TextView", 0);
			clickOnElement(Me);

			clickOnElement(Locators.NAME, SettingsScreen.SETTTINGS_TITLE_LBL);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update profile info..");
		}
	}

	public void startSingleChatAndSendMessageToUnsavedNumber(String msisdn , String message  ) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("STARTING CHAT WITH UNSAVED USER... "+newAppVersion);
		goToHome();
		clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		enterText(msisdn);
		clickOnElement(Locators.NAME, "Tap to start chat");
		//	    	clickOnElement(Locators.NAME,"1");

		sendMessage(message);
	}

	public void startSingleChatAndSendMessageToHikeUser(String name , String message  ) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("STARTING CHAT WITH HIKE USER... "+newAppVersion);

		goToHome();
		clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		//		enterText(msisdn);
		clickOnElement(Locators.NAME, name);
		//	    	clickOnElement(Locators.NAME,"1");

		sendMessage(message);
	}

	public void sendMessage() throws UiObjectNotFoundException{
		System.out.println("Sending Message... "+newAppVersion);
		int sendButtonIndex=2;

		UiObject FrameLayout = getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT, 0);
		UiObject RelativeLayout = getChild(FrameLayout,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
		UiObject Llayout = getChild(RelativeLayout,Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
		UiObject Rlayout = getChild(Llayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 2);
		UiObject R0layout= getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);

		try{
			System.out.println(R0layout.getChildCount());
			if(R0layout.getChildCount() >= 4){
				sendButtonIndex++;
			}

		}catch(UiObjectNotFoundException e){
			System.out.println("Layout does not have 4 child");
		}

		UiObject sendButton = getChild(R0layout, Locators.CLASSNAME, AndroidClassNames.IMAGE_BUTTON, sendButtonIndex);
		clickOnElement(sendButton);

		//		UiDevice.getInstance().pressBack();
	}

	public void sendHikeMessage(String version){
		try {
			System.out.println("SENDING HIKE MESSAGE..."+newAppVersion);
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
			//			UiDevice.getInstance().pressBack();
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,messageSent));
			Thread.sleep(8000);
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,messageReceived));
			goToHome();			

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to send message");
		}
	}

	public void sendHike2SmsMessage(String version){
		try {
			System.out.println("SENDING MESSAGE TO SMS USER... "+newAppVersion);

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
		try {
			int counter =0;
			System.out.println("GOING BACK TO HOME SCREEN... "+newAppVersion);

			UiObject startChat = getElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			
			while(!startChat.exists() && counter <5){
				UiDevice.getInstance().pressBack();
				startChat= getElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
				counter++;
				//				UiObject nux = getElement(Locators.NAME, "Express more with stickers and chat with your best friends!",5000);
				//				if(isElementPresentOnScreen(nux)){
				//					clickOnElement(Locators.NAME, "SKIP");
				//				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void captureSmsCountBeforeUpgrade(String version){
		try {
			System.out.println("CAPTURING SMS COUNT BEFORE UPGRADE... "+newAppVersion);

			goToHome();
			openOverflowMenu();

			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			UiObject smsElement = new UiObject(new UiSelector().textStartsWith("SMS"));
			System.out.println(smsElement.getText());
			int smsTextLen = smsElement.getText().length();
			smsCountOnUiBeforeUpgrade = smsElement.getText().substring(smsTextLen-3, smsTextLen-1).trim();
			System.out.println(smsCountOnUiBeforeUpgrade);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to capture sms count before upgrade");
		}
	}

	public void openOverflowMenu(){
		try {
			System.out.println("Opening Overflow Menu... "+newAppVersion);
			clickOnElement(Locators.CONTENT_DESCRIPTION,HomeScreen.OVERFLOW_ICON);
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void toggleAutoDownloadCheckbox(String version){
		try {
			System.out.println("CHANGING AUTO DOWNLOAD SETTINGS... "+newAppVersion);

			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME, SettingsScreen.AUTO_DOWNLOAD_MEDIA_LBL);

			UiObject compressedVideo = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(0));
			compressedVideo.click();
			autoDownloadCheckboxStatus.put(0, compressedVideo.isChecked());

			UiObject imagesMobile = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(1));
			imagesMobile.click();
			autoDownloadCheckboxStatus.put(1, imagesMobile.isChecked());

			UiObject videoMobile = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(2));
			videoMobile.click();
			autoDownloadCheckboxStatus.put(2, videoMobile.isChecked());

			UiObject audioMobile = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(3));
			audioMobile.click();
			autoDownloadCheckboxStatus.put(3, audioMobile.isChecked());

			UiObject imagesWifi = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(4));
			imagesWifi.click();
			autoDownloadCheckboxStatus.put(4, imagesWifi.isChecked());

			UiObject Auto_Download_media = getElement(Locators.NAME, AutoDownloadMediaScreen.WHEN_CONNECTED_TO_WIFI_LBL);
			UiObject Mobile_Data_Lable = getElement(Locators.NAME, AutoDownloadMediaScreen.WHEN_ON_MOBILE_DATA_LBL);
			UiDevice.getInstance().swipe(Auto_Download_media.getBounds().centerX(), Auto_Download_media.getBounds().centerY(), Mobile_Data_Lable.getBounds().centerX(), Mobile_Data_Lable.getBounds().centerY(), 5);

			UiObject videoWifi = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(5));
			videoWifi.click();
			autoDownloadCheckboxStatus.put(5,videoWifi.isChecked());

			UiObject audioWifi = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(6));
			audioWifi.click();
			autoDownloadCheckboxStatus.put(6, audioWifi.isChecked());

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
			System.out.println("ADDING AS FAVORITE... "+newAppVersion);
			exitHike();
			launchHikeWithoutWaitingForPopup();
			goToHome();

			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME,"Timeline");
			clickOnElement(Locators.CONTENT_DESCRIPTION,TimelineScreen.FAV_IN_TIMELINE_ICON);
			UiObject add=getElement(Locators.NAME,"Add");
			UiObject name=add.getFromParent(new UiSelector().className(AndroidClassNames.TEXT_VIEW).index(0));
			System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm"+name.getText());
			favorite=name.getText();
			clickOnElement(add);
			if(isElementPresentOnScreen(Locators.NAME, "Yes")){
				clickOnElement(Locators.NAME, "Yes");
			}
			//			Assert.assertTrue(searchElementInListWithouTAsserting(Locators.NAME,"Added as a favorite"));
			clickOnElement(Locators.NAME, FavoriteScreen.FAV_TITLE_LBL);
			clickOnElement(Locators.NAME, TimelineScreen.TIMELINE_TITLE_LBL);
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			Assert.assertTrue("Unable to add favorite",isElementPresentOnScreen(Locators.NAME, "FAVORITES"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setStatusUpdate() throws UiObjectNotFoundException, InterruptedException {
		try {
			System.out.println("SETTING STATUS UPDATE... "+newAppVersion);
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME,"Timeline");
			clickOnElement(Locators.CONTENT_DESCRIPTION,TimelineScreen.POST_A_NEW_UPDATE_ICON);
			enterText(Locators.NAME,StatusScreen.WHATS_UP_LBL+updatedName+"?", STATUS_UPDATE);

			UiObject view = getElement(Locators.CLASSNAME, "android.view.View");
			UiObject flayout = getChild(view, Locators.CLASSNAME, "android.widget.FrameLayout", 1);
			UiObject llayout = getChild(flayout, Locators.CLASSNAME, "android.widget.LinearLayout", 0);
			UiObject rlayout = getChild(llayout, Locators.CLASSNAME, "android.widget.RelativeLayout", 0);
			UiObject rlayout1 = getChild(rlayout, Locators.CLASSNAME, "android.widget.RelativeLayout", 1);
			UiObject moods = getChild(rlayout1, Locators.CLASSNAME, "android.widget.ImageView");
			clickOnElement(moods);
			clickOnElement(Locators.NAME, "In love");
			clickOnElement(Locators.NAME,StatusScreen.POST_BTN);
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME,"Timeline");
			Assert.assertTrue("Failed to Update Status", isElementPresentOnScreen(Locators.NAME,STATUS_UPDATE));
			clickOnElement(Locators.NAME, TimelineScreen.TIMELINE_TITLE_LBL);
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.CONTENT_DESCRIPTION,SettingsScreen.VIEW_PROFILE);
			Assert.assertTrue("Failed to Update Status", isElementPresentOnScreen(Locators.NAME,STATUS_UPDATE));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update status..");
		}
	}
	public void toggleNotificationCheckbox(){
		try {
			System.out.println("CHANGING NOTIFICATION SETTINGS... "+newAppVersion);
			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME , SettingsScreen.NOTIFICATIONS_LBL);
			int count=0;
			for(int i=0 ; i<6; i++){
				if(i==0){
					clickOnElement(Locators.NAME, "Vibrate on new message");
					clickOnElement(Locators.NAME, "Off");
					notificationCheckboxStatus.put(i, false);

				}
				else if(i==3){
					clickOnElement(Locators.NAME, "Select a sound to play for a new notification");
					searchElementInListWithouTAsserting(Locators.NAME, "Off");
					clickOnElement(Locators.NAME, "Off");
					clickOnElement(Locators.NAME, "OK");
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

	public void verifyAutoDownloadCheckboxPersistence(String old_version, String new_version,UpdateLibrary ul){
		try {
			System.out.println("VERIFYING AUTO DOWNLOAD SETTING PERSISTENCE... "+newAppVersion);
			super.goToHome();
			super.openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME, SettingsScreen.MEDIA_LBL);
			UiObject Auto_Download_media = getElement(Locators.NAME, AutoDownloadMediaScreen.WHEN_CONNECTED_TO_WIFI_LBL);
			UiObject Mobile_Data_Lable = getElement(Locators.NAME, AutoDownloadMediaScreen.WHEN_ON_MOBILE_DATA_LBL);
			UiDevice.getInstance().swipe(Auto_Download_media.getBounds().centerX(), Auto_Download_media.getBounds().centerY(), Mobile_Data_Lable.getBounds().centerX(), Mobile_Data_Lable.getBounds().centerY(), 5);

			if(new_version.equals(newAppVersion)){			
				if(old_version.equals("3.7.0") || old_version.equals("3.6.6") || old_version.equals("3.6.0") || old_version.equals("3.5.1"))
				{
					for(int i=1 ; i<7 ; i++){	
						System.out.println(i);
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));					
						boolean wasChecked = ul.autoDownloadCheckboxStatus.get(i-1);
						System.out.println(wasChecked);
						boolean isChecked = object.isChecked();	
						System.out.println(isChecked);
						Assert.assertTrue(wasChecked==isChecked);	
					}
				}
				else{				
					for(int i=0 ; i<7 ; i++){	
						System.out.println(i);
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));					
						boolean wasChecked = ul.autoDownloadCheckboxStatus.get(i);
						System.out.println(wasChecked);
						boolean isChecked = object.isChecked();	
						System.out.println(isChecked);
						Assert.assertTrue(wasChecked==isChecked);	
					}		
				}
			}
			else{
				for(int i=0 ; i<7 ; i++){
					if(old_version.equals("2.7.0")||old_version.equals("2.7.1")){
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));
						boolean isChecked = object.isChecked();
						//Default check
						if(i==1||i==2){
							Assert.assertTrue("Default setting is not right",!isChecked );
						}
						else{
							Assert.assertTrue("Default setting is not right",isChecked );
						}
					}
					else {
//						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));
//						boolean wasChecked = autoDownloadCheckboxStatus.get(i);
//						boolean isChecked = object.isChecked();
//						Assert.assertTrue(wasChecked==isChecked);
						
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));					
						boolean wasChecked = ul.autoDownloadCheckboxStatus.get(i-1);
						System.out.println(wasChecked);
						boolean isChecked = object.isChecked();	
						System.out.println(isChecked);
						Assert.assertTrue(wasChecked==isChecked);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void shareMediaToGroup(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1. Share media in the group and verify shared media appears under group info.");
		try {
			System.out.println("Sharing media to the group");
			goToHome();
			clickOnElement(Locators.NAME, Updated_Test_Group_Name);
			clickOnElement(Locators.CONTENT_DESCRIPTION, ChatThreadScreen.ATTACH_ICON);
			clickOnElement(Locators.NAME, AttachmentListScreen.GALLERY_LBL);
			UiDevice.getInstance().click(171, 302);
			Assert.assertTrue("Failed to switch to gallery view", isElementPresentOnScreen(Locators.NAME, ImageSelectionScreen.CHOOSE_A_PHOTO));
			UiDevice.getInstance().click(108, 267);
			clickOnElement(Locators.NAME,"Next");
			clickOnElement(Locators.NAME, ChooseImageQualityScreen.SEND_BTN);
			Assert.assertTrue("Failed to redirect to chat thread",isElementPresentOnScreen(Locators.NAME,"4"+GroupInfoScreen.MEMBER_LBL));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void togglePrivacyCheckbox(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Change last seen settings from privacy screen."+"\n"+
				"2.Note the status of all elements.");
		try {
			System.out.println("CHANGING PRIVACY SETTINGS");
			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME , SettingsScreen.PRIVACY_LBL);
			int count=0;
			for(int i=0 ; i<5; i++){
				if(i==1 || i==2 || i==3 || i==4){
					privacyCheckboxStatus.put(i, false);
				}
				else{
					UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(count));
					object.click();
					System.out.println("CLICKING ON CHECKBOX");
					count++;
					privacyCheckboxStatus.put(i, object.isChecked());
				}
			}
			Iterator iterator = privacyCheckboxStatus.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				System.out.println("The key is: " + mapEntry.getKey()
						+ ",value is :" + mapEntry.getValue());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void blockUser(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1. Block user from privacy settings."+"\n"+
				"2. Verify user appears as block from chat thread.");
		try {
			System.out.println("Block user from settings");
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HIKE_CONTACT_NAME_4);
			sendMessage(TEST_CHAT_MESSAGE);
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME,SettingsScreen.PRIVACY_LBL);
			clickOnElement(Locators.NAME,PrivacyScreen.BLOCKED_LIST_LBL);
			enterText(HIKE_CONTACT_NAME_4);
			UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(0));
			object.click();
			blockUserCheckboxStatus.put(0, object.isChecked());
			clickOnElement(Locators.NAME,BlockedUserScreen.SAVE_LBL);
			clickOnElement(Locators.NAME, PrivacyScreen.PRIVACY_TITLE_LBL);
			clickOnElement(Locators.NAME, SettingsScreen.SETTTINGS_TITLE_LBL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void downloadStickerCategory(String version) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Download sticker from sticker palatte.");
		try {
			System.out.println("Download sticker category from sticker shop");
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HIKE_CONTACT_NAME);
			UiObject view = getElement(Locators.CLASSNAME, "android.view.View");
			UiObject frame = getChild(view, Locators.CLASSNAME, "android.widget.FrameLayout");
			UiObject r_Layout = getChild(frame, Locators.CLASSNAME, "android.widget.RelativeLayout");
			UiObject l_Layout = getChild(r_Layout, Locators.CLASSNAME, "android.widget.LinearLayout");
			UiObject r_Layout1 = getChild(l_Layout, Locators.CLASSNAME, "android.widget.RelativeLayout",2);
			UiObject r_Layout2 = getChild(r_Layout1, Locators.CLASSNAME, "android.widget.RelativeLayout");
			UiObject stickerbtn= getChild(r_Layout2, Locators.CLASSNAME, "android.widget.ImageButton");
			clickOnElement(stickerbtn);
			UiDevice.getInstance().click(674, 1134);
			waitForElement(Locators.NAME, StickerShop.FREE_LBL, MAX_TIMEOUT);
			UiObject rlayout = getElement(Locators.CLASSNAME, "android.widget.RelativeLayout", 1);
			UiObject llayout = getChild(rlayout, Locators.CLASSNAME, "android.widget.LinearLayout", 2);
			UiObject download = getChild(llayout, Locators.CLASSNAME, "android.widget.ImageView");
			clickOnElement(download);
			Thread.sleep(20000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createGCandUpdateName() throws UiObjectNotFoundException, InterruptedException{
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Create group and update name."+"\n"+
				"2.Verify that group name change mqtt packet appears.");
		try {
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME, OverFlowListScreen.NEW_GROUP_LBL);
			enterText(Test_Group_Name);
			clickOnElement(Locators.NAME,NewGroupScreen.NEXT_LBL);
			clickElementInList(Locators.NAME, HIKE_CONTACT_NAME);
			clickElementInList(Locators.NAME, HIKE_CONTACT_NAME_1);
			clickElementInList(Locators.NAME, HIKE_CONTACT_NAME_2);
			clickOnElement(Locators.NAME,NewGroupParticipantSelectionScreen.DONE_LBL);
			Assert.assertTrue("Failed to create group of 4 members",isElementPresentOnScreen(Locators.NAME,"4"+GroupInfoScreen.MEMBER_LBL));
			clickOnElement(Locators.CONTENT_DESCRIPTION, ChatThreadScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME, GroupChatThreadOverflowListScreen.GROUP_INFO_LBL);
			clickOnElement(Locators.CONTENT_DESCRIPTION,GroupInfoScreen.EDIT_GROUP_NAME_ICON);
			clearFocussedElementText(Test_Group_Name.length());
			enterText(Updated_Test_Group_Name);
			clickOnElement(Locators.NAME, EditGroupNameScreen.DONE_BTN);
			Assert.assertTrue("Failed to update group name",isElementPresentOnScreen(Locators.NAME,Updated_Test_Group_Name));
			clickOnElement(Locators.NAME, GroupInfoScreen.GROUP_INFO_TITLE_LBL);
			Assert.assertTrue("Failed to show group name update notification", isElementPresentOnScreen(Locators.NAME,"You"+ChatEventsObject.GROUP_CHAT_NAME_CHANGE_EVENT));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
