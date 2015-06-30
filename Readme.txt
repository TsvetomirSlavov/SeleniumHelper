SeleniumHelper.java contains a number of methods that help the WebDriver act robustly, based on a page's current condition.

It contains the following tools:


    /** false if indicator never changes. wait and then  throw exception if clickee doesn't exist */
    static boolean click_element_and_wait_for_indicator_text_to_change(WebDriver driver, String clickee_xpath, String indicator_xpath) throws InterruptedException, WebpageNeverChangedException, Exception 

	
    /** doesnt wait for clickee.  returns false if not exist */
    static boolean click_element_quietly_and_wait_for_indicator_text_to_change(WebDriver driver, String clickee_xpath, String indicator_xpath) throws InterruptedException 

	
    /** false if indicator never changes.  it's okay if indicator doesn't exist before clicking clickee*/
    static boolean click_element_and_wait_for_indicator_text_to_change(WebDriver driver, WebElement clickee, String indicator_xpath) throws InterruptedException 

	
    public static WebElement waitForElementByXpath(WebDriver driver, String element_xpath) throws InterruptedException, Exception 

	
    /** false if indicator never changes.  it's okay if indicator doesn't exist before clicking clickee*/
    public static boolean executeJavascript_and_wait_for_indicator_text_to_change(WebDriver driver, String js, String indicator_xpath) throws InterruptedException 

	
	