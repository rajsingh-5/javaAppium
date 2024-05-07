package utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

/**
 * 
 * @author Biswajit
 *
 */
public class WebDriverUtility {
	public WebDriverWait wait;

	public WebDriverUtility(AndroidDriver<AndroidElement> driver) {
		this.wait = new WebDriverWait(driver, 30);
	}

	public void implicitWait(WebDriver driver, int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public void explicitWait(WebElement element, int waitTime) {

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElemnetToBeVisible(WebElement elemnet) {
		wait.until(ExpectedConditions.visibilityOf(elemnet));
	}

	public void waitForElemnetToBeVisible(AndroidElement elemnet) {
		wait.until(ExpectedConditions.visibilityOf(elemnet));
	}

	public void waitForElemnetToBeClickable(WebElement elemnet) {
		wait.until(ExpectedConditions.elementToBeClickable(elemnet));
	}

	public void waitAndClick(WebElement element) throws InterruptedException {
		int count = 0;
		while (count < 20) {
			try {
				element.click();
				break;
			} catch (Throwable e) {
				Thread.sleep(1000);
				count++;
			}
		}
	}

	public void takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/" + screenshotName + ".PNG");
		FileUtils.copyFile(src, dest);
	}

	public void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void tapInMiddle(AppiumDriver<WebElement> driver, WebElement element) {
		Point location = element.getLocation();
		int x = location.getX();
		int y = location.getY();
		Dimension size = element.getSize();
		int height = size.getHeight();
		int width = size.getWidth();
		TouchAction action = new TouchAction(driver);
		action.tap(PointOption.point(x + width / 2, y - height / 2)).release().perform();
	}

}
