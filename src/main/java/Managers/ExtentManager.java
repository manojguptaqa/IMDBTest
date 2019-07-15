package Managers;

import java.io.File;
import java.util.Date;
import com.aventstack.extentreports.AnalysisStrategy;

//

//http://relevantcodes.com/Tools/ExtentReports2/javadoc/index.html?com/relevantcodes/extentreports/ExtentReports.html

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;

	private static ThreadLocal<ExtentTest> parentTest;
	private static ThreadLocal<ExtentTest> currentTest;

	public static ExtentReports GetExtent() {
		if (extent != null)
			return extent; // avoid creating new instance of html file
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {

		Date d = new Date();
		String fileName = "TestReport" + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

		File file = new File(System.getProperty("user.dir") + "/TestReports/" + fileName);
		htmlReporter = new ExtentHtmlReporter(file);

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setCSS(".colllapsible {\r\n" + "  background-color: #039be5;\r\n" + "  color: white;\r\n"
				+ "  cursor: pointer;\r\n" + "  padding: 18px;\r\n" + "  width: 100%;\r\n" + "  border: none;\r\n"
				+ "  text-align: center;\r\n" + "  outline: none;\r\n" + "  font-size: 15px;\r\n" + "}\r\n" + "\r\n" +
				// ".active, .colllapsible:hover {\r\n" +
				// " background-color: #ffffff;\r\n" +
				// "}\r\n" +
				// "\r\n" +
				".content {\r\n" + "  padding: 0 18px;\r\n" + "  display: none;\r\n" + "  overflow: hidden;\r\n"
				+ "  background-color: #f1f1f1;\r\n" + "}");

		htmlReporter.config().setJS("\r\n" + "function myFunction() \r\n" + "                {\r\n"
				+ "var coll = document.getElementsByClassName(\"colllapsible\");\r\n" + "var i;\r\n" + "\r\n"
				+ "for (i = 0; i < coll.length; i++) {\r\n" + "  coll[i].addEventListener(\"click\", function() {\r\n"
				+ "    this.classList.toggle(\"active\");\r\n" + "    var content = this.nextElementSibling;\r\n"
				+ "    if (content.style.display === \"block\") {\r\n" + "      content.style.display = \"none\";\r\n"
				+ "    } else {\r\n" + "      content.style.display = \"block\";\r\n" + "    }\r\n" + "  });\r\n"
				+ "}\r\n" + "}");

		File XML_File = new File("configs//Extent-Config.xml");
		htmlReporter.loadXMLConfig(XML_File);

		htmlReporter.config().setChartVisibilityOnOpen(false);
		htmlReporter.config().setDocumentTitle("IMDB Tests");
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setReportName("IMDB Test Automation Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");

		extent.setSystemInfo("Author", "Manoj Gupta");
		extent.setSystemInfo("Application URL", FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

		return htmlReporter;
	}

	public static ThreadLocal<ExtentTest> getParentTest() {
		if (parentTest == null)
			parentTest = new ThreadLocal<ExtentTest>();

		return parentTest;

	}

	public static ThreadLocal<ExtentTest> getCurrentTest() {
		if (currentTest == null)
			currentTest = new ThreadLocal<ExtentTest>();

		return currentTest;

	}

	public static void setParentTest(ExtentTest test) {
		getParentTest();
		parentTest.set(test);

	}

	public static void setCurrentTest(ExtentTest test) {
		getCurrentTest();
		currentTest.set(test);

	}

}