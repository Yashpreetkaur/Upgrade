package com.bsb.hike.library;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;

import android.os.RemoteException;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.bsb.hike.base.ExecuteShell;
import com.bsb.hike.chatevents.ChatEventsObject;
import com.bsb.hike.common.AndroidClassNames;
//import com.bsb.hike.common.CommonFunctionsUiAutomator;
import com.bsb.hike.common.HikeConstants;
import com.bsb.hike.library.HikeLibrary;
import com.bsb.hike.common.Locators;
import com.bsb.hike.objectlocator.AccountScreen;
import com.bsb.hike.objectlocator.AttachmentListScreen;
import com.bsb.hike.objectlocator.AutoDownloadMediaScreen;
import com.bsb.hike.objectlocator.BackupScreen;
import com.bsb.hike.objectlocator.BlockedListScreen;
import com.bsb.hike.objectlocator.BlockedUserScreen;
import com.bsb.hike.objectlocator.ChatThreadOverFlowMenuListScreen;
import com.bsb.hike.objectlocator.ChatThreadScreen;
import com.bsb.hike.objectlocator.ConversationScreen;
import com.bsb.hike.objectlocator.EditGroupNameScreen;
import com.bsb.hike.objectlocator.EditProfileScreen;
import com.bsb.hike.objectlocator.FileScreen;
import com.bsb.hike.objectlocator.GroupChatThreadListScreen;
import com.bsb.hike.objectlocator.GroupChatThreadOverflowListScreen;
import com.bsb.hike.objectlocator.GroupInfoOverflowMenuListScreen;
import com.bsb.hike.objectlocator.GroupInfoScreen;
import com.bsb.hike.objectlocator.HomeScreen;
import com.bsb.hike.objectlocator.ImageSelectionScreen;
import com.bsb.hike.objectlocator.LoginAboutYouScreen;
import com.bsb.hike.objectlocator.LoginPhoneNumberScreen;
import com.bsb.hike.objectlocator.LoginTellUsMoreScreen;
import com.bsb.hike.objectlocator.MediaViewerScreen;
import com.bsb.hike.objectlocator.MyProfileOverflowOptionsScreen;
import com.bsb.hike.objectlocator.MyProfileScreen;
import com.bsb.hike.objectlocator.NewChatContactSelectScreen;
import com.bsb.hike.objectlocator.NewGroupParticipantSelectionScreen;
import com.bsb.hike.objectlocator.NewGroupScreen;
import com.bsb.hike.objectlocator.OverFlowListScreen;
import com.bsb.hike.objectlocator.PinHistoryScreen;
import com.bsb.hike.objectlocator.PinScreen;
import com.bsb.hike.objectlocator.PreviewScreen;
import com.bsb.hike.objectlocator.PrivacyScreen;
import com.bsb.hike.objectlocator.ProfileOverflowScreen;
import com.bsb.hike.objectlocator.ProfileScreen;
import com.bsb.hike.objectlocator.SettingsScreen;
import com.bsb.hike.objectlocator.StatusScreen;
import com.bsb.hike.objectlocator.StickerShop;
import com.bsb.hike.objectlocator.TimelineScreen;
import com.bsb.hike.objectlocator.UIAutomatorObjectLocator;
import com.bsb.hike.objectlocator.WelcomeScreen;
import com.bsb.hike.popup.screen.ChooseImageQualityScreen;
import com.bsb.hike.popup.screen.ConfirmYourNumberPopUpScreen;
import com.bsb.hike.popup.screen.EditGroupNamePopupScreen;
import com.bsb.hike.popup.screen.GroupInfoParticipantOptionPopUpScreen;
import com.bsb.hike.popup.screen.MuteGroupTip;
import com.bsb.hike.qa.apisupport.GroupChatMessageSupport;
import com.bsb.hike.qa.apisupport.Hike2HikeMessageSupport;
import com.bsb.hike.qa.apisupport.StatusUpdateSupport;
import com.bsb.hike.qa.dbmanager.MongoDBManagerUtil;
import com.bsb.hike.qa.dbmanager.RedisServiceManagerUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;


public class UpdateLibrary extends HikeLibrary{

	public UIAutomatorObjectLocator objLocator = new UIAutomatorObjectLocator();
	public RedisServiceManagerUtil redis = RedisServiceManagerUtil.getInstance();
	public MongoDBManagerUtil mongo = MongoDBManagerUtil.getInstance();
	public  DB userDB = mongo.getMongo().getDB("userdb");
	public static String[] appVersions={"3.9.7","3.9.6","3.9.2"};	//// "3.9.6","3.9.2","3.9.6","3.9.2","3.9.0",,,"3.9.6", "3.9.2",,"3.9.2","3.9.0""3.9.2","2.7.0","2.7.1","2.8.0","2.8.2","2.8.5","2.9.0","2.9.6","3.0.0","3.0.1","3.1.0","3.2.0","3.3.0","3.3.1"
	public static String newAppVersion="3.9.8";
	public HashMap<String, List<String>> hikeMsgHm =new HashMap<String, List<String>>();
	public HashMap<String, List<String>> hikeMsgSm=new HashMap<String, List<String>>();
	public HashMap<String, List<String>> hikeMsGrp=new HashMap<String, List<String>>(); ;
	public LinkedHashMap<Integer, Boolean> autoDownloadCheckboxStatus=new LinkedHashMap<Integer, Boolean>();
	public LinkedHashMap<Integer, Boolean> notificationCheckboxStatus=new LinkedHashMap<Integer, Boolean>();
	public LinkedHashMap<Integer, Boolean> privacyCheckboxStatus=new LinkedHashMap<Integer, Boolean>();
	public LinkedHashMap<Integer, Boolean> stickerCheckboxStatus= new LinkedHashMap<Integer, Boolean>();
	public LinkedHashMap<Integer, Boolean> blockUserCheckboxStatus= new LinkedHashMap<Integer, Boolean>();
	public List<String> suList=new ArrayList<String>();
	String smsUser = "";
	String smsCountOnUiBeforeUpgrade = "";
	int smsCountFromRedisBeforeUpgrade = 0;
	String updatedName = "abcd";
	public String favorite="";
	public static String textAfterBackup="";
	String groupName = "apitest";

	public static  String[] getSetUpgradeOldVersion()  {

		try {
			Properties prop = new Properties();
			String propFileName = "/data/local/tmp/builds.properties";
			FileReader reader = new FileReader(propFileName);
			prop.load(reader);
			String versions=prop.getProperty("oldAppVersions");
			String[] oldVersion = versions.split(", ");
			oldVersion[0]=oldVersion[0].substring(1);
			oldVersion[oldVersion.length-1]=oldVersion[oldVersion.length-1].substring(0,oldVersion[oldVersion.length-1].length()-1);
			for(int i=0;i<oldVersion.length;i++)
				System.out.println(oldVersion[i]);
			appVersions=oldVersion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appVersions;
	}

	public static String getSetUpgradeNewVersion() {
		try {
			String newVersion =null;
			Properties prop = new Properties();
			String propFileName = "/data/local/tmp/builds.properties";
			FileReader reader = new FileReader(propFileName);
			prop.load(reader);
			newVersion = prop.getProperty("newAppVersion");
			System.out.println(newVersion);
			newAppVersion=newVersion;
			System.out.println("new app version is ....."+newAppVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newAppVersion;
	}

	public void installOldVersionApp(String baseVersion){
		try {
			System.out.println("INSTRUMENTATION DESCRIPTION: -Install old version:");	
			System.out.println("  INSTALLING OLD VERSION");
			ExecuteShell exec = new ExecuteShell();
			System.out.println(baseVersion);
			exec.ExecuteShellCommand("pm", "install" , "/data/local/tmp/android-client-" + baseVersion + ".apk");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to install base app");
		}
	}


	public void createNewUser(String version) throws UiObjectNotFoundException, InterruptedException, RemoteException   {
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Sign up."+"\n"+
				"2.Check to ensure signup.");
		try {
			System.out.println("CREATING NEW USER");
			//setting pin for current user
			//setPin();
			//launching app
			launchHikeWithoutWaitingForPopup();
			takeScreenshot();
			System.out.println("#############STARTING TEST WWWWWWWWWWWWWWWWWWWWW");
			if(version.equals("2.8.0") || version.equals("2.8.2") || version.equals("2.8.5")){
				clickOnElement(Locators.NAME, "Keep close friends close");
				clickOnElement(Locators.NAME,"Accept and Continue");
			}

			else{
				clickOnElement(Locators.NAME , WelcomeScreen.HIKE_MESSENGER_LOGO_ICON);
				clickOnElement(Locators.NAME, WelcomeScreen.GET_STARTED_BTN);
			}

			waitForElement(Locators.NAME,LoginPhoneNumberScreen.PHONE_NUMBER_TXT, 10);
			enterText(Locators.NAME, LoginPhoneNumberScreen.PHONE_NUMBER_TXT, getDEFAULT_MSISDN_Create());
			clickOnElement(Locators.NAME, LoginPhoneNumberScreen.NEXT_BTN);
			Thread.sleep(2000);
			clickOnElement(Locators.NAME, ConfirmYourNumberPopUpScreen.CONFIRM_BTN);
			Thread.sleep(2000);


			if(version.equals("2.8.0") || version.equals("2.8.2") || version.equals("2.8.5")){
				waitForElement(Locators.NAME,"Pin",MAX_TIMEOUT);
			}

			else{
				waitForElement(Locators.NAME,PinScreen.PIN_TXT,MAX_TIMEOUT);	
			}
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

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to create new user");
		}

	}

	public void updateProfile(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Update user name from edit profile screen."+"\n"+
				"2.Check updated name in profile screen");
		try {
			System.out.println("UPDATING PROFILE");
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




	public void sendHikeMessage(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Start a new chat with hike user."+"\n"+
				"2.Send a message."+"\n"+
				"3.Receive a message on same chat thread."+"\n"+
				"4.Check that messages has been successfully sent and received");
		try {
			System.out.println("SENDING HIKE MESSAGE");
			List<String> listOfMessages = new ArrayList<String>();
			//goToHome();
			String messageSent = "automation" + RandomStringUtils.randomNumeric(5);
			startSingleChatAndSendMessageToUnsavedNumber("+911234555247" , messageSent , version);	
			listOfMessages.add(messageSent);
			Hike2HikeMessageSupport hikeMessage = new Hike2HikeMessageSupport();
			String messageReceived = "auto h2h#" + RandomStringUtils.randomNumeric(4);
			hikeMessage.sendHikeMessage("+911234555247", getDEFAULT_MSISDN() , messageReceived);
			listOfMessages.add(messageReceived);
			hikeMsgHm.put("+911234555247", listOfMessages);
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,messageSent));
			goToHome();		

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to send message");
		}
	}


