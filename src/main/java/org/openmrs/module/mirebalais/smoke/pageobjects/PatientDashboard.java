package org.openmrs.module.mirebalais.smoke.pageobjects;

import java.util.HashMap;
import java.util.List;

import org.openmrs.module.mirebalais.smoke.pageobjects.forms.ConsultNoteForm;
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
	public static final String CONSULTATION = "Konsiltasyon";
	private HashMap<String, By> formList;
	private ConsultNoteForm consultNoteForm;

	public PatientDashboard(WebDriver driver) {
		super(driver);
		consultNoteForm = new ConsultNoteForm(driver);
//		eDNoteForm = new EmergencyDepartmentNoteForm(driver);
//		retroConsultNoteForm = new RetroConsultNoteForm(driver);
//		xRayForm = new XRayForm(driver);
		createFormsMap();
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

	public void addConsultNoteWithDischarge(String primaryDiagnosis) throws Exception {
		openForm(formList.get("Consult Note"));
		consultNoteForm.fillFormWithDischarge(primaryDiagnosis);
	}

	public void openForm(By formIdentification) {
		clickOn(formIdentification);
	}
	
	public Integer countEncountersOfType(String encounterName) {
        try {
        	waitForElement(By.cssSelector("span.encounter-name"));
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
	
	private void createFormsMap() {
		formList = new HashMap<String, By>();
		formList.put("Consult Note", By.id("mirebalais.consult"));
		formList.put("Surgical Note", By.id("mirebalais.surgicalOperativeNote"));
		formList.put("Order X-Ray", By.id("org.openmrs.module.radiologyapp.orderXray"));
		formList.put("ED Note", By.id("mirebalais.edConsult"));
	}

}
