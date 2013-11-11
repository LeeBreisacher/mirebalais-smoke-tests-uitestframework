package org.openmrs.module.mirebalais.smoke;

import org.junit.Before;
import org.openmrs.module.mirebalais.smoke.pageobjects.HomePage;
import org.openmrs.module.mirebalais.smoke.pageobjects.PatientDashboard;
import org.openmrs.uitestframework.test.TestBase;

public class BasicMirebalaisSmokeTest extends TestBase {

	public static final String PATIENT_IDENTIFIER_TYPE = "ZL EMR ID";
	
	protected HomePage appDashboard;
	protected PatientDashboard patientDashboard;
	
	@Before
	public void initPages() {
        appDashboard = new HomePage(driver);
        patientDashboard = new PatientDashboard(driver);
	}
	
}