	public void startSingleChatAndSendMessageToUnsavedNumber(String msisdn , String message , String version ) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("STARTING CHAT WITH UNSAVED USER");

		goToHome();
		clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		enterText(msisdn);
		if(version.equals("2.8.0") || version.equals("2.8.2") || version.equals("2.8.5")){
			clickOnElement(Locators.NAME, "Tap here to add this person");
			clickOnElement(Locators.NAME,"1");
		}
		else{
			clickOnElement(Locators.NAME,NewChatContactSelectScreen.TAP_TO_START_CHAT_LBL);
		}
		sendMessage(message);
	}




	public void sendHike2SmsMessage(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Start a new chat with SMS user."+"\n"+
				"2.Send a message."+"\n"+
				"3.Check that message has been successfully sent.");
		try {
			System.out.println("SENDING MESSAGE TO SMS USER");
			List<String> listOfMessages = new ArrayList<String>();
			goToHome();
			smsUser = "+9111"+RandomStringUtils.randomNumeric(8);
			String messageSent = "automation" + RandomStringUtils.randomNumeric(5);
			startSingleChatAndSendMessageToUnsavedNumber(smsUser , messageSent , version);	
			listOfMessages.add(messageSent);
			hikeMsgSm.put(smsUser, listOfMessages);
			goToHome();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to send message");
		}
	}

	public void createGcAndSendMessage(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Create a new group and add user to it."+"\n"+
				"2.Receive a message by group owner in group." +
				"\n"+"3.Send a new message in group."+
				"\n"+"4.Check that messages has been successfully sent and received");
		try {
			System.out.println("CREATING GROUP AND SENDING MESSAGE");
			GroupChatMessageSupport gc = new GroupChatMessageSupport();
			List<String> list = new ArrayList<String>();
			List<String> listMessages = new ArrayList<String>();
			System.out.println(getDEFAULT_MSISDN());
			list.add(getDEFAULT_MSISDN());
			list.add("+911231238765");
			gc.createGroupAndSendMessage("+911234555247" , list);
			Thread.sleep(5000);
			String receivedMessage = "Hello ! I am the group intiator";
			listMessages.add(receivedMessage);
			String sentMessage = "sending message";
			listMessages.add(sentMessage);
			clickOnElement(Locators.NAME, groupName);
			enterText(sentMessage);
			sendMessage();
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,sentMessage));
			Assert.assertTrue("message is not sent", isElementPresentOnScreen(Locators.NAME,receivedMessage));
			hikeMsGrp.put(groupName, listMessages);
			UiDevice.getInstance().pressBack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void setStatusUpdate() throws UiObjectNotFoundException, InterruptedException {
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Set a status update."+"\n"+
				"2.Check status in profile and timeline");
		try {
			System.out.println("SETTING STATUS UPDATE");

			String friendToAdd = "+911234555247";
			//String statusUpdateByFriend = "This is test SU";
			String statusUpdateByUser = "Happy";
			//suList.add(statusUpdateByFriend);
			suList.add(statusUpdateByUser);
			MongoDBManagerUtil mongo = MongoDBManagerUtil.getInstance();
			DB userDB = mongo.getMongo().getDB("userdb");
			mongo.modifyFavorites("+91" + getDEFAULT_MSISDN(), friendToAdd , true, true, userDB);
			mongo.modifyFavorites(friendToAdd, "+91" + getDEFAULT_MSISDN() , true, true, userDB);
			StatusUpdateSupport suSupport = new StatusUpdateSupport();
			suSupport.setStatusUpdate(friendToAdd);
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION,HomeScreen.TIMELINE_ICON);
			clickOnElement(Locators.CONTENT_DESCRIPTION,TimelineScreen.POST_A_NEW_UPDATE_ICON);
			enterText(Locators.NAME,StatusScreen.WHATS_UP_LBL+updatedName+"?", STATUS_UPDATE);
			clickOnElement(Locators.NAME,StatusScreen.POST_BTN);
			Assert.assertTrue("Failed to Update Status", isElementPresentOnScreen(Locators.NAME,STATUS_UPDATE));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update status..");
		}
	}


	public void captureSmsCountBeforeUpgrade(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Capture the SMS count from SMS screen or Settings screen depending on the version.");
		try {
			System.out.println("CAPTURING SMS COUNT BEFORE UPGRADE");

			goToHome();
			openOverflowMenu();
			if(version.contains("2.9")){
				clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
				UiObject smsElement = new UiObject(new UiSelector().textStartsWith("SMS"));
				System.out.println(smsElement.getText());
				int smsTextLen = smsElement.getText().length();
				smsCountOnUiBeforeUpgrade = smsElement.getText().substring(smsTextLen-3, smsTextLen-1);
				System.out.println(smsCountOnUiBeforeUpgrade);
			}
			else{

				//				UiObject smsCountElement = new UiObject(new UiSelector().textContains("/"));
				clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
				UiObject smsCountElement = new UiObject(new UiSelector().textContains("Free SMS"));
				String smsCountText = smsCountElement.getText();
				String[] splitSmsCountText = smsCountText.split(":");
				System.out.println(splitSmsCountText[1]);
				System.out.println(splitSmsCountText[1].split("\\)")[0]);
				smsCountOnUiBeforeUpgrade = splitSmsCountText[1].split("\\)")[0].trim();
				System.out.println("smsCountOnUiBeforeUpgrade                 "+smsCountOnUiBeforeUpgrade);
			}
			//			smsCountFromRedisBeforeUpgrade = captureSmsCountFromServerBeforeUpgrade();
			//			System.out.println(smsCountFromRedisBeforeUpgrade);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to capture sms count before upgrade");
		}
	}

	public int captureSmsCountFromServerBeforeUpgrade(){

		try{
			System.out.println("CAPTURING SMS COUNT FROM SERVER");

			String msisdn = "+91" + getDEFAULT_MSISDN();
			String uid = redis.getUserUid(msisdn);
			Map<String , String> list = redis.hgetAll("em_"+uid);
			double transactionScore = 0.0;
			int sentMessages = (list.get("sm") !=null) ?  Integer.parseInt(list.get("sm")) : 0; 
			int receivedMessages = (list.get("rm") !=null) ?  Integer.parseInt(list.get("rm")) : 0; 
			int gcSentMessages = (list.get("gcsm") !=null) ?  Integer.parseInt(list.get("gcsm")) : 0;
			int gcReceivedMessages = (list.get("gcrm") !=null) ?  Integer.parseInt(list.get("gcrm")) : 0;
			transactionScore = (1 * Math.min(sentMessages, receivedMessages)) +

					(0.2 * Math.min(gcSentMessages, gcReceivedMessages));
			int h2sEmLinked = (int) Math.floor((2*(Math.pow(transactionScore, 0.55))));
			int h2sJoiningBonus = (list.get("h2sjb") !=null) ?  Integer.parseInt(list.get("h2sjb")) : 0;
			int h2sEarned = (list.get("h2se") !=null) ?  Integer.parseInt(list.get("h2se")) : 0;
			int h2sManualBuffer = (list.get("h2smb") !=null) ?  Integer.parseInt(list.get("h2smb")) : 0;
			int h2sSent = (list.get("h2ss") !=null) ?  Integer.parseInt(list.get("h2ss")) : 0;
			int currentH2S = (h2sJoiningBonus + h2sEarned + h2sManualBuffer + h2sEmLinked) - h2sSent;
			return currentH2S;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsCountFromRedisBeforeUpgrade;
	}


	public void toggleNotificationCheckbox(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Change notification settings."+"\n"+
				"2.Note the status of all elements.");
		try {
			System.out.println("CHANGING NOTIFICATION SETTINGS");

			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME , SettingsScreen.NOTIFICATIONS_LBL);

			for(int i=0 ; i<5; i++){
				UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));
				object.click();
				System.out.println("CLICKING ON CHECKBOX");
				notificationCheckboxStatus.put(i, object.isChecked());


			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toggleAutoDownloadCheckbox(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Change auto download settings from auto download tab."+"\n"+
				"2.Note the status of all elements.");
		try {
			System.out.println("CHANGING AUTO DOWNLOAD SETTINGS .. update lib ");

			goToHome();
			openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME, SettingsScreen.AUTO_DOWNLOAD_MEDIA_LBL);
			UiObject listView = getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW, 0);
			UiObject lLayout = getChild(listView,Locators.CLASSNAME,AndroidClassNames.LINEAR_LAYOUT,2);
			UiObject checkbox = getChild(lLayout,Locators.CLASSNAME,AndroidClassNames.CHECKBOX,0);
			checkbox.click();
			autoDownloadCheckboxStatus.put(0, checkbox.isChecked());
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
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Add Hike contact as favorite."+"\n"+
				"2.Check favorite status from chat screen and timeline.");
		try {
			System.out.println("ADDING FAVORITE");

			goToHome();

			clickOnElement(Locators.NAME, objLocator.contacts_BTN);
			clickOnElement(Locators.NAME,"Add");
			//Assert.assertTrue(isElementPresentOnScreen("Added as a favorite"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void putAppInBackground(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Put app in background");
		try {
			System.out.println("PUTTING APP IN BACKGROUND");

			UiDevice.getInstance().pressHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateAppVersion(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Update app version");
		try {
			System.out.println("UPDATING APP VERSION");

			ExecuteShell exec = new ExecuteShell();
			exec.ExecuteShellCommand("pm", "install" , " -r", " /data/local/tmp/android-client-" + newAppVersion + ".apk");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while updating app version..");
		}
	}

	public void verifyDataPersistence(String version, String newVersion, UpdateLibrary ul){

		try {
			System.out.println("VERIFYING DATA PERSISTENCE");
			verifyEditedProfile();
			verifyHikeMessagePersistence(newVersion,ul);
			verifySmsMessagePersistence(newVersion,ul);
			verifyGroupChatPersistence(newVersion,ul);
			verifyStatusUpdatePersistence(newVersion);
			verifySmsCountPersistence(ul);
			verifyAppBuildNumber(newVersion);
			verifyNotificationsCheckboxPersistence(newVersion,ul);
			verifyAutoDownloadCheckboxPersistence(version, newVersion,ul);
			verifyPrivacyCheckboxPersistence(version,ul);
			verifyFavoriteListPersistence(ul);
			verifyBlockedUserPersistance(newVersion,ul);
			verifyAppVersionPersistenceFromServer();
			verifyStickerDownloadedStatus();
			//			verifyStickerEnableDisablePersistance();
			verifyAddedMemberToGroupPersists();
			verifyPinPersist(newVersion);
			verifyCreatedGroupAndMemberCountPersist(newVersion);
			verifyShareContentPersistance(newVersion);
			verifySharedMediaPersistance(newVersion);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while validating data persistence..");
		}
	}

	public void verifyEditedProfile(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Check profile screen."+"\n"+
				"2.Updated name should be visible.");
		try {
			System.out.println("VERIFYING UPDATED PROFILE");
			super.openOverflowMenu();
			clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
			Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,updatedName));
			clickOnElement(Locators.CONTENT_DESCRIPTION,SettingsScreen.VIEW_PROFILE);
			clickOnElement(Locators.CONTENT_DESCRIPTION,MyProfileScreen.OVERFLOW_MENU_LBL);
			clickOnElement(Locators.NAME,"Edit Profile");
			Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,PROFILE_EMAIL));
			goToHome();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify edited profile...");
		}
	}

	public void verifyEditedProfileAfterReset(){
		try {
			System.out.println("VERIFYING UPDATED PROFILE");
			super.openOverflowMenu();
			clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
			Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,DEFAULT_NAME));
			clickOnElement(Locators.CONTENT_DESCRIPTION,SettingsScreen.VIEW_PROFILE);
			clickOnElement(Locators.CONTENT_DESCRIPTION,MyProfileScreen.OVERFLOW_MENU_LBL);
			clickOnElement(Locators.NAME,"Edit Profile");
			Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,DEFAULT_NAME));
			goToHome();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify edited profile...");
		}
	}

	public void verifyHikeMessagePersistence(String old_version,UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Check chat with hike contact."+"\n"+
				"2.All messages should be present in chat thread.");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping hike message persistance test as old verison does not support backup");
			} else {
				System.out.println("VERIFYING HIKE MESSAGE PERSISTENCE");
				super.goToHome();
				//			clickOnElementContinueOnFail(Locators.NAME, objLocator.start_Adding_Favourites_BTN);
				//	        clickOnElementContinueOnFail(Locators.NAME, objLocator.skip_BTN);
				String contactName = ul.hikeMsgHm.keySet().iterator().next();
				System.out.println(contactName);
				List<String> listOfMessages = new ArrayList<String>();
				listOfMessages = ul.hikeMsgHm.get(contactName);
				Assert.assertTrue("No contact found.." , isElementPresentOnScreen(Locators.NAME,contactName));
				System.out.println(listOfMessages.get(0));
				System.out.println(listOfMessages.get(1));
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(0)) || isElementPresentOnScreen(Locators.NAME,listOfMessages.get(1)) || 
						isElementPresentOnScreen(Locators.NAME,"This is test SU"));
				clickOnElement(Locators.NAME,contactName);
				Thread.sleep(1000);
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(0)));
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(1)));
				Assert.assertTrue(isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION,"Hike Call"));
				super.goToHome();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while validating data persistence..");
		}
	}

	public void verifySmsMessagePersistence(String old_version,UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Check chat with SMS contact."+"\n"+
				"2.All messages should be present in chat thread.");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping sms message persistance test as old version does not support backup");
			} else {		
				System.out.println("VERIFYING sms MESSAGE PERSISTENCE");
				String contactName = ul.hikeMsgSm.keySet().iterator().next();
				System.out.println(contactName);
				List<String> listOfMessages = new ArrayList<String>();
				listOfMessages = ul.hikeMsgSm.get(contactName);
				Assert.assertTrue("No contact found.." , isElementPresentOnScreen(Locators.NAME,contactName));
				System.out.println(listOfMessages.get(0));
				//System.out.println(listOfMessages.get(1));
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(0)));// || isElementPresentOnScreen(listOfMessages.get(1)));
				clickOnElement(Locators.NAME,contactName);
				Thread.sleep(1000);
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(0)));
				//Assert.assertTrue(isElementPresentOnScreen(listOfMessages.get(1)));
				super.goToHome();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while validating data persistence..");
		}
	}

	public void verifyGroupChatPersistence(String old_version,UpdateLibrary ul){

		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Check group chat screen."+"\n"+
				"2.All messages should be present in chat thread.");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping group chat persistance as old version does not support backup");
			} else {
				System.out.println("VERIFYING GROUP MESSAGE PERSISTENCE");
				String groupName = ul.hikeMsGrp.keySet().iterator().next();
				System.out.println(groupName);
				List<String> listOfMessages = new ArrayList<String>();
				listOfMessages = ul.hikeMsGrp.get(groupName);
				System.out.println(listOfMessages.get(0));
				System.out.println(listOfMessages.get(1));
				Assert.assertTrue("No contact found.." , isElementPresentOnScreen(Locators.NAME,groupName));
				clickOnElement(Locators.NAME,groupName);
				//Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(0)) || isElementPresentOnScreen(Locators.NAME,listOfMessages.get(1)));
				Thread.sleep(1000);
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(0)));
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,listOfMessages.get(1)));
				UiDevice.getInstance().pressBack();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while validating data persistence..");
		}
	}

	public void verifyStatusUpdatePersistence(String old_version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Check the status update."+"\n"+
				"2.It should persist after upgrade.");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping status update peristance test");
			} else {	
				System.out.println("VERIFYING STATUS UPDATE PERSISTENCE");
				super.goToHome();
				clickOnElement(Locators.CONTENT_DESCRIPTION,HomeScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME,"Timeline");
				Assert.assertTrue(isElementPresentOnScreen(Locators.NAME,STATUS_UPDATE));
				//Assert.assertTrue(isElementPresentOnScreen(suList.get(1)));
			}

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while validating data persistance..");
		}
	}

	public void verifyAppBuildNumber(String new_version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify build number from settings screen.");
		try {
			System.out.println("VERIFYING app build number");
			String buildNumber = "Version " + new_version;
			super.goToHome();
			super.openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			Assert.assertTrue("Account is not present", isElementPresentOnScreen(Locators.NAME,SettingsScreen.ACCOUNT_LBL));
			searchElementInList(Locators.NAME, buildNumber);
			Assert.assertTrue("Build version is not mentioned in the botton of the page", isElementPresentOnScreen(Locators.NAME,buildNumber));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Error while validating app verion in app..");
		}
	}


	public void verifyAppVersionPersistenceFromServer(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify application version from server");
		try {
			System.out.println("VERIFYING APP VERSION  PERSISTENCE");
			DBCollection collection = userDB.getCollection(HikeConstants.MONGO_USERS_COLLECTION);
			System.out.println("CCCCCCCCCCCCCCCCCCCCCCCC"+collection.toString());
			BasicDBObject query = new BasicDBObject().append(HikeConstants.MSISDN, getDEFAULT_MSISDN());
			System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQ"+query.toString());

			BasicDBObject selector = new BasicDBObject().append(HikeConstants.MONGO_DEVICES, 1);
			System.out.println("SSSSSSSSSSSSSSSSSSSSSS"+selector.toString());

			DBObject result = collection.findOne(query, selector);
			if (result == null)
			{
				System.out.println("RRRRRRRRRRRRRRRRRRRRRR");

			}
			System.out.println("RRRRRRRRRRRRRRRRRRRRRR"+result.toString());

			List<Map<String, String>> devices = (List<Map<String, String>>) result.get(HikeConstants.MONGO_DEVICES);
			for (Map<String, String> device : devices)
			{
				if (device.get("dev_type").equals("android"))
				{
					System.out.println(device.get("app_version"));
					Assert.assertTrue(device.get("app_version").equals(newAppVersion));
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyUpdatedProfileInfo(){
		try {
			System.out.println("VERIFYING UPDATED PROFILE INFO");
			super.goToHome();
			super.openOverflowMenu();
			clickOnElement(Locators.NAME,OverFlowListScreen.MY_PROFILE_LBL);
			clickOnElement(Locators.CONTENT_DESCRIPTION, MyProfileScreen.OVERFLOW_MENU_LBL);

			clickOnElement(Locators.NAME, MyProfileOverflowOptionsScreen.EDIT_PROFILE_LBL);
			//waitForElement(Locators.NAME,DEFAULT_NAME, MIN_TIMEOUT);
			Assert.assertTrue("the name did not get updated", isElementPresentOnScreen(Locators.NAME,updatedName));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to verify updated profile info..");
		}
	}

	public void verifySmsCountPersistence(UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Check SMS count from Settings screen."+"\n"+
				"2.It should be same as before.");
		try {
			System.out.println("VERIFYING SMS COUNT PERSISTENCE");
			super.goToHome();
			super.openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			UiObject smsElementAfterUpgrade = new UiObject(new UiSelector().textContains("SMS"));
			System.out.println("element...."+smsElementAfterUpgrade);
			System.out.println(smsElementAfterUpgrade.getBounds().centerX()+"element...."+smsElementAfterUpgrade.getBounds().centerY());

			int smsCountFromUIAfterUpgrade = Integer.parseInt(smsElementAfterUpgrade.getText().split(":")[1].split("\\)")[0].trim());
			System.out.println("UI"+smsCountFromUIAfterUpgrade);
			System.out.println("smsCountFromUIAfterUpgrade"+smsCountFromUIAfterUpgrade);
			Assert.assertTrue(Integer.parseInt(ul.smsCountOnUiBeforeUpgrade) == smsCountFromUIAfterUpgrade);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Sms count after upgrade not same");
		}
	}


	public void verifyAutoDownloadCheckboxPersistence(String old_version, String new_version,UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Auto Download settings should be same after upgrade."+"\n"+
				"2.Default settings should be applicable to new options.");
		try {
			System.out.println("VERIFYING AUTO DOWNLOAD SETTING PERSISTENCE");
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

	public void verifyNotificationsCheckboxPersistence(String old_version, UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Notification settings should be same after upgrade."+"\n"+
				"2.Default settings should be applicable to new options.");
		try {
			System.out.println("VERIFYING NOTIFICATION SETTING PERSISTENCE");
			super.goToHome();
			super.openOverflowMenu();
			clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
			clickOnElement(Locators.NAME , SettingsScreen.NOTIFICATIONS_LBL);
			int count=0;

			for(int i=0 ; i<7 ; i++){

				if(old_version.startsWith("2.")&&(!old_version.equals("2.9.6"))&&(!old_version.equals("2.9.7"))){
					if(i==0){
						//vibrate
						boolean wasChecked = ul.notificationCheckboxStatus.get(i);
						if(wasChecked){
							clickOnElement(Locators.NAME, "Vibrate on new message");
							UiObject default_btn=getElement(Locators.NAME, "Default");
							Assert.assertTrue("Default is not checked", default_btn.isChecked());
							UiDevice.getInstance().pressBack();

						}else{
							clickOnElement(Locators.NAME, "Vibrate on new message");
							UiObject off=getElement(Locators.NAME, "Off");
							Assert.assertTrue("Off is not checked", off.isChecked());
							UiDevice.getInstance().pressBack();

						}
					}
					else if(i==2){
						//led
						boolean wasChecked = ul.notificationCheckboxStatus.get(2);
						if(wasChecked){
							clickOnElement(Locators.NAME, "Flash the LED on new message");
							UiObject White=getElement(Locators.NAME, "White");
							//							Assert.assertTrue("Default is not checked", White.isChecked());


						}else{
							clickOnElement(Locators.NAME, "Flash the LED on new message");
							UiObject None=getElement(Locators.NAME, "None");
							//							Assert.assertTrue("Default is not checked", None.isChecked());

						}
						UiDevice.getInstance().pressBack();

					}
					else if(i==3){
						//hike jingle
						boolean wasChecked = ul.notificationCheckboxStatus.get(1);
						if(wasChecked){
							clickOnElement(Locators.NAME, "Select a sound to play for a new notification");
							UiObject HikeJingle=getElement(Locators.NAME, "HikeJingle");
							Assert.assertTrue("HikeJingle is not checked", HikeJingle.isChecked());

						}else{
							clickOnElement(Locators.NAME, "Select a sound to play for a new notification");
							UiObject off=getElement(Locators.NAME, "Off");
							Assert.assertTrue("Off is not checked", off.isChecked());
						}
						UiDevice.getInstance().pressBack();

					}
					else if(i==5||i==6||i==1){
						//default for nuj and h2o
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(count));
						boolean isChecked = object.isChecked();

						Assert.assertTrue("Default setting is not right",isChecked );
						count++;

					}
					else{
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(count));
						boolean wasChecked = ul.notificationCheckboxStatus.get(i);
						boolean isChecked = object.isChecked();
						System.out.println(wasChecked);
						System.out.println(isChecked);

						Assert.assertTrue(wasChecked==isChecked);
						count++;
					}
				}
				else if(old_version.equals("2.9.6")||old_version.equals("2.9.7")||old_version.equals("3.0.0")||old_version.equals("3.0.1")||old_version.equals("3.1.0")||old_version.equals("3.2.0")){
					if(i==0){
						clickOnElement(Locators.NAME, "Vibrate on new message");
						UiObject off = new UiObject(new UiSelector().className("android.widget.CheckedTextView").instance(0));

						//						UiObject off=getElement(Locators.NAME, "Off");
						Assert.assertTrue("Off is not checked", off.isChecked());
						UiDevice.getInstance().pressBack();

					}
					else if(i==2){
						//led
						boolean wasChecked = ul.notificationCheckboxStatus.get(2);
						if(wasChecked){
							clickOnElement(Locators.NAME, "Flash the LED on new message");
							UiObject White=getElement(Locators.NAME, "White");
							//							Assert.assertTrue("Default is not checked", White.isChecked());


						}else{
							clickOnElement(Locators.NAME, "Flash the LED on new message");
							UiObject None=getElement(Locators.NAME, "None");
							//							Assert.assertTrue("Default is not checked", None.isChecked());

						}
						UiDevice.getInstance().pressBack();

					}
					else if(i==3){
						clickOnElement(Locators.NAME, "Select a sound to play for a new notification");
						UiObject off=getElement(Locators.NAME, "Off");
						Assert.assertTrue("Off is not checked", off.isChecked());
						UiDevice.getInstance().pressBack();

					}
					else if(i==5||i==6){
						//default for nuj and h2o
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(count));
						boolean isChecked = object.isChecked();

						Assert.assertTrue("Default setting is not right",isChecked );
						count++;

					}
					else{
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(count));
						boolean wasChecked = ul.notificationCheckboxStatus.get(i);
						boolean isChecked = object.isChecked();

						Assert.assertTrue(wasChecked==isChecked);
						count++;
					}
				}
				if(old_version.equals("3.3.0")||old_version.equals("3.3.1")||old_version.equals("3.3.5")){
					if(i==0){
						clickOnElement(Locators.NAME, "Vibrate on new message");
						UiObject off = new UiObject(new UiSelector().className("android.widget.CheckedTextView").instance(0));

						//UiObject off=getElement(Locators.NAME, "Off");
						Assert.assertTrue("Off is not checked", off.isChecked());
						UiDevice.getInstance().pressBack();
					}
					else if(i==2){
						//led
						boolean wasChecked = ul.notificationCheckboxStatus.get(2);
						if(wasChecked){
							clickOnElement(Locators.NAME, "Flash the LED on new message");
							UiObject White=getElement(Locators.NAME, "HikeJingle");
							Assert.assertTrue("Default is not checked", White.isChecked());


						}else{
							clickOnElement(Locators.NAME, "Flash the LED on new message");
							UiObject None=getElement(Locators.NAME, "None");
							Assert.assertTrue("Default is not checked", None.isChecked());

						}
						UiDevice.getInstance().pressBack();

					}
					else if(i==3){
						clickOnElement(Locators.NAME, "Select a sound to play for a new notification");
						UiObject off = new UiObject(new UiSelector().className("android.widget.CheckedTextView").instance(0));

						//						UiObject off=getElement(Locators.NAME, "Off");
						Assert.assertTrue("Off is not checked", off.isChecked());
						UiDevice.getInstance().pressBack();


					}
					else{
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(count));
						boolean wasChecked = ul.notificationCheckboxStatus.get(i);
						boolean isChecked = object.isChecked();

						Assert.assertTrue(wasChecked==isChecked);
						count++;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyFavoriteListPersistence(UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Favorite added before upgrade should be present.");
		try {
			System.out.println("VERIFYING FAVORITE LIST PERSISTENCE");
			super.goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HomeScreen.NEW_CHAT_LBL);

			Assert.assertTrue("Favorite does not persist",isElementPresentOnScreen(Locators.NAME,NewChatContactSelectScreen.FAVORITE_LBL));
			clickOnElement(Locators.NAME, ul.favorite);
			clickOnElement(Locators.NAME, ul.favorite);
			clickOnElement(Locators.CONTENT_DESCRIPTION,ProfileScreen.OVERFLOW_ICON);
			Assert.assertTrue("Favorite does not persist",isElementPresentOnScreen(Locators.NAME, ProfileOverflowScreen.REMOVE_FAV_LBL));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Favorite list not persisting after upgrade");
		}
	}

	public void deleteUserAccount(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"Delete Account");

		try{
			System.out.println("deleting account");
			super.goToHome();
			deleteUser();

			Assert.assertTrue("not able to delete account", isElementPresentOnScreen(Locators.NAME,WelcomeScreen.GET_STARTED_BTN));


			//Assert.assertTrue("Welcome Screen Does Not Appear", isElementPresentOnScreen(objLocator.welcome_To_Hike_Messenger_LBL));
		}catch(Exception e){
			System.out.println("User is not able to delete account");
			Assert.fail("Unable to delete account..");
		}
	}

	public void uninstallApp(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"2.Uninstall app.");

		try {
			System.out.println("UNINSTALLING APP");
			ExecuteShell exec = new ExecuteShell();
			exec.ExecuteShellCommand("pm", "uninstall" , "com.bsb.hike");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to delete the app");
		}
	}

	public void ableToSendMessage(String msisdn) throws UiObjectNotFoundException, InterruptedException {
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Create new chat and send message after upgrade");
		try {
			System.out.println("SENDING MESSAGE");
			String message = "message after upgrade..";
			super.goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HomeScreen.NEW_CHAT_LBL);

			enterText(msisdn);

			clickOnElement(Locators.NAME, NewChatContactSelectScreen.TAP_TO_START_CHAT_LBL);
			enterText(message);
			sendMessageNewVersion();

			Assert.assertTrue("User is not able to Send Message", isElementPresentOnScreen(Locators.NAME,message));

			super.goToHome();

//			Assert.assertTrue("User is not able to Send Message", isElementPresentOnScreen(Locators.NAME,ConversationScreen.LBL_NOW_AGO_LBL));
			Assert.assertTrue("User is not able to Send Message", isElementPresentOnScreen(Locators.NAME,ConversationScreen.LBL_MOMENTS_AGO_LBL));

			Assert.assertTrue("User is not able to Send message", isElementPresentOnScreen(Locators.NAME,message));
			//    		Assert.assertTrue("User is not able to send Message", isElementPresentOnScreen(name))// need to assert for message status after getting the name


		} catch (Exception e) {

		}

	}

	public void ableToCreateGc() throws UiObjectNotFoundException, InterruptedException{
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Create group"+"\n"+
				"2.send message to it.");

		System.out.println("CREATING GC");
		super.goToHome();
		String groupName = "Automation group "+ RandomStringUtils.randomNumeric(4);
		String groupMessage = "groupMessage";
		int numberOfSelection=0;
		//deleteAllExistingChats();
		super.openOverflowMenu();
		clickOnElement(Locators.NAME, OverFlowListScreen.NEW_GROUP_LBL);
		enterText(groupName);
		clickOnElement(Locators.NAME, NewGroupScreen.NEXT_LBL);

		clickElementInList(Locators.NAME,HIKE_CONTACT_NAME);
		numberOfSelection++;
		clickElementInList(Locators.NAME,HIKE_CONTACT_NAME_1);
		numberOfSelection++;

		clickOnElement(Locators.NAME, NewGroupParticipantSelectionScreen.DONE_LBL);
		//Assert.assertTrue("user is not taken back to chats tab", isElementSelected(Locators.NAME,objLocator.Chats_tab));
		enterText(groupMessage);
		sendMessageNewVersion();
		//    	Assert.assertTrue("User Not able to Send GC message", isElementPresentOnScreen(objLocator.Hike_GroupMessage_Hint_Text_Txt));
		Assert.assertTrue("User is not able to Send GC message", isElementPresentOnScreen(Locators.NAME,groupMessage));
		UiDevice.getInstance().pressBack();
		if(!isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL)){
			UiDevice.getInstance().pressBack();
		}
