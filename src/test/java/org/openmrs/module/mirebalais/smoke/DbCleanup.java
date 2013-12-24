package org.openmrs.module.mirebalais.smoke;

import java.sql.SQLException;

import org.dbunit.dataset.DataSetException;
import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.uitestframework.test.TestData.PatientInfo;
import org.openmrs.uitestframework.test.TestData.UserInfo;

public class DbCleanup extends BasicMirebalaisSmokeTest {

	@Test
	@Ignore
	public void cleanupPatient() throws DataSetException, SQLException, Exception {
		PatientInfo testPatient = new PatientInfo();
		testPatient.id = "10879";
		deletePatient(testPatient);
	}

	@Test
	public void cleanupUser() throws DataSetException, SQLException, Exception {
		UserInfo user = new UserInfo();
		user.userId = "2368";
		user.id = "10879";
		deleteUser(user);
	}
}
