package Tests;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Context.TestContext;
import Managers.ExtentManager;
import Managers.FileReaderManager;
import ObjectClasses.Movie;
import PageObjects.HomePage;
import PageObjects.TopRatedMoviesPage;

public abstract class BaseTest {
	
	TestContext testcontext=new TestContext();
	WebDriver driver=testcontext.getWebDriverManager().getDriver();
	
	private static ExtentReports extent;

	
	HomePage homePage;
	TopRatedMoviesPage topRatedMoviesPage;
	List<Movie>MasterData;
	
	public void init()
	{
		homePage=testcontext.getPageObjectManager().getHomePage();
		  homePage.goTo(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		  homePage.isAt(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
		topRatedMoviesPage=homePage.goToTopRatedMovies();
		topRatedMoviesPage.isAt();
		MasterData=topRatedMoviesPage.createListofMovies();
	}
	
	
	
	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.GetExtent();
	}
	
    @BeforeClass
    public synchronized void beforeClass() {
        ExtentTest parent = extent.createTest(getClass().getName().substring(getClass().getName().indexOf(".")+1)).assignAuthor("Manoj Gupta");
        ExtentManager.setParentTest(parent);
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method) {
    	if(homePage==null || topRatedMoviesPage==null)
    	{
    		init();
    	}
    	
    	Test test = method.getAnnotation(Test.class);
    	String TestCaseName=method.getName();
    	System.out.println(test.description());
        ExtentTest child = ExtentManager.getParentTest().get().createNode("<b>"+TestCaseName+"</b>", test.description());
        ExtentManager.setCurrentTest(child);
        
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE)
        { String message=result.getThrowable().getMessage();
        if(!message.equals(""))
        {
        	ExtentManager.getCurrentTest().get().fail(message);
        }
        else
        	ExtentManager.getCurrentTest().get().fail(result.getThrowable());
        
        }
        else if (result.getStatus() == ITestResult.SKIP)
        	ExtentManager.getCurrentTest().get().skip(result.getThrowable());
        else
        	ExtentManager.getCurrentTest().get().pass("Test passed");

        extent.flush();
    }
    
    @AfterSuite
	public void afterSuite() {
    	
   if(driver!=null)
	   driver.quit();
    }
}

