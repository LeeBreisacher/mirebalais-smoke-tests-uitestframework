package org.openmrs.module.mirebalais.smoke.pageobjects;

import java.util.List;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PatientDashboard extends AbstractBasePage {

	static final By actions = By.cssSelector(".actions");
	static final By checkIn = By.cssSelector("a i.icon-check-in");
	static final By confirmStartVisit = By.cssSelector("#quick-visit-creation-dialog .confirm");
	static final By activeVisit = By.cssSelector(".visit-actions.active-visit");
	public static final String VITALS = "Siy Vito";

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

	public Integer countEncountersOfType(String encounterName) {
        try {
        	waitForElement(By.cssSelector("span.encounter-name"));
//            wait5seconds.until(presenceOfElementLocated(By.cssSelector("span.encounter-name")));
        }
        catch (TimeoutException e) {
            return 0;
        }

		int count = 0;
		List<WebElement> encounters = findElements(By.cssSelector("span.encounter-name"));
		for (WebElement encounter : encounters) {
			if (encounter.getText().equals(encounterName))
				count++;
		}
		return count;
	}
	
}
