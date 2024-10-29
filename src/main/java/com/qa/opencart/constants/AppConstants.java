package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String CONFIG_QA_FILEPATH = "./src/test/resources/config/config.properties";
	public static final String CONFIG_DEV_FILEPATH = "./src/test/resources/config/config.properties";
	public static final String CONFIG_STAGE_FILEPATH = "./src/test/resources/config/config.properties";
	public static final String CONFIG_PROD_FILEPATH = "./src/test/resources/config/config.properties";
	
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String ACC_PAGE_FRACTION_URL = "route=account/account";
	
	public static final  List<String> ACC_PAGE_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	
	
	
	//**********/sheet constants***********/
	public static final String REGISTER_SHEETNAME = "register";
	public static final String PRODUCT_SHEETNAME = "product";
	
}
