package com.ibm.excerise.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() throws Exception {
		FileInputStream fis = new FileInputStream(".\\config.properties");
		prop = new Properties();
		prop.load(fis);
	}

	public String getApp() {
		return prop.getProperty("nop_appURL");
	}

	public String getBrowser() {
		return prop.getProperty("browser");
	}


}
