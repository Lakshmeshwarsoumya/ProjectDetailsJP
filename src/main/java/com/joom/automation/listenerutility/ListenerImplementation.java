package com.joom.automation.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.joom.automation.WebDriverUtility.UtilityClassObject;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.JavaUtility;


public class ListenerImplementation implements ITestListener, ISuiteListener {
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		// spark report configuration
		String time = new Date().toString().replace(" ", " _").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport./report"+time+".html");
		spark.config().setDocumentTitle("CRM Test SUite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);

		// add environment information
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WIndows 10");
		report.setSystemInfo("BROWSER", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("====== ======>" + result.getMethod().getMethodName() + ">=========");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "===> Started");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("====== ======>" + result.getMethod().getMethodName() + ">====END=====");

		test.log(Status.PASS, result.getMethod().getMethodName() + "===> Completed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		JavaUtility jlib = new JavaUtility();
		String timeStamp = jlib.captureTimeStamp();

		// step-1 create an object to EventFiringWebDriver
		
		TakesScreenshot ts = (TakesScreenshot) BaseClassForAdmin.sdriver;
		String temp = ts.getScreenshotAs(OutputType.BASE64);

		String time = new Date().toString().replace(" ", " _").replace(":", "_");

		UtilityClassObject.getTest().log(Status.FAIL, result.getThrowable());
		test.addScreenCaptureFromBase64String(temp, testName + "_" + time);
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===> Failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, result.getMethod().getMethodName() + "===> Skipped");
	}

}
