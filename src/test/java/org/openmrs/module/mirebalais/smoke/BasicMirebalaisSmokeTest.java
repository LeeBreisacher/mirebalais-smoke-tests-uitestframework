package org.openmrs.module.mirebalais.smoke;

import org.junit.Before;
import org.openmrs.module.mirebalais.smoke.pageobjects.HomePage;
import org.openmrs.module.mirebalais.smoke.pageobjects.PatientDashboard;
import org.openmrs.uitestframework.test.TestBase;
import org.openmrs.uitestframework.test.TestData.PatientInfo;

/**
 * UI Smoke Test superclass, adds Mirebalais-specifics to 
 * the UI Test Framework (TestBase).  
 */
public class BasicMirebalaisSmokeTest extends TestBase {

	public static final String PATIENT_IDENTIFIER_TYPE = "ZL EMR ID";
	public static final String PATIENT_IDENTIFIER_SOURCE = "5";
	
	protected HomePage appDashboard;
	protected PatientDashboard patientDashboard;
	
	@Before
	public void initPages() {
        appDashboard = new HomePage(driver);
        patientDashboard = new PatientDashboard(driver);
	}
	
	@Override
	public PatientInfo createTestPatient() {
		PatientInfo testPatient = createTestPatient(PATIENT_IDENTIFIER_TYPE, PATIENT_IDENTIFIER_SOURCE);
		createTestEncounter("Patient Registration", testPatient);
		return testPatient;
	}
}
