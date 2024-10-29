package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.TimeUtil;
import com.qa.opencart.utils.WebElementsUtil;

public class LoginPage {

	private WebDriver driver;
	private WebElementsUtil eleUtil;

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotpassword = By.linkText("Forgotten Password");
	private By login = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new WebElementsUtil(driver);
	}


	public String loginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
		System.out.println("Login page title:" +title);
		return title;
	}

	public String loginPageURL() {
		String url = eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("Login page url:" +url);
		return url;
	}


	public boolean CheckForgotPwdLinkExist() {

		return eleUtil.doIsDisplayed(forgotpassword);
	}

	public AccountsPage doLogin(String userName,String pwd) {
		eleUtil.dosendkeys(email, userName, TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(login);


		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIME);;
		System.out.println("title after dologin:" +title);
		return new AccountsPage(driver);

	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink, TimeUtil.DEFAULT_MEDIUM_TIME);
		return new RegisterPage(driver);
	}



}
