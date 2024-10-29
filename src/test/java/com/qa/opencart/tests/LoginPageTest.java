package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class LoginPageTest extends BaseTest {

	@Test(priority=1)
	public void loginPageTitleTest() {
		
		String title = loginpage.loginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	@Test(priority=2)
	public void loginPageUrlTest() {
		
		String url = loginpage.loginPageURL();
	    Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
	}
	
	
	@Test (priority=3)
	public void forgotPwdLinkTest() {
		
		boolean linkExist = loginpage.CheckForgotPwdLinkExist();
		Assert.assertEquals(linkExist, true,AppError.LINK_NOT_EXIST);
	}
	
	@Test(priority=4)
	public void LoginTest() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountpage.AccountsPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND );
	}
}
