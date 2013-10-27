package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractBasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String expectedUrlPath() {
		return URL_ROOT + "/mirebalais/home.page";
	}

}
