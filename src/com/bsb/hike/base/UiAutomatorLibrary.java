package com.bsb.hike.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.bsb.hike.common.AndroidClassNames;
import com.bsb.hike.common.Locators;


/****************************************************************************************************************************************************************
 * 
 * @author vivek
 *
 ***************************************************************************************************************************************************************/

public class UiAutomatorLibrary extends UiAutomatorTestCase{

	
	
/*****************************************************************************************************************************************************************
 * 	
 * Check for Element in the listView with assertion fails the test case if object is not found
 * 
 * @param locator
 * @param value
 * @return void
 *
 * 
 ****************************************************************************************************************************************************************/
	
	public void searchElementInList(Locators locator,String value){
		System.out.println("Searching element in list :"+value +" by "+locator);
		UiScrollable scrollable=null;
		
		try {
			switch(locator){
			case NAME:
				scrollable = new UiScrollable(new UiSelector().className(AndroidClassNames.LIST_VIEW));
				scrollable.getChildByText(new UiSelector().className(AndroidClassNames.TEXT_VIEW), value);
				Assert.assertTrue("Succesfully found element "+value, isElementPresentOnScreen(Locators.NAME , value));
				break;
			case CONTENT_DESCRIPTION:
				scrollable = new UiScrollable(new UiSelector().description(AndroidClassNames.LIST_VIEW));
				scrollable.getChildByDescription(new UiSelector().className(AndroidClassNames.TEXT_VIEW), value);
				Assert.assertTrue("Succesfully found element "+value, isElementPresentOnScreen(Locators.CONTENT_DESCRIPTION , value));
				break;
			default:
				break;
			}
			
		} catch (Exception e) {
			Assert.fail("Element not present in the list");
		}
		
	}
	
	
/*****************************************************************************************************************************************************************
 * 	
 * Check for Element in the listView
 * 
 * @param locator
 * @param value
 * @return false : if element is not present
 * @return true : if element is present
 * @author vivek
 * 
 ****************************************************************************************************************************************************************/
	
