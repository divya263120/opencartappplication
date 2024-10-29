package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.TimeUtil;
import com.qa.opencart.utils.WebElementsUtil;

public class SearchResultPage {

	private WebDriver driver;
	private WebElementsUtil eleUtil;
	
	
	private By searchResults = By.cssSelector("div.product-thumb h4 a");
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new WebElementsUtil(driver);
	}
	
	public int getSearchResultCount() {
		
		List<WebElement> searchList =eleUtil.waitForElementsVisible(searchResults,TimeUtil.DEFAULT_MEDIUM_TIME);
		System.out.println(searchList.size());
		
		return searchList.size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		
		eleUtil.doClick(By.linkText(productName),TimeUtil.DEFAULT_LONG_TIME);
		return new ProductInfoPage(driver);
	}
}
