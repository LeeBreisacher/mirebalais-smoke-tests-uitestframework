package org.openmrs.module.mirebalais.smoke.pageobjects.forms;
/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ConsultNoteForm extends AbstractBasePage {

    // TODO key on something other than text
	public static final String ADMISSION = "Admisyon";
	public static final String DEATH = "Mouri";
	public static final String DISCHARGE = "Egzeyat";
	public static final String TRANSFER = "Transfè anndan lopital la";

    private By locationsForTransferWithinHospital = By.cssSelector("#transferInternalLocation option");
    private By locationsForAdmission = By.cssSelector("#admissionLocation option");

    public ConsultNoteForm(WebDriver driver) {
		super(driver);
	}

    public void fillFormWithDischarge(String primaryDiagnosis) throws Exception {
        fillFormWithBasicInfo(primaryDiagnosis, DISCHARGE);
    }

    public void fillFormWithDeath(String primaryDiagnosis) throws Exception {
        choosePrimaryDiagnosis(primaryDiagnosis);
        chooseDisposition(DEATH);
        WebElement dateField = driver.findElement(By.cssSelector("#dateOfDeath input"));
        dateField.click();
        dateField.sendKeys(Keys.RETURN);
        confirmData();
	}
	
	public String fillFormWithAdmissionAndReturnLocation(String primaryDiagnosis, int locationNumbered) throws Exception {
        return fillFormAndReturnPlace(primaryDiagnosis, ADMISSION, locationsForAdmission, locationNumbered);
	}

	public String fillFormWithTransferAndReturnLocation(String primaryDiagnosis, int locationNumbered) throws Exception {
        return fillFormAndReturnPlace(primaryDiagnosis, TRANSFER, locationsForTransferWithinHospital, locationNumbered);
	}

    public void editPrimaryDiagnosis(String primaryDiagnosis) throws Exception {
        removePrimaryDiagnosis();
        choosePrimaryDiagnosis(primaryDiagnosis);
        confirmData();
    }

    protected void fillFormWithBasicInfo(String primaryDiagnosis, String disposition) throws Exception {

        assertThat(subbmitButtonIsEnabled(),is(false));

        choosePrimaryDiagnosis(primaryDiagnosis);
        assertThat(subbmitButtonIsEnabled(),is(false));

        chooseDisposition(disposition);
        // for real-time consult note, submit button should not be enabled until diagnosis and disposition have been selected
        assertThat(subbmitButtonIsEnabled(),is(true));

        confirmData();
    }

    protected String fillFormAndReturnPlace(String primaryDiagnosis, String disposition, By dropdownOptionsLocator, int locationNumber) throws Exception  {
		choosePrimaryDiagnosis(primaryDiagnosis);
		chooseDisposition(disposition);
        waitForElement(dropdownOptionsLocator);
        WebElement location = driver.findElements(dropdownOptionsLocator).get(locationNumber);
        String locationText = location.getText();
        location.click();
        confirmData();
        return locationText;
	}
	
	protected String chooseOption(By placeCombo, WebElement location) {
        waitForElement(placeCombo);
        location.click();
		return location.getText();
	}

	protected void choosePrimaryDiagnosis(String primaryDiagnosis) {
		setText("diagnosis-search", primaryDiagnosis);
		driver.findElement(By.cssSelector("strong.matched-name")).click();
	}
	
	protected void chooseDisposition(String dispositionText) throws Exception {
        Select dispositions = new Select(driver.findElement(By.cssSelector("span[id^='disposition'] select:nth-of-type(1)")));  // find the first select that is child of the span whose id starts with "disposition"
        dispositions.selectByVisibleText(dispositionText);
    }

    protected void removePrimaryDiagnosis() {
        clickOn(By.cssSelector("#display-encounter-diagnoses-container .delete-item"));
    }

    protected void confirmData() {
		clickOn(By.cssSelector("#buttons .confirm"));
	}

    protected boolean subbmitButtonIsEnabled() {
        return !driver.findElement(By.cssSelector("#buttons .confirm")).getAttribute("class").contains("disabled");
    }

	@Override
	public String expectedUrlPath() {
		return null;
	}
}
