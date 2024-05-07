package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Utilitycommon {

	Boolean status = true;
	public static String Otp1;

	public void takeScreenShot(WebDriver driver, String methName) {

		String destDir = "screenshots";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		new File(destDir).mkdirs();
		String destFile = methName + dateFormat.format(new Date()) + ".png";

		try {

			FileUtils.copyFile(scrFile, new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeScreenShot1(WebDriver driver, String methName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			File file = new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/" + methName);
			if (file.exists()) {

			} else {
				file.mkdir();
			}
			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File("/Users/uat_artp/Desktop/ScreenShot/SbiYono/" + methName);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void doubleTap(WebDriver driver, Object X, Object Y) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
		}
		Map<String, Object> args = new HashMap<String, Object>();

		args.put("x", X);
		args.put("y", Y);
		((RemoteWebDriver) driver).executeScript("mobile: doubleTap", args);
		System.out.println("Tap");

	}

	public void scrollWithUiAutomator(String attributeName, String attributeValue) {
		BaseTest.driver.getContext();
		((AndroidDriver) BaseTest.driver).findElementByAndroidUIAutomator("new UIScrollable(new UiSelector())."
				+ "scrollIntoView(" + attributeName + "(\"" + attributeValue + "\"))");

	}

	public void swipe(int X1, int Y1, int X2, int Y2) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		new TouchAction(BaseTest.driver).press(PointOption.point(X1, Y1))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(X2, Y2)).release()
				.perform();
		System.out.println("Swipe is performed");
	}

	public void tap(AppiumDriver driver, int xOffset, int yOffset) {

		new TouchAction(driver).press(PointOption.point(xOffset, yOffset))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).release().perform();
		LogManager.getLogger().info("Tap is Performed at location " + xOffset + " and " + yOffset);
	}

	public void tap(int xOffset, int yOffset, String Button) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		new TouchAction(BaseTest.driver).press(PointOption.point(xOffset, yOffset))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).release().perform();
		LogManager.getLogger().info(Button + " is clicked");
	}

	public void tap(int xOffset, int yOffset, String Button, int waitTimeInSecond) {
		try {
			Thread.sleep(waitTimeInSecond * 1000);
		} catch (InterruptedException e) {
		}
		new TouchAction(BaseTest.driver).press(PointOption.point(xOffset, yOffset))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
		LogManager.getLogger().info(Button + " is clicked");
	}

	public void tap(int xOffset, int yOffset, int waitTimeInSecond) {
		try {
			Thread.sleep(waitTimeInSecond * 1000);
		} catch (InterruptedException e) {
		}
		new TouchAction(BaseTest.driver).press(PointOption.point(xOffset, yOffset))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();
		LogManager.getLogger().info("Tap is Performed at location " + xOffset + " and " + yOffset);
	}

	public void activateMessageApp() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BaseTest.driver.activateApp("com.samsung.android.messaging");

	}

	public void activateSbiYonoLiteApplication() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		BaseTest.driver.activateApp("com.sbi.sbunified");

	}

	public void tapByCoordinates(WebDriver driver, int X1, int Y1, int X2, int Y2) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		Dimension d = driver.manage().window().getSize();
		X1 = (int) (d.getWidth() * 0.5D);
		Y1 = (int) (d.getHeight() * 0.6D);

		X2 = (int) (d.getWidth() * 0.5D);
		Y2 = (int) (d.getHeight() * 0.2D);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("duration", 3);
		args.put("fromX", X1);
		args.put("fromY", Y1);
		args.put("toX", X2);
		args.put("toY", Y2);
		((JavascriptExecutor) driver).executeScript("mobile: dragFromToForDuration", args);

	}

	public void swipe1(WebDriver driver) {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);

	}

	public void swipe2(WebDriver driver, String elementName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<Object, Object> scrollObject = new HashMap<Object, Object>();
		scrollObject.put("direction", "down");
		scrollObject.put("name", elementName);
		js.executeScript("mobile: scroll", scrollObject);

	}
}
