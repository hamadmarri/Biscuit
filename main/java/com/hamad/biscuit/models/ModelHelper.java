/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModelHelper {

//	static Gson gson = new Gson();
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


	static public Root loadRoot(String name) {
		try {
			FileReader fr = new FileReader(Config.projectsDir + "/" + name + ".json");
			Root r = gson.fromJson(fr, Root.class);
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

}