	public boolean searchElementInListWithouTAsserting(Locators locator,String value){
		System.out.println("Searching element in list without asserting :"+value +" by "+locator);
		UiScrollable scrollable = null;
		boolean isElementPresent = false;
		try {
			switch(locator){
			case NAME:
				scrollable = new UiScrollable(new UiSelector().className("android.widget.ListView"));
				scrollable.getChildByText(new UiSelector().className("android.widget.TextView"), value);
				isElementPresent=true;
				break;
			case CONTENT_DESCRIPTION:
				scrollable = new UiScrollable(new UiSelector().className("android.widget.ListView"));
				scrollable.getChildByDescription(new UiSelector().className("android.widget.TextView"), value);
				isElementPresent=true;
				break;

			default:
				break;
			}
			
		} catch (Exception e) {
			System.out.println("LOG : Not Able To Find Element");
			e.getStackTrace();
			isElementPresent=false;
		}
		return isElementPresent;
	}

/****************************************************************************************************************************************************************
 * 
 * 
 * @param object (UiObject)
 * @return true : if element is present
 * @return false : if element is not present
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/
	
	
	public boolean isElementPresentOnScreen(UiObject object){
		System.out.println("Checking if element(UiObject) is present on screen");
		boolean isElementPresent = false;
		try{
			
			for (int i=0;i<5;i++){
				if(object.exists()){
					isElementPresent= true;	
					break;
				}
				else{
					Thread.sleep(1000);
					continue;
				}
			}
			if(!isElementPresent){
				System.out.println("Element "+object.getText()+"is not present in the list "+returnScreenshot());
			}
			
			return isElementPresent;
		}catch(Exception e){
			return isElementPresent;
		}
		
	}

/****************************************************************************************************************************************************************
 * 
 * @param locator
 * @param value
 * @return true : if element is present
 * @return false : if element is not present
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/
	public boolean isElementPresentOnScreen(Locators locator , String value){
		System.out.println("Checking if element is present on screen ,locator: "+locator+" value: "+value);
		boolean isElementPresent = false;
		UiObject object = null;
		try{
			switch (locator) {
			case NAME:
				object = new UiObject(new UiSelector().text(value));
				isElementPresent = isElementPresentOnScreen(object);
				break;
				
			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				isElementPresent = isElementPresentOnScreen(object);
				break;

			default:
				break;
			}
		}catch(Exception e){
			System.out.println("Element with property "+ locator +" with value "+ value+ " is not present in the screen");
		}
		return isElementPresent;
	}

/****************************************************************************************************************************************************************
 * 
 * @param locator
 * @param value
 * @return void
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/
	
	public void clickOnElement(Locators locator , String value ) throws UiObjectNotFoundException, InterruptedException {
		System.out.println("Clicking on element by "+locator+" value: "+value);
		UiObject object= null;
		switch(locator){
		
		case NAME:
			
			object = new UiObject(new UiSelector().text(value));
			
				if(isElementPresentOnScreen(object)){
					object.click();
					System.out.println("Succesfully Clicked on Element "+ value + " By Name");
					break;
				}
				else{
					Assert.fail("Not able to click on element "+ value + " By Name " + returnScreenshot());
					break;
				}
			
		
		case CONTENT_DESCRIPTION:
			object = new UiObject(new UiSelector().description(value));
			
			
				if(isElementPresentOnScreen(object)){
					object.click();
					System.out.println("Succesfully Clicked on Element "+ value + " By CONTENT_DESCRIPTION");
					break;
				}
				else{
					Assert.fail("Not able to click on element "+ value + " By CONTENT_DESCRIPTION");
					break;
				}
		
		
		case CLASSNAME:
			object = new UiObject(new UiSelector().className(value));
			
			
				if(isElementPresentOnScreen(object)){
					object.click();
					System.out.println("Succesfully Clicked on Element "+ value + " By CLASSNAME");
					break;
				}
				else{
					Assert.fail("Not able to click on element "+ value + " By CLASSNAME");
					break;
				}
		
		default:
			break;
			
		}
		
	}

/****************************************************************************************************************************************************************
 * 
 * @param locator
 * @param value
 * @param index
 * @author vivek
 * 
 ****************************************************************************************************************************************************************/
	public void clickOnElement(Locators locator , String value , int index)  {
		System.out.println("Clicking on element by "+locator+" value: "+value+" index:"+index);
		UiObject object = null;
		try {
			object = new UiObject(new UiSelector().className(value).index(index));
			if(isElementPresentOnScreen(object)){
				object.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Not able to click on element "+ value + " By CLASSNAME");
		}
	}
	
	public void clickOnElement(UiObject object) throws UiObjectNotFoundException  {
		System.out.println("Clicking element "+object);
		object.click();
	}
	
/****************************************************************************************************************************************************************
 * 
 * @param locator
 * @param value
 * @return true : if element is present
 * @return false : if element is not present
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/
	public Boolean clickOnElementContinueOnFail(Locators locator , String value ) {
		System.out.println("Clicking on element by "+locator+" value: "+value+" and continue on fail");
		UiObject object= null;
		switch(locator){
		
		case NAME:
			object = new UiObject(new UiSelector().text(value));
		try{
				if(isElementPresentOnScreen(object)){
					object.click();
					System.out.println("Succesfully Clicked on Element "+ value + " By Name");
					return true;

				}
				else{
					System.out.println("Not able to click on Element "+ value + " By Name");
					return false;
				}
		}catch(Exception e){
			System.out.println("Not able to click on Element "+ value + " By Name");
		}
		
		case CONTENT_DESCRIPTION:
			object = new UiObject(new UiSelector().description(value));
			try{
				if(isElementPresentOnScreen(object)){
					object.click();
					System.out.println("Succesfully Clicked on Element "+ value + " By CONTENT_DESCRIPTION");
					return true;
				}
				else{
					System.out.println("Not able to click on element "+ value + " By CONTENT_DESCRIPTION");
					return false;
				}
			}
			catch(Exception e){
				System.out.println("Not able to click on Element "+ value + " By CONTENT_DESCRIPTION");
			}
		default:
			return false;
		}
	}
	
/****************************************************************************************************************************************************************
 * 
 * @param locator
 * @param value
 * @param timeout
 * @return boolean true :if element appears
 * 				   false  :if element does not appears
 * @throws InterruptedException
 * @author vivek
 * @throws UiObjectNotFoundException 
 * 
 ****************************************************************************************************************************************************************/
	public boolean waitForElement(Locators locator, String value , int timeout) throws InterruptedException, UiObjectNotFoundException{
		System.out.println("Waiting for element "+value+" for "+timeout);
		boolean isElementVisible= false;
		UiObject object = null;
		
		switch(locator){
		
		case NAME:
			object = new UiObject(new UiSelector().text(value));
			isElementVisible = waitForElement(object, timeout);
			if(!isElementVisible){
				Assert.fail("Element with "+ locator +"value "+ value +"does not become visible after "+ timeout+ " seconds");
			}
			break;
		
		case CONTENT_DESCRIPTION:
			object = new UiObject(new UiSelector().description(value));
			isElementVisible = waitForElement(object, timeout);
			if(!isElementVisible){
				Assert.fail("Element with "+ locator +"value "+ value +"does not become visible after "+ timeout+ " seconds");
			}
			break;
			
		default:
			break;
			
			
		}
		return isElementVisible;
	}

/****************************************************************************************************************************************************************
 * 
 * @param object (Uiobject)
 * @param timeout
 * @return boolean true :if element appears
 * 				   false  :if element does not appears
 * @throws InterruptedException
 * @author vivek
 * @throws UiObjectNotFoundException 
 * 
 ***************************************************************************************************************************************************************/
	public boolean waitForElement(UiObject object, int timeout) throws InterruptedException, UiObjectNotFoundException{
		System.out.println("Waiting for element "+object.getText()+" for "+timeout);
		boolean isElementFound = false;
		for(int counter = 0;  counter<timeout ; counter++){
			if(object.exists()){
				isElementFound= true;
				break;
			}
			else{
				Thread.sleep(1000);
				continue;
			}
		}
		return isElementFound;
	}
	
/****************************************************************************************************************************************************************
 * 
 * @param locator
 * @param value
 * @return void
 * @author vivek
 * 
 ****************************************************************************************************************************************************************/
	public void clickElementInList(Locators locator , String value){
		System.out.println("Clicking Element in list :"+locator+" value: "+value);
		UiScrollable scrollable = new UiScrollable(new UiSelector().className("android.widget.ListView"));
		switch(locator){
		
		case NAME:
			try{
				scrollable.getChildByText(new UiSelector().className("android.widget.TextView"), value).click();
			}catch(Exception e){
				System.out.println("not able to get child by name"+value);
			}
			break;
		case CLASSNAME:
			//TODO
			break;
		case CONTENT_DESCRIPTION:
			//TODO
			break;
		default:
			break;
		}
		

	}

/****************************************************************************************************************************************************************
 * 
 * @param locator
 * @param name
 * @return true: if selected property of element is true
 * 		   false : if selected property of element is false
 * @author vivek
 * 	
 ***************************************************************************************************************************************************************/
	public boolean isElementSelected(Locators locator , String name){
		System.out.println("Checking if element is selected "+name);
		UiObject object= null;
		boolean isSelected = false;
		switch(locator){
		
		case NAME: 
			object = new UiObject(new UiSelector().text(name));
			try{
				isSelected = object.isSelected();
			}catch(Exception e){
				System.out.println("UiObject not found");
			}
			break;
			
		default:
			break;
		}
		return isSelected;
	}

/**************************************************************************************************************************************************************
 * 	
 * @param characterCount
 * @return void
 * @author vivek
 * 
 **************************************************************************************************************************************************************/
	public void clearFocussedElementText(int characterCount){
		System.out.println("clearing text from text box");
		for(int counter=0; counter<characterCount ; counter++){
			UiDevice.getInstance().pressKeyCode(67);
		}
		
	}	


/****************************************************************************************************************************************************************
 * 
 * @return String : path of file where screenshot get saved 
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/
	public String takeScreenshot(){
		System.out.println("taking screenshot");
		String fileName= ("Screenshot"+RandomStringUtils.randomNumeric(10)+".png");
		System.out.println("FILE NAME          ******************"+fileName);
		File storePath = new File ("/storage/sdcard0/Pictures/"+fileName);
		UiDevice.getInstance().takeScreenshot(storePath);
		return fileName;
	}
/****************************************************************************************************************************************************************
 * 
 * @return String : path of file where screenshot get saved 
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/	
	public String returnScreenshot(){
		String fileName=takeScreenshot();
		String filePath= "http://192.168.1.135/images/"+fileName;
		return filePath;
		
	}

/****************************************************************************************************************************************************************
 * 
 * @return void
 * @param Locators
 * @param value
 * @param text
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/		
	
	public void enterText(Locators locators ,String value , String text){
		System.out.println("Entering text '"+text+"' in "+value);
		UiObject textField = null;
		try {
			switch(locators){
			case NAME :
				textField = new UiObject(new UiSelector().text(value));
				if(isElementPresentOnScreen(textField)){
					textField.setText(text);
//					closekeyboard();
				}
				break;
			case CONTENT_DESCRIPTION :
				textField = new UiObject(new UiSelector().description(value));
				if(isElementPresentOnScreen(textField)){
					textField.setText(text);
//					closekeyboard();
				}
			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void enterText(String text){
		try {
			System.out.println("Entering text "+text);
			if(isElementPresentOnScreen(new UiObject(new UiSelector().className("android.widget.EditText")))){
				UiObject textField = new UiObject(new UiSelector().className("android.widget.EditText"));
				textField.setText(text);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

/****************************************************************************************************************************************************************
 * 
 * closes the keyboard
 * 
 * @author vivek
 * @return void
 * 	
 ***************************************************************************************************************************************************************/
	public void closekeyboard(){
		//Use this function wisely whenever you think the keyboard needs to be closed or else the app will go back to previous page
		System.out.println("Closing keyboard");
		UiDevice.getInstance().pressBack();
	}
	
	
/***************************************************************************************************************************************************************
 * 	
 * @param uiobject
 * @throws UiObjectNotFoundException
 * 
 **************************************************************************************************************************************************************/
	public void tapAndHoldElement(UiObject uiobject) throws UiObjectNotFoundException{
		System.out.println("Tap and Hold :"+uiobject.getText());
		Rect rectangle = uiobject.getBounds();
		getUiDevice().swipe(rectangle.centerX(), rectangle.centerY(), rectangle.centerX(), rectangle.centerY(), 150);
	}
	
/****************************************************************************************************************************************************************
 * 
 * @param locators
 * @param value
 * @throws UiObjectNotFoundException
 ***************************************************************************************************************************************************************/
	
	public void tapAndHoldElement(Locators locators , String value) throws UiObjectNotFoundException{
		System.out.println("Tap and Hold :"+value+" by "+locators);
			UiObject object = null;
		try{
			switch (locators) {
			case NAME:
				object = new UiObject(new UiSelector().text(value));
				tapAndHoldElement(object);
				break;
				
			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				tapAndHoldElement(object);
				break;
				
			default:
				break;
			}
		}catch(Exception e){
			
		}
	}
	
	
	public UiObject getElement(Locators locators , String value , int index){
		System.out.println("Getting element "+value+" by "+locators+" index: "+index);
		UiObject object = null;
		try{
			switch (locators) {
			case NAME:
				object = new UiObject(new UiSelector().text(value));
				break;
				
			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				object = new UiObject(new UiSelector().className(value).index(index));
			default:
				break;
			}
		}catch(Exception e){
			
		}
		return object;
	}
	
	public UiObject getElement(Locators locators , String value ){
		System.out.println("Getting element "+value+" by "+locators);
		UiObject object = null;
		try{
			switch (locators) {
			case NAME:
				object = new UiObject(new UiSelector().text(value));
				break;
				
			case CONTENT_DESCRIPTION:
				object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				object = new UiObject(new UiSelector().className(value));	
			default:
				break;
			}
		}catch(Exception e){
			
		}
		return object;
	}
	
	public UiObject getChild(UiObject parent ,Locators locators , String value , int index){
		System.out.println("Getting child of "+parent+" by "+locators+" value: "+value+" index :"+index);
		UiObject child = null;
		try{
			switch (locators) {
			case NAME:
//				object = new UiObject(new UiSelector().text(value));
				break;
				
			case CONTENT_DESCRIPTION:
//				object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				child = parent.getChild(new UiSelector().className(value).index(index));	
			default:
				break;
			}
		}catch(Exception e){
			System.out.println("Child not found");
		}
		return child;
		
	}
	
	public UiObject getChild(UiObject parent ,Locators locators , String value){
		System.out.println("Getting child of "+parent+" by "+locators+" value: "+value);
		UiObject child = null;
		try{
			switch (locators) {
			case NAME:
//				object = new UiObject(new UiSelector().text(value));
				break;
				
			case CONTENT_DESCRIPTION:
//				object = new UiObject(new UiSelector().description(value));
				break;
			case CLASSNAME:
				child = parent.getChild(new UiSelector().className(value));	
			default:
				break;
			}
		}catch(Exception e){

			System.out.println("Child not found");

		}
		return child;
		
	}
	
	public void swipe(Point[] cordinates){
		System.out.println("swiping");
		getUiDevice().swipe(cordinates, 20);
	}
/****************************************************************************************************************************************************************
 * 
 * @param name
 * @return
 * @author vivek
 * 
 ***************************************************************************************************************************************************************/
	@Deprecated
	public boolean isElementPresentOnScreen(String name){
		boolean isElementPresent = false;
		try{
			for (int i=0;i<5;i++){
				if(new UiObject(new UiSelector().text(name)).exists()){
					return true;
				}
				else{
					Thread.sleep(1000);
					continue;
				}
			}
			if(!isElementPresent){
				System.out.println("Element "+name+" is not present in the list  "+returnScreenshot());
			}
			
			return isElementPresent;
		}catch(Exception e){
			return isElementPresent;
		}
		
	}

public void setLandscape() throws RemoteException{
		System.out.println("Setting Landscape orientation");
		UiDevice.getInstance().setOrientationRight();
	

}
public void setPotrait() throws RemoteException{
	System.out.println("Setting Potrait orientation");
		UiDevice.getInstance().setOrientationNatural();
	

}




}
