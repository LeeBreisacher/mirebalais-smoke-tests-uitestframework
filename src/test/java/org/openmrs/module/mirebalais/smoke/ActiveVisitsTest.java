package org.openmrs.module.mirebalais.smoke;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.mirebalais.smoke.pageobjects.HomePage;
import org.openmrs.uitestframework.page.LoginPage;
import org.openmrs.uitestframework.test.TestBase;

public class ActiveVisitsTest extends TestBase {

	private LoginPage loginPage;
	private HomePage homePage;

	@Before
	public void before() {
		loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        assertPage(loginPage);
		loginPage.loginAsAdmin();
		assertPage(homePage);
	}

	@Test
	public void shouldShowActiveVisitAfterStartVisit() throws Exception {

	}

}
