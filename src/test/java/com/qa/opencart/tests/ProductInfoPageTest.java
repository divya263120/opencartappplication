package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.errors.AppError;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetUp() {
		
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@Test
	public void productHeaderTest() {
		searchpage = accountpage.doSearch("macbook");
		productpage = searchpage.selectProduct("MacBook Pro");
		Assert.assertEquals(productpage.getProductHeader(), "MacBook Pro",AppError.PRODUCT_HEADER_NOT_MATCH);
	}
	
	@Test
	public void productImagesCountTest() {
		searchpage = accountpage.doSearch("macbook");
		productpage = searchpage.selectProduct("MacBook Pro");
		Assert.assertEquals(productpage.getProductImgCount(), 4,AppError.IMAGE_COUNT_NOT_MATCH);
	}
	
	@Test
	public void productInfoMapTest() {
		searchpage = accountpage.doSearch("macbook");
		productpage = searchpage.selectProduct("MacBook Pro");
		
		Map<String,String> productInfo = productpage.productMapInfo();
		System.out.println("product information");
		System.out.println(productInfo);
		
		
		softAssert.assertEquals(productInfo.get("productName"), "MacBook Pro");
		softAssert.assertEquals(productInfo.get("productImagesCount"), "4");
		softAssert.assertEquals(productInfo.get("Brand"), "Apple");
		softAssert.assertEquals(productInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfo.get("Reward Points"), "800");
		softAssert.assertEquals(productInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfo.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(productInfo.get("exTaxPrice"), "$2,000.00");
		
		softAssert.assertAll();
		
		
		System.out.println("----test is done");
	}
	
}
