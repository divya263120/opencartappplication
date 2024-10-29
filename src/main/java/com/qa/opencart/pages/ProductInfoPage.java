package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.TimeUtil;
import com.qa.opencart.utils.WebElementsUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private WebElementsUtil eleUtil;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productImageCount = By.cssSelector("div#content li img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new WebElementsUtil(driver);
	}
	
	
	public String getProductHeader() {
		String header = eleUtil.doGettext(productHeader);
		System.out.println("product header:" +header);
		return header;
	}
	
	public int getProductImgCount() {
		int imagesCount=eleUtil.waitForElementsVisible(productImageCount, TimeUtil.DEFAULT_MEDIUM_TIME).size();
		System.out.println("product images count :" +imagesCount);
		return imagesCount;
	}
	
	public Map<String,String> productMapInfo(){
		productMap = new HashMap<String,String>();
		productMap.put("productName", getProductHeader());
		productMap.put("productImagesCount", String.valueOf(getProductImgCount()));
		
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProductMetaData() {
		List<WebElement> metaDataList= eleUtil.waitForElementsVisible(productMetaData,TimeUtil.DEFAULT_MEDIUM_TIME);
		for(WebElement e :metaDataList) {
			String metaData = e.getText();
			String meta[]=metaData.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey,metaValue);
				
		}
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, TimeUtil.DEFAULT_MEDIUM_TIME);
		String priceValue = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", priceValue);
		productMap.put("exTaxPrice", exTaxPrice);
	
	}
}
