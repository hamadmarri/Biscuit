/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.hamad.biscuit.commands.userstory.ListUserStories;
import com.hamad.biscuit.models.Project;
import com.hamad.biscuit.models.Root;
import com.hamad.biscuit.views.BacklogView;
import com.hamad.biscuit.views.Dashboard;
import com.hamad.biscuit.views.ProjectView;

public class App {

	public static void main(String[] args) {
		initialize();
	}


	private static void initialize() {

		Root.setInstance(Root.load());

		if (Root.getInstance() == null) {
			Root.setInstance(new Root());
		}

		Root.getInstance().save();

//		test();

		Dashboard db = new Dashboard();
		db.view();

	}


	private static void test() {
		Calendar cal = new GregorianCalendar();
		int startingYear = cal.get(Calendar.YEAR) - 2;
		int endingYear = startingYear + 4;
		cal.set(startingYear, 0, 1);

		while (cal.get(Calendar.YEAR) <= endingYear) {
			System.out.println(cal.getTime());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

	}

}
