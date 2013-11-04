package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VitalsApp extends AbstractBasePage {

	private static final By CONFIRM_PATIENT_BUTTON = By.className("icon-arrow-right");
	private static final By HEIGHT_INCHES_FIELD = By.id("height_inches");
	private static final By SEARCH_PATIENT_FIELD = By.id("patient-search-field-search");
	private static final By WEIGHT_INCHES_FIELD = By.id("weight_lbs");
	
	public VitalsApp(WebDriver driver) {
		super(driver);
	}

	public void enterPatientIdentifier(String patientID) {
		setText("patient-search-field-search", patientID);
	}

	public void confirmPatient() {
		clickOn(CONFIRM_PATIENT_BUTTON);
	}

	public void enterVitals() {
        hitEnterOnInchesField();
        setTextToFieldInsideSpan("height_cm", "15");
        hitEnterOnLbsField();
        setTextToFieldInsideSpan("weight_kg", "50");
		hitEnterOnBMI();
        hitEnterOnFahrenheitField();
        setTextToFieldInsideSpan("temperature_c", "36");
        setTextToFieldInsideSpan("heart_rate", "50");
        setTextToFieldInsideSpan("respiratory_rate", "50");
        setTextToFieldInsideSpan("bp_systolic", "120");
        setTextToFieldInsideSpan("bp_diastolic", "80");
        setTextToFieldInsideSpan("o2_sat", "50");
		
		driver.findElement(By.id("confirmationQuestion")).findElement(By.className("confirm")).click();
	}

	private void hitEnterOnBMI() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.getElementById('hidden-calculated-bmi').setAttribute('type', 'text');");
		driver.findElement(By.id("hidden-calculated-bmi")).sendKeys(Keys.RETURN);
	}

    private void hitEnterOnFahrenheitField() {
        driver.findElement(By.id("temperature_f")).sendKeys(Keys.RETURN);
    }

    private void hitEnterOnLbsField() {
        setText(WEIGHT_INCHES_FIELD, "");
    }

    private void hitEnterOnInchesField() {
        driver.findElement(HEIGHT_INCHES_FIELD).sendKeys(Keys.RETURN);
    }

	public void captureVitalsForPatient(String identifier) {
		enterPatientIdentifier(identifier);
		confirmPatient();
		enterVitals();
	}
	
	public boolean isSearchPatientDisplayed() {
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(SEARCH_PATIENT_FIELD));
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
	}

	@Override
	public String expectedUrlPath() {
		return URL_ROOT + "/coreapps/patientdashboard/patientDashboard.page";
	}
	
}