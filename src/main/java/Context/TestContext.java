package Context;

import Enum.DriverType;
import Managers.DriverManager;
import Managers.DriverManagerFactory;
import Managers.PageObjectManager;

public class TestContext {
	 private DriverManager webDriverManager;
	 private PageObjectManager pageObjectManager;
	 
	 public TestContext(){
	 webDriverManager = DriverManagerFactory.getManager(DriverType.CHROME);
	 pageObjectManager = new PageObjectManager(webDriverManager.getDriver());

	 }
	 
	 public DriverManager getWebDriverManager() {
	 return webDriverManager;
	 }
	 
	 public PageObjectManager getPageObjectManager() {
	 return pageObjectManager;
	 }
	 

	 
	}