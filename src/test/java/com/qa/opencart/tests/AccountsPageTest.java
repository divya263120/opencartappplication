package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

public class AccountsPageTest extends BaseTest {

	
	@BeforeClass
	public void accPagesetUp() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void AccPageTitleTest() {
		Assert.assertEquals(accountpage.AccountsPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void AccPageUrlTest() {
		Assert.assertTrue(accountpage.AccountsPageURL().contains(AppConstants.ACC_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
	}
	
	@Test
	public void logOutLinkTest() {
		Assert.assertEquals(accountpage.isLogOutLinkExist(), true,AppError.LINK_NOT_EXIST);
	}
	
	@Test
	public void AccPageHeadersTest() {
		List<String> accPageHeadersList = accountpage.getAccPageHeaders();
		Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST,AppError.LIST_NOT_MATCH);
		
	}
	@DataProvider
	public Object[][] getSearchData(){
	return	new Object[][] {
			{"macbook",3},
			{"samsung",2},
			{"imac",1},
			{"airtel",0}
		};
		
	}
	@Test(dataProvider = "getSearchData")
	public void searchTest(String searchKey,int resultCount) {
		searchpage = accountpage.doSearch(searchKey);
	Assert.assertEquals(searchpage.getSearchResultCount(), resultCount,AppError.RESULT_COUNT_NOT_MATCH);
	
}
	
}
