package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientDashboard extends AbstractBasePage {

	static final By actions = By.cssSelector(".actions");
	static final By checkIn = By.cssSelector("a i.icon-check-in");
	static final By confirmStartVisit = By.cssSelector("#quick-visit-creation-dialog .confirm");
	static final By activeVisit = By.cssSelector(".visit-actions.active-visit");

	public PatientDashboard(WebDriver driver) {
		super(driver);
	}

	@Override
	public String expectedUrlPath() {
		return URL_ROOT + "/coreapps/patientdashboard/patientDashboard.page";
	}

	public void startVisit() {
		hoverOn(actions);
		clickOn(checkIn);
		clickOn(confirmStartVisit);
		waitForElement(activeVisit);
	}

}
