package com.bsb.hike.update_test;

import org.junit.Assert;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.bsb.hike.library.UpdateLibrary;
import com.bsb.hike.library.UpdateLibrary_2_7_0;
import com.bsb.hike.library.UpdateLibrary_2_7_1;
import com.bsb.hike.library.UpdateLibrary_2_8_0;
import com.bsb.hike.library.UpdateLibrary_2_8_2;
import com.bsb.hike.library.UpdateLibrary_2_8_5;
import com.bsb.hike.library.UpdateLibrary_2_9_0;
import com.bsb.hike.library.UpdateLibrary_2_9_6;
import com.bsb.hike.library.UpdateLibrary_2_9_7;
import com.bsb.hike.library.UpdateLibrary_3_0_0;
import com.bsb.hike.library.UpdateLibrary_3_0_1;
import com.bsb.hike.library.UpdateLibrary_3_1_0;
import com.bsb.hike.library.UpdateLibrary_3_2_0;
import com.bsb.hike.library.UpdateLibrary_3_3_0;
import com.bsb.hike.library.UpdateLibrary_3_3_1;
import com.bsb.hike.library.UpdateLibrary_3_5_1;
import com.bsb.hike.library.UpdateLibrary_3_7_0;
import com.bsb.hike.library.UpdateLibrary_3_7_8;

public class UpdateVersionTests extends UpdateLibrary {


