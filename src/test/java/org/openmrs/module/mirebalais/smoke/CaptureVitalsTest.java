package org.openmrs.module.mirebalais.smoke;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.mirebalais.smoke.pageobjects.HomePage;
import org.openmrs.module.mirebalais.smoke.pageobjects.PatientDashboard;
import org.openmrs.module.mirebalais.smoke.pageobjects.VitalsApp;
import org.openmrs.uitestframework.test.TestBase;
import org.openmrs.uitestframework.test.TestData.PatientInfo;

public class CaptureVitalsTest extends TestBase {

	public static final String PATIENT_IDENTIFIER_TYPE = "ZL EMR ID";

	private HomePage appDashboard;
	private PatientDashboard patientDashboard;
	private PatientInfo testPatient;

	@Before
	public void before() {
		testPatient = createTestPatient(PATIENT_IDENTIFIER_TYPE);
        appDashboard = new HomePage(driver);
        patientDashboard = new PatientDashboard(driver);

        login();
		assertPage(appDashboard);
	}
	
	@After
	public void after() throws Exception {
		deletePatientUuid(testPatient.uuid);
		dbUnitTearDown();
	}

	@Test
	public void checkInAndCaptureVitalsThruVitalsApp() throws Exception {
		System.out.println(testPatient);
        VitalsApp vitals = new VitalsApp(driver);
		
		appDashboard.goToPatientPage(testPatient.id);
		patientDashboard.startVisit();
		
		appDashboard.openCaptureVitalsApp();
		vitals.captureVitalsForPatient(testPatient.identifier);
		assertThat(vitals.isSearchPatientDisplayed(), is(true));
		
		appDashboard.goToPatientPage(testPatient.id);
		assertThat(patientDashboard.countEncountersOfType(PatientDashboard.VITALS), is(1));
	}
	
}
