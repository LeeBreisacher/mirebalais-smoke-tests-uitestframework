package org.openmrs.module.mirebalais.smoke;

import org.junit.Test;
import org.openmrs.module.mirebalais.smoke.pageobjects.HeaderPage;
import org.openmrs.uitestframework.test.TestBase;

public class HeaderTest extends TestBase {

	@Test
	public void shouldChangeLocation() throws Exception {
		login();
		
		HeaderPage headerPage = new HeaderPage(driver);
		
		headerPage.changeLocationTo(headerPage.fourthLocation());
		
		headerPage.waitForTextToBePresentInElement(HeaderPage.LOCATION, headerPage.fourthLocationText());
	}
	
}
