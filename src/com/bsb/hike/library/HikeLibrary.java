package com.bsb.hike.library;



import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;

import android.graphics.Point;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.bsb.hike.base.ExecuteShell;
import com.bsb.hike.base.UiAutomatorLibrary;
import com.bsb.hike.common.AndroidClassNames;
import com.bsb.hike.common.Locators;
import com.bsb.hike.objectlocator.AccountScreen;
import com.bsb.hike.objectlocator.ChatThreadScreen;
import com.bsb.hike.objectlocator.ConversationOptionScreen;
import com.bsb.hike.objectlocator.DeleteAccountAreYouSurePopupScreen;
import com.bsb.hike.objectlocator.DeleteAccountScreen;
import com.bsb.hike.objectlocator.HomeScreen;
import com.bsb.hike.objectlocator.LoginAboutYouScreen;
import com.bsb.hike.objectlocator.LoginPhoneNumberScreen;
import com.bsb.hike.objectlocator.NewGroupParticipantSelectionScreen;
import com.bsb.hike.objectlocator.NewGroupScreen;
import com.bsb.hike.objectlocator.OverFlowListScreen;
import com.bsb.hike.objectlocator.PinScreen;
import com.bsb.hike.objectlocator.SettingsScreen;
import com.bsb.hike.objectlocator.UIAutomatorObjectLocator;
import com.bsb.hike.objectlocator.WelcomeScreen;
import com.bsb.hike.popup.screen.ConfirmYourNumberPopUpScreen;
import com.bsb.hike.popup.screen.DeleteChatPopupScreen;
import com.bsb.hike.popup.screen.LoveUsingHikePopUpScreen;
import com.bsb.hike.popup.screen.ResetAccountPopupScreen;
import com.bsb.hike.qa.apisupport.FriendsActionSupport;
import com.bsb.hike.qa.apisupport.GroupChatMessageSupport;
import com.bsb.hike.qa.apisupport.Hike2HikeMessageSupport;
import com.bsb.hike.qa.apisupport.UserModificationSupport;
import com.bsb.hike.qa.dbmanager.RedisServiceManagerUtil;
import com.bsb.hike.tooltips.HiddenChatToolTip;
import com.bsb.hike.tooltips.StartAChatToolTip;
import com.bsb.hike.tooltips.StickerTip;





/*****************************************************************************************************************************************************************
 * 
 * @author  
 *
 *	This class contains all the comman functions related to hike app
 * example : create account, delete account
 * 
 ****************************************************************************************************************************************************************/

public class HikeLibrary extends UiAutomatorLibrary{

	public static ExecuteShell executeShellCommand = new ExecuteShell();
	public static UIAutomatorObjectLocator objLocator = new UIAutomatorObjectLocator();
	public static FriendsActionSupport friendActionSupport = new FriendsActionSupport();
	public static UserModificationSupport userSupport = new UserModificationSupport();
	public static Hike2HikeMessageSupport hike2HikeMessage = new Hike2HikeMessageSupport();
	public static GroupChatMessageSupport gcSupport = new GroupChatMessageSupport();

	public static String INSTRUMENTATION_DESCRIPTION ="INSTRUMENTATION_DESCRIPTION";
	public static String PACKAGE_NAME ="com.bsb.hike";
	public static String START_ACTIVITY_NAME = ".ui.HomeActivity";
	public static String APP_PATH = "/Users/ /Documents/AndroidApk/android-client-unobfuscate.apk";


	public static String Start_Single_User_Chat="1";

	public static int DEFAULT_CHARACTER_COUNT=40;
	public static String DEFAULT_PIN = "1111";
	public static int MAX_TIMEOUT = 120000;
	public static int MIN_TIMEOUT = 15000;

	public static String DEFAULT_NAME = "HikeTestUser";
	public static String UPDATED_NAME = "HikeTestUser2";
	public static String PROFILE_EMAIL="test@test.com";
	public static String GC_PIN="TestPin";
	public static String STATUS_UPDATE ="Happy.. :-)";
	public static String Test_Group_Name="TestGroup5";
	public static String Updated_Test_Group_Name="TestGroup6";
	public static String PinHash="#pin";
	public static String DEFAULT_PINHASH="PINHASH";
	public static String TEST_CHAT_MESSAGE ="Test Message";
	public static String TEST_GROUP_CHAT_MESSAGE = "Group Chat Message";

	
	
	public static String HIKE_CONTACT_NAME ="FirstTestUser";
    public static String HIKE_NUMBER_1 = "+914444440001";
  
