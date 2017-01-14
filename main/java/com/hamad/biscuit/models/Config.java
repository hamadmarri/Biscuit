package com.hamad.biscuit.models;

import java.io.File;
import java.text.SimpleDateFormat;

public class Config {

	public static String homeDir;
	public static String projectsDir;

	static {
		homeDir = System.getProperty("user.home");
		projectsDir = homeDir + "/biscuit";
		File f = new File(projectsDir);

		if (!f.exists()) {
			f.mkdir();
		}
	}

}