	UpdateLibrary ul;
		public void testVersionUpgradeCheckpoints() throws UiObjectNotFoundException, InterruptedException {
			try{
				System.out.println("DELETE USER UPGRADE TEST"+ appVersions[0]);
				for(int index=0 ; index < appVersions.length ; index++){//appVersions.length
					hikeMsgHm.clear();
					hikeMsgSm.clear();
					hikeMsGrp.clear();
					suList.clear();
					autoDownloadCheckboxStatus.clear();
					notificationCheckboxStatus.clear();
					privacyCheckboxStatus.clear();
					stickerCheckboxStatus.clear();
					blockUserCheckboxStatus.clear();
					//BEFORE UPGRADE
					ul=getVersionLibrary(appVersions[index]);
					ul.installOldVersionApp(appVersions[index]);
					ul.createNewUser(appVersions[index]);
					setSMSCredit(15);
					ul.updateProfile();
					ul.sendHikeMessage(appVersions[index]);
					ul.sendSticker(appVersions[index]);
					ul.sendHike2SmsMessage(appVersions[index]);
					ul.createGcAndSendMessage();
					ul.addMemberToGroup();
					ul.addPin();
					ul.createGCandUpdateName();		
					ul.shareContentToGroup();
					ul.shareMediaToGroup();
					ul.setStatusUpdate();
					ul.blockUser();
					ul.captureSmsCountBeforeUpgrade(appVersions[index]);
					ul.toggleNotificationCheckbox();
					ul.toggleAutoDownloadCheckbox(appVersions[index]);
					ul.togglePrivacyCheckbox();
					ul.addHikeContactAsFavorite(appVersions[index]);
//					//ul.stickerEnableDisable();
					ul.downloadStickerCategory();
					ul.putAppInBackground();
					ul.updateAppVersion();
//					//AFTER UPGRADE
					ul.launchHike();
					ul.verifyDataPersistence(appVersions[index], newAppVersion);
//					ul.addDndMemberToGroup();
					ul.ableToSendMessage("+911234555247");
					ul.sendSticker(appVersions[index]);
					ul.ableToCreateGc();
					ul.abletoUpdateStatus();				
					Thread.sleep(5000);
					ul.ableToAddFavorite();
					deleteAllExistingChats();
					ul.deleteUserAccount();
					ul.uninstallApp();							
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				Assert.fail("Unable to update the version");
			}
		}


	public void xtestVersionUpgradeCheckpoints1() throws UiObjectNotFoundException, InterruptedException {
		try{
			System.out.println("RESET ACCOUNT UPGRADE TEST"+ appVersions[0]);
			for(int index=0 ; index < appVersions.length ; index++){//appVersions.length
				hikeMsgHm.clear();
				hikeMsgSm.clear();
				hikeMsGrp.clear();
				suList.clear();
				autoDownloadCheckboxStatus.clear();
				notificationCheckboxStatus.clear();
				privacyCheckboxStatus.clear();
				stickerCheckboxStatus.clear();
				blockUserCheckboxStatus.clear();
				//BEFORE UPGRADE
				ul=getVersionLibrary(appVersions[index]);
				ul.installOldVersionApp(appVersions[index]);
				ul.createNewUser(appVersions[index]);
				setSMSCredit(15);
				ul.updateProfile();
				ul.sendHikeMessage(appVersions[index]);
				ul.sendSticker(appVersions[index]);
				ul.sendHike2SmsMessage(appVersions[index]);
				ul.createGcAndSendMessage();
				ul.addMemberToGroup();
				ul.addPin();
				ul.createGCandUpdateName();		
				ul.shareContentToGroup();
				ul.shareMediaToGroup();
				ul.setStatusUpdate();
				ul.blockUser();
				ul.captureSmsCountBeforeUpgrade(appVersions[index]);
				ul.toggleNotificationCheckbox();
				ul.toggleAutoDownloadCheckbox(appVersions[index]);
				ul.togglePrivacyCheckbox();
				ul.addHikeContactAsFavorite(appVersions[index]);
//				//ul.stickerEnableDisable();
				ul.downloadStickerCategory();
				ul.backUpAccount(appVersions[index]);
				ul.resetUserAccount();
				ul.putAppInBackground();
				ul.updateAppVersion();
				//AFTER UPGRADE
				ul.launchHike();
				ul.createNewUser(appVersions[index]);
				ul.restoreAccount(appVersions[index]);
				ul.verifyBackUpTextPersistance(appVersions[index]);
				ul.verifyEditedProfileAfterReset();
				ul.verifyHikeMessagePersistence(appVersions[index]);
				ul.verifySmsMessagePersistence(appVersions[index]);
				ul.verifyGroupChatPersistence(appVersions[index]);
				ul.verifyStatusUpdatePersistence(appVersions[index]);
				ul.verifyBlockedUserPersistance(appVersions[index]);
				ul.verifySmsCountPersistence();
				ul.verifyAppBuildNumber(newAppVersion);
				ul.verifyFavoriteListPersistence();
				ul.verifyAppVersionPersistenceFromServer();
				ul.verifyAddedMemberToGroupPersists();
				ul.verifyPinPersist(appVersions[index]);
				ul.verifyCreatedGroupAndMemberCountPersist();
				ul.verifyShareContentPersistance(appVersions[index]);
				ul.verifySharedMediaPersistance(appVersions[index]);
				//ul.addDndMemberToGroup();
				ul.ableToSendMessage("+911234555247");
				ul.sendSticker(appVersions[index]);
				ul.ableToCreateGc();
				ul.abletoUpdateStatus();				
				Thread.sleep(5000);
				ul.ableToAddFavorite();
				deleteAllExistingChats();
				ul.deleteUserAccount();
				ul.uninstallApp();							
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to update the version");
		}
	}
	public UpdateLibrary getVersionLibrary(String version){
		UpdateLibrary ul;
		if (version.equals("2.7.0")){
			ul=new UpdateLibrary_2_7_0();
		}
		else if (version.equals("2.7.1")){
			ul=new UpdateLibrary_2_7_1();
		}
		else if (version.equals("2.8.0")){
			ul=new UpdateLibrary_2_8_0();
		}
		else if (version.equals("2.8.2")){
			ul=new UpdateLibrary_2_8_2();
		}
		else if(version.equals("2.8.5")){
			ul=new UpdateLibrary_2_8_5();
		}
		else if(version.equals("2.9.0")){
			ul=new UpdateLibrary_2_9_0();
		}
		else if(version.equals("2.9.6")){
			ul=new UpdateLibrary_2_9_6();
		}
		else if(version.equals("2.9.7")){
			ul=new UpdateLibrary_2_9_7();
		}
		else if(version.equals("3.0.0")){
			ul=new UpdateLibrary_3_0_0();
		}
		else if(version.equals("3.0.1")){
			ul=new UpdateLibrary_3_0_1();
		}
		else if(version.equals("3.1.0")){
			ul=new UpdateLibrary_3_1_0();
		}
		else if(version.equals("3.2.0")){
			ul=new UpdateLibrary_3_2_0();
		}
		else if(version.equals("3.3.0")){
			ul=new UpdateLibrary_3_3_0();
		}
		else if(version.equals("3.3.1")){
			ul=new UpdateLibrary_3_3_1();
		}
		else if(version.equals("3.3.5")){
			ul=new UpdateLibrary_3_3_1();
		}
		else if(version.equals("3.5.0")){
			ul=new UpdateLibrary_3_3_1();
		}
		else if(version.equals("3.5.1")){
			ul=new UpdateLibrary_3_5_1();
		}
		else if(version.equals("3.6.0")){
			ul=new UpdateLibrary_3_5_1();
		}
		else if(version.equals("3.6.6")){
			ul=new UpdateLibrary_3_7_0();
		}
		else if(version.equals("3.7.0")){
			ul=new UpdateLibrary_3_7_0();
		}
		else if(version.equals("3.7.8")){
			ul=new UpdateLibrary_3_7_8();
		}
		else if(version.equals("3.8.0")){
			ul=new UpdateLibrary_3_7_8();
		}
		else if(version.equals("3.8.6")){
			ul=new UpdateLibrary_3_7_8();
		}
		else if(version.equals("3.8.7")){
			ul=new UpdateLibrary_3_7_8();
		}
		else if(version.equals("3.8.8")){
			ul=new UpdateLibrary_3_7_8();
		}
		else if(version.equals("3.8.9")){
			ul=new UpdateLibrary_3_7_8();
		}
		else{
			ul=new UpdateLibrary();
		}
		return ul;
	}
}
