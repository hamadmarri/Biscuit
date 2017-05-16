/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModelHelper {

	// static Gson gson = new Gson();
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();


	static public void save(Object o, String name) {
		try {
			FileWriter fw = new FileWriter(Config.projectsDir + "/" + name + ".json");
			gson.toJson(o, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	static public Dashboard loadDashboard(String name) {
		try {
			FileReader fr = new FileReader(Config.projectsDir + "/" + name + ".json");
			Dashboard r = gson.fromJson(fr, Dashboard.class);
			fr.close();
			return r;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	static public Project loadProject(String name) {
		try {
			FileReader fr = new FileReader(Config.projectsDir + "/" + name + ".json");
			Project p = gson.fromJson(fr, Project.class);
			fr.close();
			return p;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


	public static void delete(String name) {
		try {
			File file = new File(Config.projectsDir + "/" + name + ".json");

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void rename(String oldName, String newName) throws IOException {
		File file = new File(Config.projectsDir + "/" + oldName + ".json");
		File file2 = new File(Config.projectsDir + "/" + newName + ".json");

		if (file2.exists())
			throw new java.io.IOException("file exists");

		// Rename file (or directory)
		file.renameTo(file2);
	}
}
