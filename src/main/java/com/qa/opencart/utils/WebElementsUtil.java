package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.opencart.exceptions.ElementException;


public class WebElementsUtil {

	private WebDriver driver;

	public WebElementsUtil(WebDriver driver)
	{
		this.driver = driver;
	}

	//checking Send keys value should not be null

	private void checkNull(String value) {
		if(value==null) {
			throw  new ElementException("value is null");
		}
	}

	//doSendKeys method
	public void doSendKeys(By locator,String value)
	{
		checkNull(value);
		try {
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
		}
		catch(NullPointerException e) {
			throw new ElementException("WebElement is null");
		}
	}

	public void dosendkeys(By locator,String value,int timeout) {
		checkNull(value);
		waitForElementVisible(locator,timeout).clear();
		waitForElementVisible(locator,timeout).sendKeys(value);
		
	}
	
	public void doSendKeys(By locator,CharSequence... value)
	{
		
		try {
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
		}
		catch(NullPointerException e) {
			throw new ElementException("WebElement is null");
		}
	}

	//getElement method using findElement
	public WebElement getElement(By locator)
	{
		try {
			WebElement elt = driver.findElement(locator);
			return elt;
		}
		catch(NoSuchElementException e) {
			System.out.println("no element found at " + locator);
			e.printStackTrace();
			return null;
		}
	}

	//do click method for links using click()

	public void doClick(By locator)
	{
		getElement(locator).click();
	}


	public  void doClick(By locator,int timeout) {

		waitForElementVisible(locator,timeout).click();
	}

	//getText method for different locators
	public String doGettext(By locator)
	{
		return getElement(locator).getText();
	}


	//getAttribute method to get value of attribute

	public String doGetAttribute(By locator,String value)
	{
		return getElement(locator).getAttribute(value);
	}

	//generic function for findElements	
	public  List<WebElement> getElements (By locator)
	{
		return driver.findElements(locator);
	}

	//generic function to find size of webElements
	public int getElementsSize(By locator)
	{
		return getElements(locator).size();
	}


	//generic function to get text of List<webElements>
	public List<String>  getElementsText(By locator)
	{
		List<WebElement> webList= getElements(locator);
		List<String> textList = new ArrayList<String>();
		String text;
		int elementsHavingTextCount=0;
		int elementsNotHavingTextCount=0;

		for(WebElement e : webList) {
			text = e.getText();
			if(text.length()!=0) {
				textList.add(text);
				elementsHavingTextCount++;
			}
			else {
				elementsNotHavingTextCount++;
			}
		}
		System.out.println(elementsHavingTextCount);
		System.out.println(elementsNotHavingTextCount);
		return textList;
	}

	public  boolean doIsDisplayed(By locator) {
		try {
			driver.findElement(locator).isDisplayed();
			return true;
		}
		catch(NoSuchElementException e) {
			System.out.println("element with locator " +locator+ " is not displayed " );
			return false;
		}
	}	


	public  void isDisplayedMultipleTimes(By locator) {

		int elementCount = driver.findElements(locator).size();
		if(elementCount==1) {
			System.out.println("element located once with " +locator);
		}
		else {
			System.out.println("element located " +elementCount+ " times with " +locator);
		}
	}

	public  void isDisplayedwithExpectedValue(By locator,int expectedValue) {
		int elementCount = driver.findElements(locator).size();
		if(elementCount== expectedValue) {
			System.out.println("element located  with  " + expectedValue + locator);
		}
		else {
			System.out.println("element located " +elementCount+ " times but the expected value is " + expectedValue );
		}
	}
	//***************************select methods*****************
	public  void doSelectByIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public  void doSelectByVisibleText(By locator,String text) {
		Select select = new Select(getElement(locator));
		text.trim();
		select.selectByVisibleText(text);
	}

	public  void doSelectByvalue(By locator,String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}


