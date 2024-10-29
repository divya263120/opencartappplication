package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

public class RegistrationPageTest extends BaseTest{

	@BeforeClass
	public void registrationPageSetUp() {
		registerpage = loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] userRegData() {
		return new  Object[][] {
			{"jaggu","jaggu1","546535353","jgu@123","yes"},
			{"pinky","pinky11","54444443","pink@123","no"},
		};
	}
	@DataProvider
	public Object[][] getUserRegDataFromExcelSheet() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEETNAME);
	}
	@DataProvider
	public Object[][] getUserRegDataFromCSV(){
		
		return CSVUtil.csvData(AppConstants.REGISTER_SHEETNAME);
	}
	
	
	@Test(dataProvider="getUserRegDataFromCSV")
	public void userRegTest(String firstName,String secondName,String phoneNo,String password,String subscribe) {
		Assert.assertTrue
		(registerpage.userRegister(firstName, secondName, StringUtils.getRandomEmailId(),phoneNo, password, subscribe),
		AppError.USER_REG_NOT_DONE);
	}
}
