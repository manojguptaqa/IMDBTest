package Utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import Context.TestContext;
import Enum.ScreenshotType;
import Managers.ExtentManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ExtentReportsUtility {
	
	static TestContext testcontext=new TestContext();
	static WebDriver driver=testcontext.getWebDriverManager().getDriver();
	public ExtentReports report = ExtentManager.GetExtent();
	//public static ExtentTest Test=ExtentManager.getCurrentTest().get();
	
	public static synchronized String TakeScreenShot(WebDriver driver) {
		String fileName = (new Date()).toString().replace(" ", "_").replace(":", "-").trim() + ".png";
		String destinationFilePath = System.getProperty("user.dir") + "/ScreenShots/" +fileName;
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination=new File(destinationFilePath);
			FileUtils.copyFile(source, destination);
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
		System.out.println("Screen shot taken");
		//Test.log(Status.INFO, "Screenshot taken");
		return destinationFilePath;
	}
	
	
	public static String TakeFullPageScreenshot(WebDriver driver)
	{
		String fileName = (new Date()).toString().replace(" ", "_").replace(":", "-").trim() + ".png";
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	       try {
			ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") +"/ScreenShots/"+fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return System.getProperty("user.dir") +"/ScreenShots/"+fileName;
	}
	
	public static void AttachScreenShotToReport(ScreenshotType Type) 
	{

		//String ScreenShotName=new Date().toString().replaceAll(" ", "_").replaceAll(":", "_");
		String screenshot_path="";
		switch (Type) {
		case fullPage:
			screenshot_path = TakeFullPageScreenshot(driver);
			break;
		case partial:
			screenshot_path = TakeScreenShot(driver);
			break;
		}

		try {
			
			//get curent test
			ExtentManager.getCurrentTest().get().info("<a href=\""+screenshot_path+"\" target=\"_blank\">Screenshot</a>", MediaEntityBuilder.createScreenCaptureFromPath(screenshot_path).build());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void printJson(String JsonString,String Message)
	{

		String jsonReport = "";
		if (JsonString.contains("\n")) {
			jsonReport = JsonString.replaceAll("\n", "</br>");

		}

		// Test.log(Status.INFO, "<B>"+Message +":</B></br>" + jsonReport);

		String html = "<button onclick=\"myFunction()\" class=\"colllapsible\" title=\"Double click to expand/collapse\">"
				+ Message + "</button><div class=\"content\"><p>" + jsonReport + "</p></div>";

		ExtentManager.getCurrentTest().get().log(Status.INFO, html);

	}
	
	public static void reportPass(String msg) {
		ExtentManager.getCurrentTest().get().log(Status.PASS, msg);

	}
	
//----------------------------------------------------------------------------------------------------	

	public static void reportFailure(String msg) {
		ExtentManager.getCurrentTest().get().log(Status.FAIL, msg);

		Assert.fail(msg);
	}
	
	public static void reportStep(String msg) {
		ExtentManager.getCurrentTest().get().log(Status.INFO, msg);

	}

}

