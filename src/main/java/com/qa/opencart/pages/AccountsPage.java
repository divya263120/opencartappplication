package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.TimeUtil;
import com.qa.opencart.utils.WebElementsUtil;

public class AccountsPage {

	private WebDriver driver;
	private WebElementsUtil eleUtil;


	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new WebElementsUtil(driver);
	}

	private By  logout = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.cssSelector("div#search input");
	private By searchclick = By.cssSelector("div#search button");


	public String AccountsPageTitle() {

		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Account page title:" +title);
		return title;
	}

	public String AccountsPageURL() {

		String url = eleUtil.waitForUrlContains(AppConstants.ACC_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("Account page url:" +url);
		return url;
	}

	public boolean isLogOutLinkExist() {
		return  eleUtil.doIsDisplayed(logout);
	}

	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.waitForElementsVisible(headers, TimeUtil.DEFAULT_MEDIUM_TIME);
		List<String> AccPageHeadersList = new ArrayList<String>();
		for(WebElement e :headerList ) {
			String text = e.getText();
			AccPageHeadersList.add(text);
		}

		return AccPageHeadersList;
	}

	public boolean isSearchLinkExist() {
		return eleUtil.doIsDisplayed(search);
	}

	public SearchResultPage doSearch(String SearchKey) {

		System.out.println("search key is:" +SearchKey);
		if(isSearchLinkExist()) {

			eleUtil.dosendkeys(search, SearchKey, TimeUtil.DEFAULT_TIME);
			eleUtil.doClick(searchclick);

			return new SearchResultPage(driver);
		}
		else
		{
			System.out.println("search field is not present on the page");
			return null;
		}


	}
}