//		Assert.assertTrue("User is not able to Send Message", isElementPresentOnScreen(Locators.NAME,ConversationScreen.LBL_NOW_AGO_LBL));
		Assert.assertTrue("User is not able to Send Message", isElementPresentOnScreen(Locators.NAME,ConversationScreen.LBL_MOMENTS_AGO_LBL));

		Assert.assertTrue("User is not able to Send message", isElementPresentOnScreen(Locators.NAME,groupMessage));


	}

	public void abletoUpdateStatus() throws UiObjectNotFoundException, InterruptedException {
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Post a new status after upgrade");
		try {
			System.out.println("UPDATING STATUS");
			super.goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION,HomeScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME,"Timeline");
			clickOnElement(Locators.CONTENT_DESCRIPTION, TimelineScreen.POST_A_NEW_UPDATE_ICON);
			enterText(STATUS_UPDATE+STATUS_UPDATE);
			clickOnElement(Locators.NAME,objLocator.Post_Button);
			Assert.assertTrue("Failed to Update Status", isElementPresentOnScreen(Locators.NAME,STATUS_UPDATE+STATUS_UPDATE));

		} catch (Exception e) {

		}
	}

	public void ableToAddFavorite(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Add a new favorite after upgrade");

		try {
			System.out.println("ADDING FAVORITE");
			super.goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION,HomeScreen.OVERFLOW_ICON);
			clickOnElement(Locators.NAME,"Timeline");
			clickOnElement(Locators.CONTENT_DESCRIPTION,TimelineScreen.FAV_IN_TIMELINE_ICON);
			UiObject llayout=getElement(Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 3);
			String fav_name;
			//Check if add button is present or not
			if(isElementPresentOnScreen(Locators.NAME,"Add")){
				UiObject add=getElement(Locators.NAME,"Add");
				UiObject name=add.getFromParent(new UiSelector().className(AndroidClassNames.TEXT_VIEW).index(0));
				fav_name=name.getText();

				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm"+fav_name);
				clickOnElement(add);
			}
			else{
				UiObject fav=getChild(llayout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 2);
				UiObject name=fav.getFromParent(new UiSelector().className(AndroidClassNames.TEXT_VIEW).index(0));
				fav_name=name.getText();
				System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmm"+fav_name);
				clickOnElement(fav);

			}
			//Check for favorite popup
			if(isElementPresentOnScreen(Locators.NAME, "Yes")){
				clickOnElement(Locators.NAME, "Yes");
			}

			super.goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HomeScreen.NEW_CHAT_LBL);

			Assert.assertTrue("not able to add favorite",isElementPresentOnScreen(Locators.NAME,NewChatContactSelectScreen.FAVORITE_LBL));
			System.out.println("ssssssssssssssssssssssss"+fav_name);
			clickOnElement(Locators.NAME, fav_name);
			clickOnElement(Locators.NAME, fav_name);
			clickOnElement(Locators.CONTENT_DESCRIPTION,ProfileScreen.OVERFLOW_ICON);
			Assert.assertTrue("not able to add favorite",isElementPresentOnScreen(Locators.NAME, ProfileOverflowScreen.REMOVE_FAV_LBL));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to add favorite post upgrade");
		}
	}

	public void sendSticker(String old_version) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Open chat thead."+"\n"+
				"2.Click on sticker icon."+
				"3.Popup to download should not appear.");
		if (old_version.equals("3.5.1")) {
			goToHome();
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HomeScreen.NEW_CHAT_LBL);

			enterText(HIKE_NUMBER_1);
			clickOnElement(Locators.NAME,NewChatContactSelectScreen.TAP_TO_START_CHAT_LBL);
			UiDevice.getInstance().click(40, 760);
			if(isElementPresentOnScreen(Locators.NAME, "OK")){
				clickOnElement(Locators.NAME, "OK");
			}
			else if(isElementPresentOnScreen(Locators.NAME, "Download")){
				clickOnElement(Locators.NAME, "Download");
			}

		} else {
			goToHome();	
			searchElementInList(Locators.NAME, HIKE_CONTACT_NAME);
			clickOnElement(Locators.NAME, HIKE_CONTACT_NAME);
			UiDevice.getInstance().click(40, 760);
			if(isElementPresentOnScreen(Locators.NAME, "OK")){
				clickOnElement(Locators.NAME, "OK");
			}
			else if(isElementPresentOnScreen(Locators.NAME, "Download")){
				clickOnElement(Locators.NAME, "Download");
			}
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
	public void verifyStickerDownloadedStatus(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify downloaded sticker persist after upgrade.");
		try {

			goToHome();

			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HomeScreen.NEW_CHAT_LBL);

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
			UiObject rlayout = getElement(Locators.CLASSNAME, "android.widget.RelativeLayout", 1);
			UiObject llayout = getChild(rlayout, Locators.CLASSNAME, "android.widget.LinearLayout", 1);
			System.out.println("ssssss"+llayout.getChildCount());
			Assert.assertEquals(llayout.getChildCount(), 2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stickerEnableDisable(){
		try {
			System.out.println("Sticker enable disable");
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
			//			Thread.sleep(2000);
			//			UiScrollable scr = new UiScrollable(new UiSelector().className(AndroidClassNames.LIST_VIEW).scrollable(true));
			//			scr.flingToEnd(MAX_TIMEOUT);

			clickOnElement(Locators.CONTENT_DESCRIPTION,StickerShop.STICKER_SHOP_SETTING_LBL);
			//			UiObject view1 = getElement(Locators.CLASSNAME, "android.view.View");

			UiSelector viewSelector = new UiSelector().className(AndroidClassNames.LIST_VIEW).childSelector(new UiSelector().className(AndroidClassNames.VIEW));
			UiCollection children = new UiCollection(viewSelector);

			System.out.println(children.getChildCount());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyStickerEnableDisablePersistance(){
		try {
			System.out.println("Verifying sticker enable disable");
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
			Thread.sleep(2000);
			clickOnElement(Locators.CONTENT_DESCRIPTION, StickerShop.STICKER_SHOP_SETTING_LBL);

			for(int i=0 ; i<5 ; i++){	
				UiObject object = new UiObject(new UiSelector().className("android.widget.ImageButton").instance(i));					
				boolean wasChecked = stickerCheckboxStatus.get(i);
				boolean isChecked = object.isChecked();	
				Assert.assertTrue(wasChecked==isChecked);					
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void addMemberToGroup(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1. Add member to the created group.");
		try {
			clickOnElement(Locators.NAME, groupName);
			clickOnElement(Locators.NAME, groupName);
			Thread.sleep(2000);
			clickOnElement(Locators.NAME,GroupInfoScreen.ADD_A_MEMBER_ICON);
			clickElementInList(Locators.NAME, HIKE_CONTACT_NAME);
			clickElementInList(Locators.NAME,HIKE_CONTACT_NAME_1);
			clickOnElement(Locators.NAME,NewGroupParticipantSelectionScreen.DONE_LBL);
			Assert.assertTrue("Failed to add member to the group", isElementPresentOnScreen(Locators.NAME,ChatEventsObject.GROUP_CHAT_NAME_ADDED_EVENT +HIKE_CONTACT_NAME_1+" and "+HIKE_CONTACT_NAME+ChatEventsObject.GROUP_CHAT_ADD_PARTICIPANT_EVENT));
			//			Assert.assertTrue("Failed to add member to the group", isElementPresentOnScreen(Locators.NAME,ChatEventsObject.GROUP_CHAT_NAME_ADDED_EVENT +HIKE_CONTACT_NAME+" and "+HIKE_CONTACT_NAME_1+ChatEventsObject.GROUP_CHAT_ADD_PARTICIPANT_EVENT));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyAddedMemberToGroupPersists(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify added member persist in the group.");
		try {
			System.out.println("Verifying added member peristance after upgrade");
			goToHome();
			clickOnElement(Locators.NAME, groupName);
			clickOnElement(Locators.NAME, groupName);
			Thread.sleep(2000);
			Assert.assertTrue("Recently added user not found after upgrade", isElementPresentOnScreen(Locators.NAME,HIKE_CONTACT_NAME));
			Assert.assertTrue("Recently added user not found after upgrade", isElementPresentOnScreen(Locators.NAME,HIKE_CONTACT_NAME_1));
			Assert.assertTrue("Group member is not correct, it should be 5", isElementPresentOnScreen(Locators.NAME,"5/500"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPin(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1. Add pin in the group.");
		try {
			goToHome();
			clickOnElement(Locators.NAME, groupName);
			clickOnElement(Locators.CONTENT_DESCRIPTION,ChatThreadScreen.PIN_ICON);
			enterText(GC_PIN);
			clickOnElement(Locators.NAME,ChatThreadScreen.PIN_BTN);
			Assert.assertTrue("Failed to post pin", isElementPresentOnScreen(Locators.NAME,ChatThreadScreen.YOU_POSTED_A_PIN_LBL));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyPinPersist(String old_version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify added pin persist in the group.");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping pin persitance test as old version does not support backup");
			} else {			
				System.out.println("Verifying group pin peristance after upgrade");
				goToHome();
				clickOnElement(Locators.NAME, groupName);
				clickOnElement(Locators.NAME, groupName);
				clickOnElement(Locators.CONTENT_DESCRIPTION,GroupInfoScreen.SHARED_PINS_LBL);		
				Assert.assertTrue("Recently added pin does not persist after upgrade", isElementPresentOnScreen(Locators.NAME,GC_PIN));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void muteGroup(){
		try {
			goToHome();
			clickOnElement(Locators.NAME, groupName);
			clickOnElement(Locators.CONTENT_DESCRIPTION,ChatThreadScreen.OVERFLOW_ICON);	
			clickOnElement(Locators.NAME,GroupChatThreadOverflowListScreen.MUT_GROUP_LBL);
			Assert.assertTrue("Failed to mute group", isElementPresentOnScreen(Locators.NAME,MuteGroupTip.THIS_CONVERSATION_IS_MUTED_LBL));
			clickOnElement(Locators.CONTENT_DESCRIPTION,ChatThreadScreen.OVERFLOW_ICON);
			Assert.assertTrue("Option to unmute group is not visible under overflow", isElementPresentOnScreen(Locators.NAME,GroupChatThreadOverflowListScreen.UNMUT_GROUP_LBL));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyMutePersist(){
		try {
			goToHome();
			clickOnElement(Locators.NAME, groupName);
			Assert.assertTrue("Failed to mute group", isElementPresentOnScreen(Locators.NAME,MuteGroupTip.THIS_CONVERSATION_IS_MUTED_LBL));
			clickOnElement(Locators.CONTENT_DESCRIPTION, ChatThreadScreen.OVERFLOW_ICON);	
			Assert.assertTrue("Option to unmute group is not visible under overflow", isElementPresentOnScreen(Locators.NAME,GroupChatThreadOverflowListScreen.UNMUT_GROUP_LBL));

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
			Assert.assertTrue("Failed to create group of 4 members",isElementPresentOnScreen(Locators.NAME,"4"+GroupInfoScreen.PEOPLE_LBL));
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
	public void verifyCreatedGroupAndMemberCountPersist(String version){ 
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify created group and its added members count");
		try {
			System.out.println("Verifying group peristance after upgrade");
			goToHome();
			Assert.assertTrue("Created group does not persists after upgrade",isElementPresentOnScreen(Locators.NAME,Updated_Test_Group_Name));
			clickOnElement(Locators.NAME, Updated_Test_Group_Name);
			Assert.assertTrue("Failed to persist group members count",isElementPresentOnScreen(Locators.NAME,"4"+GroupInfoScreen.PEOPLE_LBL));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shareContentToGroup(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1. Share file in the group."+"\n"+
				"2. verify file gets shared and visible under group info.");
		try {
			System.out.println("Sharing content to the group");
			goToHome();
			clickOnElement(Locators.NAME, Updated_Test_Group_Name);
			sendMessage(PinHash+DEFAULT_PINHASH);
			Assert.assertTrue("Failed to post pin hash tag", isElementPresentOnScreen(Locators.NAME, ChatThreadScreen.YOU_LBL+DEFAULT_PINHASH));
			clickOnElement(Locators.CONTENT_DESCRIPTION, ChatThreadScreen.ATTACH_ICON);
			clickOnElement(Locators.NAME, AttachmentListScreen.FILE_LBL);
			clickOnElement(Locators.NAME, "Internal Storage");
			clickOnElement(Locators.NAME, "Hike");
			clickOnElement(Locators.NAME, "Media");
			clickElementInList(Locators.NAME, "hikeFiles");
			Assert.assertTrue("Failed to share file", isElementPresentOnScreen(Locators.NAME, "hikeFiles"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyShareContentPersistance(String old_version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify shared content persist in the group.");
		try {

			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping pin persitance test as old version does not support backup");
			} else {
				System.out.println("Verifying group shared contents peristance after upgrade");
				goToHome();
				clickOnElement(Locators.NAME, Updated_Test_Group_Name);
				clickOnElement(Locators.CONTENT_DESCRIPTION, ChatThreadScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME, GroupChatThreadOverflowListScreen.GROUP_INFO_LBL);
				Assert.assertTrue("Shared content section is not visible", isElementPresentOnScreen(Locators.NAME,GroupInfoScreen.SHARED_CONTENT_LBL));
				Assert.assertTrue("Shared Files section is not visible", isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION,GroupInfoScreen.SHARED_FILES_LBL));
				Assert.assertTrue("Shared Pins section is not visible", isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION,GroupInfoScreen.SHARED_PINS_LBL));
				clickOnElement(Locators.CONTENT_DESCRIPTION, GroupInfoScreen.SHARED_FILES_LBL);
				Assert.assertTrue("Shared file does not exists after upgrade",isElementPresentOnScreen(Locators.NAME, "hikeFiles"));
				clickOnElement(Locators.NAME,FileScreen.FILES_TITLE_LBL);
				clickOnElement(Locators.CONTENT_DESCRIPTION, GroupInfoScreen.SHARED_PINS_LBL);
				Assert.assertTrue("Shared Pin does not exists after upgrade",isElementPresentOnScreen(Locators.NAME,DEFAULT_PINHASH));
				Assert.assertTrue("Failed to switch to Pin history screen",isElementPresentOnScreen(Locators.NAME, PinHistoryScreen.PIN_HISTORY_TITLE_LBL));
				goToHome();
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
			Assert.assertTrue("Failed to switch to gallery view", isElementPresentOnScreen(Locators.NAME, ImageSelectionScreen.TAP_AND_HOLD_TO_SELECT_MULTIPLE_FILES));

//			Assert.assertTrue("Failed to switch to gallery view", isElementPresentOnScreen(Locators.NAME, ImageSelectionScreen.CHOOSE_A_PHOTO));
			UiDevice.getInstance().click(108, 267);
			clickOnElement(Locators.NAME,"Send");

//			clickOnElement(Locators.NAME,"Next");
//			clickOnElement(Locators.NAME, PreviewScreen.SEND_BTN);
			clickOnElement(Locators.NAME, ChooseImageQualityScreen.SEND_BTN);
			Assert.assertTrue("Failed to redirect to chat thread",isElementPresentOnScreen(Locators.NAME,"4"+GroupInfoScreen.PEOPLE_LBL));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifySharedMediaPersistance(String old_version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify shared media persist in the group.");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping pin persitance test as old version does not support backup");
			} else {		
				System.out.println("Verifying group shared media peristance after upgrade");
				goToHome();
				clickOnElement(Locators.NAME, Updated_Test_Group_Name);
				clickOnElement(Locators.CONTENT_DESCRIPTION, ChatThreadScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME, GroupChatThreadOverflowListScreen.GROUP_INFO_LBL);
				Assert.assertTrue("Shared content section is not visible", isElementPresentOnScreen(Locators.NAME,GroupInfoScreen.SHARED_MEDIA_LBL));
				UiObject list = getElement(Locators.CLASSNAME, "android.widget.ListView");
				UiObject llayout = getChild(list, Locators.CLASSNAME, "android.widget.LinearLayout", 1);
				UiObject llayout1 = getChild(llayout, Locators.CLASSNAME, "android.widget.LinearLayout", 1);
				UiObject media = getChild(llayout1, Locators.CLASSNAME, "android.widget.RelativeLayout", 0);
				clickOnElement(media);
				Assert.assertTrue("", isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION,MediaViewerScreen.FORWARD_ICON));
				goToHome();
			}
		} catch (Exception e) {
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

	public void verifyBlockedUserPersistance(String old_version,UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Verify blocked user persist after upgrade.");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Verifying blocked user peristance after upgrade from settings");

				clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME,SettingsScreen.PRIVACY_LBL);
				clickOnElement(Locators.NAME,PrivacyScreen.BLOCKED_LIST_LBL);
				enterText(HIKE_CONTACT_NAME_4);
				UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(0));					
				boolean wasChecked = ul.blockUserCheckboxStatus.get(0);
				System.out.println("wasss"+wasChecked);
				boolean isChecked = object.isChecked();	
				System.out.println("isss"+isChecked);
				Assert.assertTrue(wasChecked==isChecked);
				UiObject object1 = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(0));					
				object1.click();
				clickOnElement(Locators.NAME,BlockedListScreen.SAVE_BTN);
				goToHome();
			} else {
				System.out.println("Verifying blocked user peristance after upgrade, under chat thread and blocked user list");
				goToHome();
				clickOnElement(Locators.NAME, HIKE_CONTACT_NAME_4);
				//			Assert.assertTrue("failed to show blocked user as Blocked under chat thread", isElementPresentOnScreen(Locators.NAME,"You've blocked FourthTestUser. Unblock and continue hiking."));
				goToHome();
				clickOnElement(Locators.CONTENT_DESCRIPTION,HomeScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME,SettingsScreen.PRIVACY_LBL);
				clickOnElement(Locators.NAME,PrivacyScreen.BLOCKED_LIST_LBL);
				enterText(HIKE_CONTACT_NAME_4);
				UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(0));					
				boolean wasChecked = ul.blockUserCheckboxStatus.get(0);
				System.out.println("wasss"+wasChecked);
				boolean isChecked = object.isChecked();	

				System.out.println("isss"+isChecked);
				Assert.assertTrue(wasChecked==isChecked);
				UiObject object1 = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(0));					
				object1.click();
				clickOnElement(Locators.NAME,BlockedUserScreen.SAVE_LBL);
				goToHome();
			}
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
			for(int i=0 ; i<4; i++){
				if(i==0){
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

	public void verifyPrivacyCheckboxPersistence(String old_version,UpdateLibrary ul){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Last seen settings should be same after upgrade."+"\n"+
				"2.Default settings should be applicable to new options.");
		if(old_version.equals("3.9.2") || old_version.equals("3.9.6")){
	
			try {
				System.out.println("VERIFYING PRIVACY SETTING PERSISTENCE");
				super.goToHome();
				super.openOverflowMenu();
				clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME , SettingsScreen.PRIVACY_LBL);

				for(int i=0;i<5;i++){
					System.out.println(i);
					if(i==0){
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));
						boolean wasChecked = ul.privacyCheckboxStatus.get(i);
						boolean isChecked = object.isChecked();
						System.out.println(wasChecked);
						System.out.println(isChecked);
						Assert.assertTrue(wasChecked==isChecked);
					}
					else if(i==1){
						UiObject listView = getElement(Locators.CLASSNAME, "android.widget.ListView");
						UiObject llayout = getChild(listView, Locators.CLASSNAME, "android.widget.LinearLayout", 2);				
						UiObject lastSeenLbl = getChild(llayout,Locators.CLASSNAME, "android.widget.TextView", 1);
						System.out.println(lastSeenLbl.getText());
						Assert.assertTrue("Show to my contacts is not present",lastSeenLbl.getText().contains("Show Last Seen : My Contacts"));
					}
					if(i==2){
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i-1));
						boolean wasChecked = ul.privacyCheckboxStatus.get(i);
						boolean isChecked = object.isChecked();
						System.out.println(wasChecked);
						System.out.println(isChecked);
						Assert.assertTrue(wasChecked==isChecked);
					}
					else if (i==3){
						boolean wasChecked = ul.privacyCheckboxStatus.get(i);
						System.out.println(wasChecked);
						Assert.assertFalse("Blocked list default setting does not persists",wasChecked);
					}
					else{
						UiObject listView = getElement(Locators.CLASSNAME, "android.widget.ListView");
						UiObject llayout = getChild(listView, Locators.CLASSNAME, "android.widget.LinearLayout", 6);				
						UiObject favouriteLbl = getChild(llayout,Locators.CLASSNAME, "android.widget.TextView", 1);	
						System.out.println(favouriteLbl.getText());
						Assert.assertTrue("Favourite privacy setting is not coming", favouriteLbl.getText().equals(PrivacyScreen.FAVOURITE_LBL));
					}

				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}

		}
		else{
			try {
				System.out.println("VERIFYING PRIVACY SETTING PERSISTENCE");
				super.goToHome();
				super.openOverflowMenu();
				clickOnElement(Locators.NAME, OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME , SettingsScreen.PRIVACY_LBL);

				for(int i=0;i<5;i++){
					System.out.println(i);
					if(i==0){
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i));
						boolean wasChecked = ul.privacyCheckboxStatus.get(i);
						boolean isChecked = object.isChecked();
						System.out.println(wasChecked);
						System.out.println(isChecked);
						Assert.assertTrue(wasChecked==isChecked);
					}
					else if(i==1){
						UiObject listView = getElement(Locators.CLASSNAME, "android.widget.ListView");
						UiObject llayout = getChild(listView, Locators.CLASSNAME, "android.widget.LinearLayout", 2);				
						UiObject lastSeenLbl = getChild(llayout,Locators.CLASSNAME, "android.widget.TextView", 1);
						System.out.println(lastSeenLbl.getText());
						Assert.assertTrue("Show to My contacts is not present",lastSeenLbl.getText().contains("Show Last Seen : My Contacts"));
					}
					else if (i==2){
						UiObject object = new UiObject(new UiSelector().className("android.widget.CheckBox").instance(i-1));
						boolean wasChecked = ul.privacyCheckboxStatus.get(i);
						boolean isChecked = object.isChecked();
						System.out.println(wasChecked);
						System.out.println(isChecked);
						Assert.assertTrue(wasChecked==isChecked);
					}
					else if (i==3){
						boolean wasChecked = ul.privacyCheckboxStatus.get(i);
						System.out.println(wasChecked);
						Assert.assertFalse("Blocked list default setting does not persists",wasChecked);
					}
					else{
						UiObject listView = getElement(Locators.CLASSNAME, "android.widget.ListView");
						UiObject llayout = getChild(listView, Locators.CLASSNAME, "android.widget.LinearLayout", 6);				
						UiObject favouriteLbl = getChild(llayout,Locators.CLASSNAME, "android.widget.TextView", 1);	
						System.out.println(favouriteLbl.getText());
						Assert.assertTrue("Favourite privacy setting is not coming", favouriteLbl.getText().equals(PrivacyScreen.FAVOURITE_LBL));
					}

				}

			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	 

	public void resetUserAccount(){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Reset user account");
		try{
			System.out.println("Resetting Account");
			super.goToHome();
			resetUser();
			Assert.assertTrue("not able to reset account", isElementPresentOnScreen(Locators.NAME,WelcomeScreen.GET_STARTED_BTN));
			//Assert.assertTrue("Welcome Screen Does Not Appear", isElementPresentOnScreen(objLocator.welcome_To_Hike_Messenger_LBL));
		}catch(Exception e){
			System.out.println("User is not able to reset account");
			Assert.fail("Unable to reset account..");
		}
	}

	public void backUpAccount(String version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Go to account settings "+"\n"+
				"2.Take user account backup");

		try {
			if (version.equals("3.5.1")) {
				System.out.println("Skipping backup as version does not have backup functionality");

			} else {
				goToHome();
				clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME,SettingsScreen.ACCOUNT_LBL);
				Assert.assertTrue("Backup default text is not present for new user", isElementPresentOnScreen(Locators.NAME,AccountScreen.BACK_UP_LBL));
				UiObject lview = getElement(Locators.CLASSNAME,"android.widget.ListView");
				UiObject llayout = getChild(lview, Locators.CLASSNAME, "android.widget.LinearLayout", 0);
				UiObject rlayout = getChild(llayout, Locators.CLASSNAME, "android.widget.RelativeLayout", 0);	 
				UiObject backUpText = getChild(rlayout, Locators.CLASSNAME, "android.widget.TextView", 2);
				String textBeforeBackup = backUpText.getText();
				Assert.assertTrue(textBeforeBackup.contains("missing"));
				System.out.println(textBeforeBackup);
				clickOnElement(Locators.NAME, AccountScreen.BACK_UP_LBL);
				textAfterBackup = backUpText.getText();
				System.out.println(textAfterBackup);
				Assert.assertNotEquals("Backup Text not get changed, after taking backup", textBeforeBackup, textAfterBackup);
				clickOnElement(Locators.NAME, AccountScreen.ACCOUNT_TITLE_LBL);
				clickOnElement(Locators.NAME, SettingsScreen.SETTTINGS_TITLE_LBL);
				waitForElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL, MIN_TIMEOUT);
			}



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyBackUpTextPersistance(String old_version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Go to account settings "+"\n"+
				"2.Verify backup text");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping backup persistance test as old version does not support backup");
			} else {
				System.out.println("VERIFYING BACKUP TEXT");
				clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME,SettingsScreen.ACCOUNT_LBL);
				UiObject lview = getElement(Locators.CLASSNAME,"android.widget.ListView");
				UiObject llayout = getChild(lview, Locators.CLASSNAME, "android.widget.LinearLayout", 0);
				UiObject rlayout = getChild(llayout, Locators.CLASSNAME, "android.widget.RelativeLayout", 0);	 
				UiObject backUpText = getChild(rlayout, Locators.CLASSNAME, "android.widget.TextView", 2);
				String backupTextAfterReset = backUpText.getText();
				Assert.assertEquals("Backup Text got changed, after resetting account", backupTextAfterReset, textAfterBackup);
				clickOnElement(Locators.NAME, AccountScreen.ACCOUNT_TITLE_LBL);
				clickOnElement(Locators.NAME, SettingsScreen.SETTTINGS_TITLE_LBL);
				waitForElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL, MIN_TIMEOUT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void restoreAccount(String old_version){
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Restore user account backup while re-signup");
		try {
			if (old_version.equals("3.5.1")) {
				System.out.println("Skipping restore account as old version does not support backup");
			} else {
				waitForElement(Locators.NAME, BackupScreen.WOULD_YOU_LBL, MIN_TIMEOUT);
				UiObject imgView= getElement(Locators.CLASSNAME,AndroidClassNames.IMAGE_VIEW,3);
				Thread.sleep(2000);
				getUiDevice().getInstance().click(imgView.getBounds().centerX(),imgView.getBounds().bottom+110);
				waitForElement(Locators.NAME, "your account and messages", MAX_TIMEOUT);  
				if(isElementPresentOnScreen(Locators.NAME , objLocator.Awesome_BTN)){
					clickOnElementContinueOnFail(Locators.NAME, objLocator.Awesome_BTN);
				}       
			}
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			clickOnElement(Locators.NAME, HomeScreen.NEW_CHAT_LBL);
			enterText(HIKE_NUMBER_1);
			clickOnElement(Locators.NAME,NewChatContactSelectScreen.TAP_TO_START_CHAT_LBL);
			super.goToHome();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addDndMemberToGroup(){
		try {
			clickOnElement(Locators.NAME, groupName);
			clickOnElement(Locators.NAME, groupName);
			Thread.sleep(2000);
			clickOnElement(Locators.CONTENT_DESCRIPTION, GroupInfoScreen.ADD_A_MEMBER_ICON);
			clickElementInList(Locators.NAME,HIKE_DND_NAME_1);
			clickOnElement(Locators.NAME, NewGroupParticipantSelectionScreen.DONE_LBL);
			Assert.assertTrue("Failed to add member to the group", isElementPresentOnScreen(Locators.NAME, ChatEventsObject.GROUP_CHAT_NAME_ADDED_EVENT +HIKE_DND_NAME_1+ ChatEventsObject.GROUP_CHAT_ADD_PARTICIPANT_EVENT));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