	public  int getDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}


	public  List<String> getDropDownOptionsText(By locator) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionslist = select.getOptions();
		System.out.println(optionslist.size());
		List<String> optionstextList = new ArrayList<String>();

		for(WebElement e: optionslist ) {
			String text = e.getText();
			optionstextList.add(text);
		}
		return optionstextList;
	}

	public  void selectValueFromDropDown(By locator,String optionText) {
		Select select = new Select(getElement(locator));
		List<WebElement> optinslist = select.getOptions();

		for(WebElement e :optinslist ) {
			String text = e.getText();
			if(text.equals(optionText)) {
				e.click();
			}
		}
	}

	public void selectDropDownOptionsWithoutSelectClass(By locator,String optionText) {

		List<WebElement> optionslist =	getElements(locator);
		for(WebElement e:optionslist ) {
			String text = e.getText();
			if(text.equals(optionText)) {
				e.click();
				break;
			}
			else
			{
				throw new ElementException("no such option "+optionText);
			}
		}
	}
	public  void multipleSelect(By locator,String optionText)

	{
		Select select = new Select(getElement(locator));
		if(select.isMultiple())
		{
			select.selectByVisibleText(optionText);

		}
	}
	public  void deSelectOption(By locator,String optionText) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for(WebElement e: optionsList) {

			if( e.isSelected()) {
				select.deselectByVisibleText(optionText);
			}
			else {
				System.out.println("cannot deselect " + optionText +" because deslected option is not yet  selected " );
			}
			break;
		}


	}


	//************** Actions Util ***********//

	public void moveFromParentToChild(By parentLocator,By childLocator) throws InterruptedException {


		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentLocator)).perform();
		Thread.sleep(2000);

		doClick(childLocator);


	}
	public  void dragAndDrop(By sourceelt,By targetelt) {

		Actions act = new Actions(driver);
		act.dragAndDrop(getElement(sourceelt), getElement(targetelt)).perform();

	}

	public  void actionsDoSendKeys(By locator,String value) {

		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator),value).perform();
	}


	public  void actionDoClick(By locator) {

		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();;
	}


	/*
	 * this method is for to enter value in the text field with pause	
	 */
	public  void doSendKeysActionsWithPause(By locator,int time,String searchKey)
	{
		String text =searchKey;
		char[] ch = text.toCharArray();

		Actions act = new Actions(driver);

		for(char c:ch) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(time).perform();	
		}
	}


	/*
	 * this method is for to enter value in the text field  with pause	default(500s)
	 */
	public  void doSendKeysActionsWithPause(By locator,String searchKey)
	{
		String text =searchKey;
		char[] ch = text.toCharArray();

		Actions act = new Actions(driver);

		for(char c:ch) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).perform();	
		}
	}

	//****** wait until  *******//

	/**An expectation for checking that an element is present on the DOM of a page.
	 *  This does not
	 *  necessarily mean that the element is visible.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  WebElement waitForElementPresence(By locator,int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is
	 * greater than 0.  
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public  WebElement waitForElementVisible(By locator,int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator,int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		catch(Exception e) {
			return List.of();//return empty array list
		}
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible.
	 *  Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
	   Parameters:locator used to find the element
	   Returns:the list of WebElements once they are located
	 * @param locator
	 */
	public List<WebElement> waitForElementsPresence(By locator,int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}


	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.

	 * @param locator
	 * @param timeOut
	 */
	public void clickWhenReady(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}


	/**
	 * An expectation for checking that the title contains a case-sensitive substring

	 * @param value
	 * @param timeOut
	 * @return
	 */

	public  String  waitForTitleToBe(String value,int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			if(wait.until(ExpectedConditions.titleIs(value))) {
				return driver.getTitle();
			}
		}

		catch(TimeoutException e) {

			System.out.println("title not found");
		}
		return driver.getTitle();
	}

	/**
	 * An expectation for checking the title of a page.

	 * @param value
	 * @param timeOut
	 * @return
	 */

	public  String  waitForTitleContainsToBe(String value,int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			if(wait.until(ExpectedConditions.titleIs(value))) {
				return driver.getTitle();
			}
		}

		catch(TimeoutException e) {

			System.out.println("title not found");
		}
		return driver.getTitle();
	}

	/**
	 * An expectation for the URL of the current page to contain specific text.

	 * @param value
	 * @param timeOut
	 * @return
	 */
	public  String  waitForUrlContains(String value,int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			if(wait.until(ExpectedConditions.urlContains(value))) {
				return driver.getCurrentUrl();
			}
		}

		catch(TimeoutException e) {

			System.out.println("title not found");
		}
		return driver.getCurrentUrl();
	}

	/**
	 * An expectation for the URL of the current page to be a specific url.

	 * @param value
	 * @param timeOut
	 * @return
	 */

	public  String  waitForUrlToBe(String value,int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			if(wait.until(ExpectedConditions.urlToBe(value))) {
				return driver.getCurrentUrl();
			}
		}

		catch(TimeoutException e) {

			System.out.println("title not found");
		}
		return driver.getCurrentUrl();
	}


	/************ Alert with wait ***********/

	public  Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public  String getAlertText(int timeOut) {
		Alert alert = waitForJSAlert(timeOut);
		String text =alert.getText();
		alert.accept();
		return text;
	}

	public  void alertAccept(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}

	public  void alertdimiss(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}

	public  void alertSendKeys(int timeOut,String value) {
		Alert alert = waitForJSAlert(timeOut);
		alert.sendKeys(value);
		alert.accept();

	}

	//*****************  Wait for Frame ***************//

	public  void waitForFrame(By framelocator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));

	}

	public  void waitForFrameByIndex(int index,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));

	}

	public  void waitForFrameByStringOrId(String nameOrId,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));

	}

	public  void waitForFrameByFrameElement(WebElement frameElement,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}

	public void waitForwindowsToBe(int totalWindows,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindows));
	}

}