    public static String HIKE_CONTACT_NAME_1 ="SecondTestUser";
    public static String HIKE_NUMBER_2 = "+914444440002";
    
    public static String HIKE_CONTACT_NAME_2 ="ThirdTestUser";
    public static String HIKE_NUMBER_3 = "+914444440003";
    
    public static String HIKE_CONTACT_NAME_3 ="FourthTestUser";
    public static String HIKE_NUMBER_4 = "+914444440004";
    
    public static String HIKE_CONTACT_NAME_4 ="FifthTestUser";
    public static String HIKE_NUMBER_5 = "+914444440005";
  
    public static String INTERNATIONAL_HIKE_USER ="INTERNATIONALHIKEUSER";
    public static String INTERNATIONAL_HIKE_NUMBER = "+447903524281";

	public static String HIKE_SMS_CONTACT_NAME_1 ="HikeSMSContact";
	public static String HIKE_SMS_CONTACT_NUMBER_1 ="+911231231232";

	public static String HIKE_SMS_CONTACT_NAME_2 ="SecondHikeSMSContact";
	public static String HIKE_SMS_CONTACT_NUMBER_2 ="+911231234321";

	public static String HIKE_SMS_CONTACT_NAME_3 ="ThirdHikeSMSContact";
	public static String HIKE_SMS_CONTACT_NUMBER_3 ="+911231265473";

	public static String HIKE_SMS_CONTACT_NAME_4 ="FourthHikeSMSContact";
	public static String HIKE_SMS_CONTACT_NUMBER_4 ="+911231233487";

	public static String HIKE_DND_NAME_1 ="HikeDNDUser";
	public static String HIKE_DND_NUMBER_1 ="+919818461120";

	HashMap<String, String> Contacts = new HashMap<String, String>();

	public static String StickerTag = "yo,boss";
	public static String DEF_DIGIT ="711777";
	public static String DEFAULT_MSISDN ; //RandomStringUtils.randomNumeric(4);


	//	public static void main(String args[]){
	//		getUser();
	//	}
	/*****************************************************************************************************************************************************************
	 * 
	 * @return MSISDN of the current User
	 * @author  
	 * 
	 ****************************************************************************************************************************************************************/
	public static String getUser(){
		return System.getProperty("user.dir");

	}
	public static String getDEFAULT_MSISDN_Create(){
		setDEFAULT_MSISDN();
		return DEFAULT_MSISDN;

	}
	
