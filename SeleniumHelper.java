import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumHelper {

    private static long webdriver_wait_millis = 20 * 1000;

    /** false if indicator never changes. wait and then  throw exception if clickee doesn't exist */
    static boolean click_element_and_wait_for_indicator_text_to_change(WebDriver driver, String clickee_xpath, String indicator_xpath) throws InterruptedException, WebpageNeverChangedException, Exception {

	WebElement clickee = waitForElementByXpath(driver, clickee_xpath);

	return click_element_and_wait_for_indicator_text_to_change(driver, clickee, indicator_xpath);
    }

    /** doesnt wait for clickee.  returns false if not exist */
    static boolean click_element_quietly_and_wait_for_indicator_text_to_change(WebDriver driver, String clickee_xpath, String indicator_xpath) throws InterruptedException {

		WebElement clickee;
		try {
			clickee = driver.findElement(By.xpath(clickee_xpath));
		} catch (Exception e) {
			return false;
		}
		return click_element_and_wait_for_indicator_text_to_change(driver, clickee, indicator_xpath);
    }

    /** false if indicator never changes.  it's okay if indicator doesn't exist before clicking clickee*/
    static boolean click_element_and_wait_for_indicator_text_to_change(WebDriver driver, WebElement clickee, String indicator_xpath) throws InterruptedException {
	

		String indicator_initial_value = "aw3fawe3fa3wfa2w3fa3w43tws4tgwsg";

		try {
			indicator_initial_value = driver.findElement(By.xpath(indicator_xpath)).getText();		//it's okay if indicator doesn't exist before clicking clickee
		} catch (Exception e) {
		}

		clickee.click();

		long napLength = 0;
		WebElement indicator = null;
		boolean couldntFindIndicator = false;
		do {
			try {
				indicator = driver.findElement(By.xpath(indicator_xpath));
			} catch (Exception e) {
				couldntFindIndicator = true;
			}
			if (indicator == null || couldntFindIndicator || indicator_initial_value.equals(driver.findElement(By.xpath(indicator_xpath)).getText())) {	// first two are redundant
				Thread.sleep(100);
				napLength += 100;
			} else
				break;
		} while (napLength < webdriver_wait_millis);


		return indicator == null || !indicator_initial_value.equals(driver.findElement(By.xpath(indicator_xpath)).getText());
    }

    public static WebElement waitForElementByXpath(WebDriver driver, String element_xpath) throws InterruptedException, Exception {
		Exception e1;
		long napLength = 0;
		do {
			try {
				WebElement element = driver.findElement(By.xpath(element_xpath));
				return element;
			} catch (Exception e) {
				Thread.sleep(100);
				napLength += 100;
				e1 = e;
			}
		} while (napLength < webdriver_wait_millis);
		System.out.println("\n\n\n\n\n\n" + driver.getPageSource());
		System.exit(0);
		throw e1;
    }

    /** false if indicator never changes.  it's okay if indicator doesn't exist before clicking clickee*/
    public static boolean executeJavascript_and_wait_for_indicator_text_to_change(WebDriver driver, String js, String indicator_xpath) throws InterruptedException {

		String indicator_initial_value = "aw3fawe3fa3wfa2w3fa3w43tws4tgwsg";

		try {
			indicator_initial_value = driver.findElement(By.xpath(indicator_xpath)).getText();		//it's okay if indicator doesn't exist before clicking clickee
		} catch (Exception e) {
		}

	//	System.out.println("executing: " + js);

		((JavascriptExecutor)driver).executeScript(js);

		long napLength = 0;
		WebElement indicator = null;
		boolean couldntFindIndicator = false;
		do {
			try {
			try {
				indicator = driver.findElement(By.xpath(indicator_xpath));
			} catch (Exception e) {
				couldntFindIndicator = true;
			}
			if (couldntFindIndicator || indicator_initial_value.equals(driver.findElement(By.xpath(indicator_xpath)).getText())) {	// first two are redundant
				Thread.sleep(100);
				napLength += 100;
			} else
				break;
			} catch (org.openqa.selenium.StaleElementReferenceException e) {	//thrown by this, i think cos page changes in the middle of this command: indicator_initial_value.equals(driver.findElement(By.xpath(indicator_xpath)).getText())
			}
		} while (napLength < webdriver_wait_millis);


		return indicator == null || !indicator_initial_value.equals(driver.findElement(By.xpath(indicator_xpath)).getText());


    }

    public static class WebpageNeverChangedException extends Exception {
		public WebpageNeverChangedException() {
		}
    }
}
