package utils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.remote.SessionId;

public class FileUtility {

	public String getPropertyData(String key) {
		Properties p = null;
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/testdata.properties");
			p = new Properties();
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String data = p.getProperty(key);
		return data;
	}

	public String getPropertyData(String key, String propertyFileName) throws IOException {
		FileInputStream fis = new FileInputStream("./data/" + propertyFileName + ".properties");
		Properties p = new Properties();
		p.load(fis);
		String data = p.getProperty(key);
		return data;
	}
}
