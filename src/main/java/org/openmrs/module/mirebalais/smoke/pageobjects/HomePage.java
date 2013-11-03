package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractBasePage {

	public static final String ACTIVE_VISITS = "org-openmrs-module-mirebalais-activeVisitsHomepageLink-app";

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String expectedUrlPath() {
		return URL_ROOT + "/mirebalais/home.page";
	}

	public void openActiveVisitsApp() {
        openApp(ACTIVE_VISITS);
	}

	private void openApp(String appIdentifier) {
		driver.get(properties.getWebAppUrl());
		clickOn(By.id(appIdentifier));
//        waitForJsVariable("Navigator.isReady");
	}

	public void goToPatientPage(String patientId) {
        driver.get(properties.getWebAppUrl() + "/coreapps/patientdashboard/patientDashboard.page?patientId=" + patientId);
	}

}
