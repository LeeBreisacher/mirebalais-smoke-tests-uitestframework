package org.openmrs.module.mirebalais.smoke;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.mirebalais.smoke.pageobjects.HomePage;
import org.openmrs.module.mirebalais.smoke.pageobjects.PatientDashboard;
import org.openmrs.uitestframework.page.LoginPage;
import org.openmrs.uitestframework.test.TestBase;
import org.openmrs.uitestframework.test.TestData.PatientInfo;

public class ActiveVisitsTest extends TestBase {

	public static final String PATIENT_IDENTIFIER_TYPE = "ZL EMR ID";
	
	private LoginPage loginPage;
	private HomePage appDashboard;
	private PatientDashboard patientDashboard;
	private PatientInfo testPatient;

	@Before
	public void before() {
		testPatient = createTestPatient(PATIENT_IDENTIFIER_TYPE);
		loginPage = new LoginPage(driver);
        appDashboard = new HomePage(driver);
        patientDashboard = new PatientDashboard(driver);

        assertPage(loginPage);
		loginPage.loginAsAdmin();
		assertPage(appDashboard);
	}
	
	@After
	public void after() throws Exception {
		deletePatientUuid(testPatient.uuid);
		dbUnitTearDown();
	}

	@Test
	public void shouldShowActiveVisitAfterStartVisit() throws Exception {
		System.out.println(testPatient.givenName + " " + testPatient.familyName + " " + testPatient.identifier + " " + testPatient.uuid);
		appDashboard.openActiveVisitsApp();
		assertFalse(pageContent().contains(testPatient.identifier));
		
		appDashboard.goToPatientPage(testPatient.id);
		assertPage(patientDashboard);
		patientDashboard.startVisit();
		
		appDashboard.openActiveVisitsApp();
		String contentText = pageContent();
		assertThat(contentText, containsString(testPatient.getName()));
		assertThat(contentText, containsString(testPatient.identifier));
	}

}
