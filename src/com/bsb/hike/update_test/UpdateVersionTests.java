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
import com.bsb.hike.library.UpdateLibrary_3_3_5;
import com.bsb.hike.library.UpdateLibrary_3_5_0;
import com.bsb.hike.library.UpdateLibrary_3_5_1;
import com.bsb.hike.library.UpdateLibrary_3_6_0;
import com.bsb.hike.library.UpdateLibrary_3_6_6;
import com.bsb.hike.library.UpdateLibrary_3_7_0;
import com.bsb.hike.library.UpdateLibrary_3_7_8;
import com.bsb.hike.library.UpdateLibrary_3_8_0;
import com.bsb.hike.library.UpdateLibrary_3_8_6;
import com.bsb.hike.library.UpdateLibrary_3_8_7;
import com.bsb.hike.library.UpdateLibrary_3_8_8;
import com.bsb.hike.library.UpdateLibrary_3_8_9;
import com.bsb.hike.library.UpdateLibrary_3_9_0;
import com.bsb.hike.library.UpdateLibrary_3_9_2;
import com.bsb.hike.library.UpdateLibrary_3_9_2_57;

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
//					//BEFORE UPGRADE
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
////					//AFTER UPGRADE
					UpdateLibrary new_ver = getVersionLibrary(newAppVersion);
					new_ver.launchHike();
					new_ver.verifyDataPersistence(appVersions[index], newAppVersion,ul);
//					ul.addDndMemberToGroup();
					new_ver.ableToSendMessage("+911234555247");
					new_ver.sendSticker(newAppVersion);
					new_ver.ableToCreateGc();
					new_ver.abletoUpdateStatus();				
					Thread.sleep(5000);
					new_ver.ableToAddFavorite();
					deleteAllExistingChats();
					new_ver.deleteUserAccount();
					new_ver.uninstallApp();							
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
			ul=new UpdateLibrary_3_3_5();
		}
		else if(version.equals("3.5.0")){
			ul=new UpdateLibrary_3_5_0();
		}
		else if(version.equals("3.5.1")){
			ul=new UpdateLibrary_3_5_1();
		}
		else if(version.equals("3.6.0")){
			ul=new UpdateLibrary_3_6_0();
		}
		else if(version.equals("3.6.6")){
			ul=new UpdateLibrary_3_6_6();
		}
		else if(version.equals("3.7.0")){
			ul=new UpdateLibrary_3_7_0();
		}
		else if(version.equals("3.7.8")){
			ul=new UpdateLibrary_3_7_8();
		}
		else if(version.equals("3.8.0")){
			ul=new UpdateLibrary_3_8_0();
		}
		else if(version.equals("3.8.6")){
			ul=new UpdateLibrary_3_8_6();
		}
		else if(version.equals("3.8.7")){
			ul=new UpdateLibrary_3_8_7();
		}
		else if(version.equals("3.8.8")){
			ul=new UpdateLibrary_3_8_8();
		}
		else if(version.equals("3.8.9")){
			ul=new UpdateLibrary_3_8_9();
		}
		else if(version.equals("3.9.0")){
			ul=new UpdateLibrary_3_9_0();
		}
		else if(version.equals("3.9.2")){
			ul=new UpdateLibrary_3_9_2();
		}
		else if(version.equals(newAppVersion)){
			ul=new UpdateLibrary_3_9_2_57();
		}
		else{
			ul=new UpdateLibrary();
		}
		return ul;
	}
}
