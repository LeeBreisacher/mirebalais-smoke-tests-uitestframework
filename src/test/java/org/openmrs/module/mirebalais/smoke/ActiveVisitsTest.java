package org.openmrs.module.mirebalais.smoke;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.uitestframework.test.TestData.PatientInfo;

public class ActiveVisitsTest extends BasicMirebalaisSmokeTest {

	private PatientInfo testPatient;

	@Before
	public void before() {
		testPatient = createTestPatient();
        login();
		assertPage(appDashboard);
	}
	
	@After
	public void after() throws Exception {
		deletePatient(testPatient);
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
