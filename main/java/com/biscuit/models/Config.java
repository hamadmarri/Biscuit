package com.biscuit.models;

import java.io.File;

public class Config {

	private static String homeDir;
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
