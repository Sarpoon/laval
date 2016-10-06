package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFinder {

	Properties properties = null;
	InputStream input = null;

	private PropertiesFinder() {
	}

	private static PropertiesFinder INSTANCE = new PropertiesFinder();

	public static PropertiesFinder getInstance() {
		return INSTANCE;
	}

	private void loadProperties() {
		try {
			input = new FileInputStream("ressources/settings.properties");
			properties = new Properties();
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Object getProperty(String propertyKey) {
		if (properties == null) {
			loadProperties();
		}
		Object property = properties.getProperty(propertyKey);
		return property;
	}
}
