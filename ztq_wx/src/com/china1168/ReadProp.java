package com.china1168;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProp {

	
		private static Properties config = null;

		static {
			InputStream in = ReadProp.class.getClassLoader().getResourceAsStream(
		    "config.properties");
			/*InputStream in = ReadProp.class.getClassLoader()
					.getResourceAsStream("config.properties");*/
			config = new Properties();
			try {
				config.load(in);
				in.close();
			} catch (IOException e) {
				System.out.println("No AreaPhone.properties defined error");
			}
		}

		// 根据key读取value
		public static String readValue(String key) {
			// Properties props = new Properties();
			try {
				String value = config.getProperty(key);
				return value;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ConfigInfoError" + e.toString());
				return null;
			}
		}
		
		
}
