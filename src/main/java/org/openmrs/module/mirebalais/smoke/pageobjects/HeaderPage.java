package org.openmrs.module.mirebalais.smoke.pageobjects;

import org.openmrs.uitestframework.page.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderPage extends AbstractBasePage {

	static final By LOCATION_MENU = By.className("icon-map-marker");
	static final By LOCATIONS = By.cssSelector("ul.select li");
	public static final By LOCATION = By.cssSelector("li.change-location span");

	public HeaderPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String expectedUrlPath() {
		return null;	// does not apply to this "fragment"
	}

    public WebElement fourthLocation() {
        return findElements(LOCATIONS).get(3);
    }

    public String fourthLocationText() {
        return fourthLocation().getAttribute("textContent");
    }

	public void changeLocationTo(WebElement location) throws Exception {
		clickOnLocationMenu();
		location.click();
    }

    private void clickOnLocationMenu() {
		findElement(LOCATION_MENU).click();
	}

}
