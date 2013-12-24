package org.openmrs.module.mirebalais.smoke;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openmrs.uitestframework.test.TestData;
import org.openmrs.uitestframework.test.TestData.PatientInfo;
import org.openmrs.uitestframework.test.TestData.RoleInfo;
import org.openmrs.uitestframework.test.TestData.UserInfo;

public class ConsultNoteTest extends BasicMirebalaisSmokeTest {

    private static final String PRIMARY_DIAGNOSIS = "IGU";
    private static final String EDITED_PRIMARY_DIAGNOSIS = "Asthme";

    private static UserInfo clinicalUser;
	private static RoleInfo clinicalRole;
    
	private PatientInfo testPatient;

	@BeforeClass
	public static void loginAsClinicalUser() throws Exception {
		clinicalRole = findOrCreateRole("Application Role: clinical");
		clinicalUser = createUser("smoke-test-clinical-" + TestData.randomSuffix(), clinicalRole);
        login(clinicalUser);
    }

	@AfterClass
	public static void cleanup() throws Exception {
		deleteUser(clinicalUser);
		deleteRole(clinicalRole);
	}
	
	@Before
	public void setUp() throws Exception {
		testPatient = createTestPatient();
		System.out.println("Test Patient: " + testPatient);
		
		appDashboard.goToPatientPage(testPatient.id);
		patientDashboard.startVisit();
	}

	@After
	public void after() throws Exception {
		deletePatient(testPatient);
	}
	
	@Test
	public void testSetup() {
		System.out.println("clinicalRole: " + clinicalRole);
		System.out.println("clinicalUser: " + clinicalUser);
		System.out.println("testPatient: " + testPatient);
	}

/*
	@BeforeClass
	public static void prepare() throws Exception {
        logInAsClinicalUser();
    }
	
	@Before
	public void setUp() throws Exception {
		Patient testPatient = PatientDatabaseHandler.insertNewTestPatient();
		initBasicPageObjects();
		
		appDashboard.goToPatientPage(testPatient.getId());
		patientDashboard.startVisit();
	}
	
	@Test
	public void addConsultationToAVisitWithoutCheckin() throws Exception {
		patientDashboard.addConsultNoteWithDischarge(PRIMARY_DIAGNOSIS);
		
		assertThat(patientDashboard.countEncountersOfType(PatientDashboard.CONSULTATION), is(1));
	}
	
	@Test
	public void addConsultationNoteWithDeathAsDispositionClosesVisit() throws Exception {
		patientDashboard.addConsultNoteWithDeath(PRIMARY_DIAGNOSIS);
		
		assertThat(patientDashboard.isDead(), is(true));
		assertThat(patientDashboard.hasActiveVisit(), is(false));
		assertThat(patientDashboard.startVisitButtonIsVisible(), is(false));
	}


    @Test
    public void editConsultationNote() throws Exception {

        patientDashboard.addConsultNoteWithDischarge(PRIMARY_DIAGNOSIS);
        patientDashboard.editExistingConsultNote(EDITED_PRIMARY_DIAGNOSIS);

        assertThat(patientDashboard.countEncountersOfType(PatientDashboard.CONSULTATION), is(1));

        patientDashboard.viewConsultationDetails();
        assertThat(patientDashboard.containsText(EDITED_PRIMARY_DIAGNOSIS), is(true));
        assertThat(patientDashboard.containsText(PRIMARY_DIAGNOSIS), is(false));
    }

	@Test
	public void addEDNote() throws Exception {
		patientDashboard.addEmergencyDepartmentNote(PRIMARY_DIAGNOSIS);
		
		assertThat(patientDashboard.countEncountersOfType(PatientDashboard.CONSULTATION), is(1));
	}

    @Test
    public void editEDNote() throws Exception {

        patientDashboard.addEmergencyDepartmentNote(PRIMARY_DIAGNOSIS);
        patientDashboard.editExistingEDNote(EDITED_PRIMARY_DIAGNOSIS);

        assertThat(patientDashboard.countEncountersOfType(PatientDashboard.CONSULTATION), is(1));

        patientDashboard.viewConsultationDetails();
        assertThat(patientDashboard.containsText(EDITED_PRIMARY_DIAGNOSIS), is(true));
        assertThat(patientDashboard.containsText(PRIMARY_DIAGNOSIS), is(false));
    }
 * */

}