	public void set_MSISDN( String num) throws UiObjectNotFoundException, InterruptedException
	{ 
		String prop = "sdk.dir=/Users/yashpreet/Documents/adt-bundle-mac-x86_64-20140702/sdk \n" +
				"msisdn="+num;

		FileWriter	output=null;
		try {

			output = new FileWriter("/data/local/tmp/local.properties");
			output.write(prop);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	public void convertStringToKeyCode(String text) {

		KeyCharacterMap mKeyCharacterMap = KeyCharacterMap
				.load(KeyCharacterMap.SPECIAL_FUNCTION);
		int keycode = 0;

		KeyEvent[] events = mKeyCharacterMap.getEvents(text.toCharArray());
		for (KeyEvent event2 : events) {
			if (event2.getAction() == 0) {
				keycode = event2.getKeyCode();
				System.out.println(keycode + " ");
				if (keycode >= 0 && keycode <= 16) {
					UiDevice.getInstance().pressKeyCode(keycode);
				} else if (keycode >= 29 && keycode <= 54) {
					UiDevice.getInstance().pressKeyCode(keycode);
				} else {
					UiDevice.getInstance().pressKeyCode(keycode, 1);
				}
			}
		}
	}
	public static String getDEFAULT_MSISDN() {
		setDEFAULT_MSISDN();
		return "+91"+DEFAULT_MSISDN;
	}
	public static void setDEFAULT_MSISDN(){
		try {
			DEFAULT_MSISDN=getMsisdn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static  String getMsisdn() throws IOException {

		Properties prop = new Properties();

		String propFileName = "/data/local/tmp/local.properties";
		FileReader reader = new FileReader(propFileName);
		prop.load(reader);

		String msisdn = prop.getProperty("msisdn");
		return msisdn;
	}
	/***************************************************************************************************************************************************************
	 * 
	 * 	@return void
	 * @author  
	 * 
	 ***************************************************************************************************************************************************************/
	public static void setPin(){
		System.out.println("Setting Pin");
		RedisServiceManagerUtil.getInstance().setKey("pincodes-"+getDEFAULT_MSISDN(), DEFAULT_PIN);		
	}

	public static String getPin(){
		String pin="";
		pin = RedisServiceManagerUtil.getInstance().getKey("pincodes-"+getDEFAULT_MSISDN());
		return pin;	
	}

	/****************************************************************************************************************************************************************
	 * 
	 * @return void
	 * @author  
	 * 
	 ****************************************************************************************************************************************************************/

	public void launchHike(){
		//testing
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"1.Launch hike");
		System.out.println("Launching Hike55555555");
		String runComponent = PACKAGE_NAME+'/'+START_ACTIVITY_NAME;
		executeShellCommand.ExecuteShellCommand("am","start","-n",runComponent);
		try{
			setPotrait();
			System.out.println("Looking for Love Using Hike PopUp");

			clickOnElementContinueOnFail(Locators.NAME, LoveUsingHikePopUpScreen.NO_THANKS_BTN);


			Thread.sleep(3000);
			clickOnElementContinueOnFail(Locators.NAME, objLocator.Unified_Inbox_Cancel);

			System.out.println("Looking for Awsome Button");

			if(isElementPresentOnScreen(Locators.NAME , "Quick setup")){
				clickOnElementContinueOnFail(Locators.NAME, "Quick setup");
			}

			if(isElementPresentOnScreen(Locators.NAME , objLocator.Awesome_BTN)){
				clickOnElementContinueOnFail(Locators.NAME, objLocator.Awesome_BTN);
			}

		}catch(Exception e){
			System.out.println("Popup does not appear");
		}


	}

	/****************************************************************************************************************************************************************
	 * 	
	 *@return void 
	 *@author  
	 *
	 ***************************************************************************************************************************************************************/

	public void launchHikeWithoutWaitingForPopup(){
		System.out.println("Launching Hike without waiting for popup");
		String runComponent = PACKAGE_NAME+'/'+START_ACTIVITY_NAME;

		executeShellCommand.ExecuteShellCommand("am","start","-n",runComponent);
	}

	/****************************************************************************************************************************************************************
	 * 
	 * @return void
	 * @author  
	 * 
	 ***************************************************************************************************************************************************************/
	public void exitHike(){
		//	getUiDevice().pressHome();
		System.out.println("Exiting hike");
		executeShellCommand.ExecuteShellCommand("am","force-stop" ,"com.bsb.hike");
	}



	/**************************************************************************************************************************************************************** * 
	 * @return void
	 * @exception UiObjectNotFoundException
	 * @author  
	 * 
	 ***************************************************************************************************************************************************************/	
	public void goToHome(){

		try {
			int counter =0;

			System.out.println("GOING BACK TO HOME SCREEN.. hikelib");
			//			UiObject view = getElement(Locators.CLASSNAME, AndroidClassNames.VIEW, 0);
			//			UiObject Llayout = getChild(view, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 1);
			//			UiObject menuOption= getChild(Llayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 2);

			UiObject startChat = getElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
			while(!startChat.exists() && counter <5){	
				System.out.println(counter);
				UiDevice.getInstance().pressBack();
				startChat= getElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
				counter++;	
//				UiObject nux = getElement(Locators.NAME, "Express more with stickers and chat with your best friends!",5000);
//				if(isElementPresentOnScreen(nux)){
//					clickOnElement(Locators.NAME, "SKIP");
//					Thread.sleep(2000);
//				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	/****************************************************************************************************************************************************************
	 * 
	 * Installs Hike App On the devices
	 * 
	 * @return void
	 * @author  
	 * 
	 ***************************************************************************************************************************************************************/
	public void installHike()
	{
		executeShellCommand.ExecuteShellCommand("adb","install",APP_PATH);
	}

	/****************************************************************************************************************************************************************
	 * 
	 * UnInstalls Hike App from the devices
	 * 
	 * @return void
	 * @author  
	 * 
	 ***************************************************************************************************************************************************************/	
	public void uninstallHike(){

		executeShellCommand.ExecuteShellCommand("adb","uninstall",PACKAGE_NAME);
	}


	/****************************************************************************************************************************************************************
	 * 
	 *																MESSAGING RELATED FUNCTIONS
	 * @throws InterruptedException 
	 * @throws UiObjectNotFoundException 
	 *
	 ***************************************************************************************************************************************************************/

	public void startSingleChatAndSendMessage(String name , String message) throws UiObjectNotFoundException, InterruptedException {
		goToHome();	
		startChat(name);
		sendMessage(message);	
	}

	public void startChat(String name) throws UiObjectNotFoundException, InterruptedException{
		goToHome();
		System.out.println("Starting Chat with "+name);
		clickOnElement(Locators.CONTENT_DESCRIPTION , HomeScreen.START_A_NEW_CHAT_LBL);
		clickElementInList(Locators.NAME , name);

	}

	public void sendMessage(String message) throws UiObjectNotFoundException{
		enterText(message);
		sendMessage();
	}

	public void sendMessage() throws UiObjectNotFoundException{
		System.out.println("Sending Message shfdjfhjhf");
		int sendButtonIndex=2;
		UiObject FrameLayout = getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT, 0);
		UiObject RelativeLayout = getChild(FrameLayout,Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
		UiObject Llayout = getChild(RelativeLayout,Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
		UiObject Rlayout = getChild(Llayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 2);
		UiObject R0layout= getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
		try{
			System.out.println(R0layout.getBounds().centerX()+"yyyyy"+R0layout.getBounds().centerY());
			System.out.println(R0layout.getChildCount());
			if(R0layout.getChildCount() >= 4){
				sendButtonIndex++;
			}

		}catch(UiObjectNotFoundException e){
			System.out.println("Layout does not have 4 child");
		}

		UiObject sendButton = getChild(R0layout, Locators.CLASSNAME, AndroidClassNames.IMAGE_BUTTON, sendButtonIndex);
		clickOnElement(sendButton);
	}

	/***************************************************************************************************************************************************************
	 * 
	 * @return void
	 * @throws UiObjectNotFoundException
	 *
	 **************************************************************************************************************************************************************/
	public void goBack() throws UiObjectNotFoundException{
		UiObject view = getElement(Locators.CLASSNAME, AndroidClassNames.VIEW,0);
		UiObject Rlayout = getChild(view, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,0);
		UiObject Llayout  = getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,0);
		UiObject backButton = getChild(Llayout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW,0);
		System.out.println(backButton.getBounds().centerX()+"backkkk"+backButton.getBounds().centerY());
		System.out.println("Clicking on back button");
		clickOnElement(backButton);
	}

	public void goBackUsingDeviceBackKey() throws UiObjectNotFoundException{
		System.out.println("Clicking on device back button");
		getUiDevice().pressBack();
	}


	/***************************************************************************************************************************************************************
	 * 	
	 * @throws UiObjectNotFoundException
	 * 
	 **************************************************************************************************************************************************************/
	public void ClickXButton() throws UiObjectNotFoundException{
		UiObject view = getElement(Locators.CLASSNAME, AndroidClassNames.VIEW, 0);
		UiObject Rlayout = getChild(view, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
		UiObject Llayout  = getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,0);
		UiObject XButton = getChild(Llayout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW);
		clickOnElement(XButton);
	}

	/***************************************************************************************************************************************************************
	 * 	
	 * @return void
	 * @author  
	 * @throws UiObjectNotFoundException 
	 * 
	 **************************************************************************************************************************************************************/
	public void deleteAllExistingChats() throws UiObjectNotFoundException {
		System.out.println("INSTRUMENTATION DESCRIPTION:"+"\n"
				+"Delete all created chat");
		
		System.out.println("deleting all conversations");
		goToHome();
		if(!(isElementPresentOnScreen(Locators.NAME, HomeScreen.TAP_NEW_MESSAGE_ICON_LBL))){
			closeStartAChatTip();
			closeHiddenToolTip();
			try {
				UiObject listview = getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW,0);
				while(!(isElementPresentOnScreen(Locators.NAME, HomeScreen.TAP_NEW_MESSAGE_ICON_LBL))){
					UiObject rLayout = getChild(listview, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
					tapAndHoldElement(rLayout);
					if(clickOnElementContinueOnFail(Locators.NAME, ConversationOptionScreen.DELETE_CHAT_LBL))
						clickOnElementContinueOnFail(Locators.NAME, DeleteChatPopupScreen.YES_BTN);
					else if(clickOnElementContinueOnFail(Locators.NAME, ConversationOptionScreen.DELETE_AND_EXIT_GROUP_LBL))
						clickOnElementContinueOnFail(Locators.NAME, DeleteChatPopupScreen.OK_BTN);
				}

			} catch (UiObjectNotFoundException e) {
				System.out.println("No Chat To Delete");
			}
		}

	}


	public void deleteUserChat(String name) throws InterruptedException{
		try{
			System.out.println("Deleting Chat with "+name);
			tapAndHoldElement(Locators.NAME , name);
			Thread.sleep(MIN_TIMEOUT);
			clickOnElementContinueOnFail(Locators.NAME, ConversationOptionScreen.DELETE_CHAT_LBL);
			clickOnElement(Locators.NAME ,DeleteChatPopupScreen.YES_BTN);
		}
		catch(Exception e){
			System.out.println("Old chat does not exists ");
		}
	}

	public void deleteGroupChat(String name) throws InterruptedException{
		try{
			System.out.println("Deleting group chat "+name);
			goToHome();
			tapAndHoldElement(Locators.NAME , name);
			clickOnElementContinueOnFail(Locators.NAME, ConversationOptionScreen.DELETE_AND_EXIT_GROUP_LBL);
			clickOnElementContinueOnFail(Locators.NAME, DeleteChatPopupScreen.OK_BTN);
		}
		catch(Exception e){
			System.out.println("Old chat does not exists starting ");
		}
	}

	/***************************************************************************************************************************************************************
	 * opens chat with a particular name 
	 * 
	 * @param name
	 **************************************************************************************************************************************************************/
	public void openChat(String name){
		try{
			System.out.println("Opening chat with "+name);
			goToHome();
			clickElementInList(Locators.NAME , name);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/***************************************************************************************************************************************************************
	 * SMS Related Function fetches/sets SMS countfrom backend
	 * 
	 * @param msisdn
	 * @return
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 * 
	 **************************************************************************************************************************************************************/
	public int getSMSCredit(String msisdn) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("Getting SMS Credits");
		Thread.sleep(10000);
		int smsCredits = friendActionSupport.getRemainingSMSCredits(msisdn);
		System.out.println(smsCredits);
		return smsCredits;
	}

	public void setSMSCredit(int smsCountToSet) {
		System.out.println("Setting SMS Credit to "+smsCountToSet);
		String smsCount=""+smsCountToSet;
		friendActionSupport.setSmsCount(getDEFAULT_MSISDN(), smsCount);
	}

	/***************************************************************************************************************************************************************
	 * 
	 *															GROUP CHAT RELATED FUNCTIONS
	 *
	 **************************************************************************************************************************************************************/
	/***************************************************************************************************************************************************************
	 * 
	 * @param groupName
	 * @param numberOfParticipant
	 * @param message
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 * @author  
	 * 
	 **************************************************************************************************************************************************************/
	public void createGroupChatAndSendMessage(String groupName , int numberOfParticipant , String message) throws UiObjectNotFoundException, InterruptedException{
		createGroupChat( groupName ,  numberOfParticipant );
		clickOnElement(Locators.NAME , groupName);
		//		clickOnElementContinueOnFail(Locators.NAME , groupName);
		sendMessage(message);
	}

	/***************************************************************************************************************************************************************
	 * 
	 * @param groupName
	 * @param numberOfParticipant
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 * @author  
	 **************************************************************************************************************************************************************/
	public void createGroupChat(String groupName , int numberOfParticipant ) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("Creating group chat '"+groupName+"' having "+numberOfParticipant+" participants");
		goToHome();
		UiObject view = getElement(Locators.CLASSNAME, AndroidClassNames.VIEW, 0);
		UiObject LLayout = getChild(view , Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT,1);
		UiObject overFlowMenu = getChild(LLayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 2);
		overFlowMenu.click();
		clickElementInList(Locators.NAME, OverFlowListScreen.NEW_GROUP_LBL);
		enterText(Locators.NAME , NewGroupScreen.NAME_THE_GROUP_TXT, groupName);
		//goBackUsingDeviceBackKey();
		clickOnElement(Locators.NAME, NewGroupScreen.NEXT_LBL);
		UiObject FLayout = getElement(Locators.CLASSNAME, AndroidClassNames.FRAME_LAYOUT,1);
		UiObject LinearLayout  = getChild(FLayout, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
		UiObject listOfContacts= getChild(LinearLayout,Locators.CLASSNAME, AndroidClassNames.LIST_VIEW , 1);
		for(int counter=1; counter<numberOfParticipant ; counter++){
			//			System.out.println("COUNTER           "+counter);
			UiObject participant = getChild(listOfContacts, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, counter);
			UiObject participantCheckbox = getChild(participant, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,2);
			//			Thread.sleep(2000);
			clickOnElement(participantCheckbox);
		}
		clickOnElement(Locators.NAME, NewGroupParticipantSelectionScreen.DONE_LBL);
		goToHome();
		Thread.sleep(1000);

	}

	/***************************************************************************************************************************************************************
	 * 
	 * @param groupName
	 * @param contacts
	 * 
	 **************************************************************************************************************************************************************/
	public void CreateGroupChat(String groupName , HashMap<Integer, String> contacts){

		try{
			System.out.println("Creating Group chat "+groupName);
			UiObject view = getElement(Locators.CLASSNAME, AndroidClassNames.VIEW, 0);
			UiObject LLayout = getChild(view, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 1);
			UiObject overFlowMenu = getChild(LLayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 2);

			overFlowMenu.click();
			clickElementInList(Locators.NAME, OverFlowListScreen.NEW_GROUP_LBL);
			enterText(Locators.NAME , NewGroupScreen.NAME_THE_GROUP_TXT , groupName);
			clickOnElement(Locators.NAME,NewGroupScreen.NEXT_LBL);
			for(int counter =1 ; counter <= contacts.size() ; counter++){
				clickElementInList(Locators.NAME , contacts.get(counter));
			}

			clickOnElement(Locators.NAME, NewGroupParticipantSelectionScreen.DONE_LBL);
		}catch(Exception e){
			System.out.println("Not able to create group chat");
		}
	}

	/***************************************************************************************************************************************************************
	 * 
	 * @throws UiObjectNotFoundException
	 * @throws InterruptedException
	 *
	 **************************************************************************************************************************************************************/
	public void closeCheckOutThemePopUp() throws UiObjectNotFoundException, InterruptedException{
		System.out.println("Closing Theme popup");
		clickOnElement(Locators.CONTENT_DESCRIPTION,ChatThreadScreen.OVERFLOW_ICON);
		clickOnElement(Locators.NAME, OverFlowListScreen.CHAT_THEME_LBL);
		getUiDevice().pressBack();

	}

	/***************************************************************************************************************************************************************
	 * 
	 * @return void
	 * 
	 **************************************************************************************************************************************************************/
	public void openOverflowMenu(){
		System.out.println("opening overflow menu");
		try {
			clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
		} catch (UiObjectNotFoundException e) {
			System.out.println("Element not found");
			Assert.fail();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Element not found");
			Assert.fail();
		}
	}




	/***************************************************************************************************************************************************************
	 * 
	 * Completely deletes user from Hike
	 * 
	 * @param msisdn
	 *
	 **************************************************************************************************************************************************************/
	public void deletingUserFromHike(String msisdn){
		System.out.println("Deleting user from hike through server");
		userSupport.deleteUser(msisdn);
		friendActionSupport.clearCacheAfterDelete(msisdn);
	}


	public void recieveHikeMessageFrom(String sender , String message){
		System.out.println("Receiving hike message");
		hike2HikeMessage.sendHikeMessage(sender, getDEFAULT_MSISDN(),message );
	}

	public String createGroupAndSendMessage(String owner , List<String> contact){
		System.out.println("Creating group and sending messange through server");
		return gcSupport.createGroupAndSendMessage(owner, contact);
	}

	public void addMemberToGroupChat(String personAdding , String personToAdd){
		System.out.println("Adding member to group through server: "+personAdding+" adding "+personToAdd);
		gcSupport.addMemberToGroup(personAdding, personToAdd);
	}

	public void removeMemberFromGroup(String removingMember , String memberToRemove){
		System.out.println("Removing member from group through server: "+removingMember+" adding "+memberToRemove);
		gcSupport.removeMemberFromGroup(removingMember, memberToRemove);
	}

	public void changeGroupNameByMember(String member , String changesGroupName){
		System.out.println("Changing Group name by member (server)");
		gcSupport.changeGroupNameByMember(member, changesGroupName);	
	}

	public boolean createHikeUserWithMsisdn(String msisdn){
		System.out.println("Creating hike user having msisdn "+msisdn);
		return userSupport.createHikeUserWithMsisdn(HIKE_SMS_CONTACT_NUMBER_2);
	}

	public void sendHikeMessage(String sender, String reciever, String message){
		System.out.println("Sending hike message from "+sender+" to "+reciever);
		hike2HikeMessage.sendHikeMessage(sender, reciever, message);
	}

	public void sendPinByOtherUser(String groupID , String msisdn , String pinText){
		gcSupport.setPin(groupID, msisdn , pinText);
	}

	public void startSingleChatAndSendMessageToUnsavedNumber(String msisdn , String message) throws UiObjectNotFoundException, InterruptedException{
		System.out.println("Sending Message '"+message+" to unsaved no. "+msisdn);
		goToHome();
		clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.START_A_NEW_CHAT_LBL);
		enterText(msisdn);
		UiObject contact=getElement(Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 1);
		clickOnElement(contact);
		sendMessage(message);


	}

	/***************************************************************************************************************************************************************
	 * 
	 * 
	 **************************************************************************************************************************************************************/
	public void setHiddenChatPassword(){
		System.out.println("Setting Hidden chat password");
		Point[] cordinates = new Point[4];
		cordinates[1] = new Point(115,531);
		cordinates[0] = new Point(115,283);

		cordinates[2] = new Point(362,532);
		cordinates[3] = new Point(362,283);
		swipe(cordinates);
	}

	public void getHiddenChatPassword(boolean reset){
		System.out.println("Getting Hidden chat password");
		if(reset==true){

		}
		else{
			Point[] cordinates = new Point[4];
			cordinates[1] = new Point(113,578);
			cordinates[0] = new Point(113,328);

			cordinates[2] = new Point(362,576);
			cordinates[3] = new Point(362,328);
			swipe(cordinates);
		}
	}


	public void deleteUser(){
		System.out.println("Deleting User");
		setDEFAULT_MSISDN();
		try{
			if(isElementPresentOnScreen(Locators.NAME,WelcomeScreen.GET_STARTED_BTN)== true){
				System.out.println("user already deleted");
			}
			else{
				goToHome();
				clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME,SettingsScreen.ACCOUNT_LBL);
				clickOnElement(Locators.NAME,AccountScreen.DELETE_ACCOUNT_LBL);

				enterText(Locators.NAME, DeleteAccountScreen.PHONE_NUMBER_TXT, getDEFAULT_MSISDN_Create());
				//goBackUsingDeviceBackKey();

				if(!isElementPresentOnScreen(Locators.NAME, DeleteAccountScreen.DELETE_MY_ACCOUNT_BTN)){
					UiObject initialPos = getElement(Locators.NAME, DeleteAccountScreen.TO_DELETE_YOUR_ACCOUNT_LBL);
					UiObject finalPos = getElement(Locators.NAME, DeleteAccountScreen.DELETE_ACCCOUNT_TITLE_LBL);

					UiDevice.getInstance().swipe(initialPos.getBounds().centerX(), initialPos.getBounds().centerY(), 
							finalPos.getBounds().centerX(), finalPos.getBounds().centerY(), 5);
				}
				clickOnElement(Locators.NAME,DeleteAccountScreen.DELETE_MY_ACCOUNT_BTN);
				Thread.sleep(MIN_TIMEOUT);

				clickOnElement(Locators.NAME,DeleteAccountAreYouSurePopupScreen.CONFIRM_BTN);

				waitForElement(Locators.NAME, WelcomeScreen.GET_STARTED_BTN, MAX_TIMEOUT);

				if(isElementPresentOnScreen(Locators.NAME,WelcomeScreen.GET_STARTED_BTN)== true){
					System.out.println("user sucessfully deleted");
				}
				else{
					System.out.println("Not able to delete user");
				}
			}

		}catch(Exception e){

			System.out.println("User is not able to delete account");
		}

	}

	public void resetUser(){
		System.out.println("Resetting User Account");
		setDEFAULT_MSISDN();
		try{
			if(isElementPresentOnScreen(Locators.NAME,WelcomeScreen.GET_STARTED_BTN)== true){
				System.out.println("user already has reset his account");
			}
			else{
				goToHome();
				clickOnElement(Locators.CONTENT_DESCRIPTION, HomeScreen.OVERFLOW_ICON);
				clickOnElement(Locators.NAME,OverFlowListScreen.SETTINGS_LBL);
				clickOnElement(Locators.NAME,SettingsScreen.ACCOUNT_LBL);
				clickOnElement(Locators.NAME,AccountScreen.RESET_ACCOUNT_LBL);			 
				waitForElement(Locators.NAME, ResetAccountPopupScreen.ARE_YOUR_SURE_LBL, MIN_TIMEOUT);

				UiObject resetbtn = getElement(Locators.CLASSNAME, "android.widget.Button", 2);
				clickOnElement(resetbtn);
				//goBackUsingDeviceBackKey();
				Thread.sleep(MIN_TIMEOUT);		 				 
				waitForElement(Locators.NAME, WelcomeScreen.GET_STARTED_BTN, MAX_TIMEOUT);	 
				if(isElementPresentOnScreen(Locators.NAME,WelcomeScreen.GET_STARTED_BTN)== true){
					System.out.println("Account sucessfully got reset");
				}
				else{
					System.out.println("Not able to reset user");
				}
			}			
		}catch(Exception e){		
			System.out.println("User is not able to reset account");
		}	 
	}



	public void createUser() throws InterruptedException, UiObjectNotFoundException{
		System.out.println("Signing up");
		setDEFAULT_MSISDN();
		setPin();
		clickOnElement(Locators.NAME, WelcomeScreen.HIKE_MESSENGER_LOGO_ICON);
		clickOnElement(Locators.NAME, WelcomeScreen.GET_STARTED_BTN);

		waitForElement(Locators.NAME,LoginPhoneNumberScreen.PHONE_NUMBER_LBL, 10);

		enterText(Locators.NAME, LoginPhoneNumberScreen.PHONE_NUMBER_TXT, getDEFAULT_MSISDN_Create());
		clickOnElement(Locators.NAME, LoginPhoneNumberScreen.NEXT_BTN);

		clickOnElement(Locators.NAME, ConfirmYourNumberPopUpScreen.CONFIRM_BTN);



		waitForElement(Locators.NAME,PinScreen.PIN_LBL,MAX_TIMEOUT);

		enterText(Locators.NAME , PinScreen.PIN_TXT , DEFAULT_PIN);

		clickOnElement(Locators.NAME , PinScreen.NEXT_BTN);

		enterText(Locators.NAME , LoginAboutYouScreen.YOUR_NAME_TXT ,DEFAULT_NAME);

		clickOnElement(Locators.NAME , LoginAboutYouScreen.NEXT_BTN);

		setSMSCredit(100);
		Assert.assertTrue("Creation Of Account Failed", isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION , HomeScreen.START_A_NEW_CHAT_LBL));
	}


	public void closeStartAChatTip() throws UiObjectNotFoundException{
		System.out.println("Closing start a chat tip");
		if(isElementPresentOnScreen(Locators.NAME, StartAChatToolTip.START_A_CHAT_LBL)){
			UiObject Rlayout = getElement(Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT,0);
			UiObject Lview = getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.LIST_VIEW, 0);
			UiObject RLayout2 = getChild(Lview, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
			UiObject RLayout1 = getChild(Rlayout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 1);
			UiObject crossButton = getChild(RLayout1, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 2);

			clickOnElement(crossButton);
		}
		else{
			System.out.println("LOG: START A CHAT TOOLTIP IS NOT PRESENT CONTINUING");
		}
	}
	public void closeHiddenToolTip() throws UiObjectNotFoundException{
		System.out.println("Closing Hidden tool tip");
		if(isElementPresentOnScreen(Locators.NAME, HiddenChatToolTip.HIDDEN_MODE_LBL)){
			UiObject Lview = getElement(Locators.CLASSNAME, AndroidClassNames.LIST_VIEW, 0);
			UiObject LLayout2 = getChild(Lview, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 0);
			UiObject RLayout1 = getChild(LLayout2, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
			UiObject LLayout = getChild(RLayout1, Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 2);
			UiObject crossButton = getChild(LLayout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 1);

			clickOnElement(crossButton);
		}
		else{
			System.out.println("LOG: HIDDEN TOOLTIP IS NOT PRESENT CONTINUING");
		}
	}
	public void closeStickerTip() throws UiObjectNotFoundException {
		System.out.println("Closing Sticker tip");
		if(isElementPresentOnScreen(Locators.NAME, StickerTip.STICKERS_LBL)){
			UiObject L_Layout=getElement(Locators.CLASSNAME, AndroidClassNames.LINEAR_LAYOUT, 5);
			UiObject R_Layout=getChild(L_Layout, Locators.CLASSNAME, AndroidClassNames.RELATIVE_LAYOUT, 0);
			UiObject cross=getChild(R_Layout, Locators.CLASSNAME, AndroidClassNames.IMAGE_VIEW, 1);
			clickOnElement(cross);
		}
	}
	public void forceCloseHike(){
		System.out.println("Force stop");
		executeShellCommand.ExecuteShellCommand("am", "force-stop" , PACKAGE_NAME);
	}


	public void sendMessageNewVersion() throws UiObjectNotFoundException{
		int sendButtonIndex=2;
		UiObject Llayout = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(0));
		System.out.println(Llayout.getChildCount()+"CCCCCCCCCCCCCCCC");
		UiObject Rlayout = Llayout.getChild(new UiSelector().className("android.widget.RelativeLayout").index(2));
		System.out.println(Rlayout.getChildCount()+"CCCCCCCCCCCCCCCC");
		UiObject R0layout= Rlayout.getChild(new UiSelector().className("android.widget.RelativeLayout").index(0));
		if(R0layout.getChildCount() >= 4){
			sendButtonIndex++;
		}
		UiObject sendButton = R0layout.getChild(new UiSelector().className("android.widget.ImageButton").index(sendButtonIndex));
		sendButton.click();
	}
	
	
}
