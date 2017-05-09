/*
	Author: Hamad Al Marri;
 */

package com.biscuit;

//import java.util.Calendar;
//import java.util.GregorianCalendar;

import com.biscuit.models.Dashboard;
import com.biscuit.views.DashboardView;

public class App {

	public static void main(String[] args) {
		initialize();
	}


	private static void initialize() {

		Dashboard.setInstance(Dashboard.load());

		if (Dashboard.getInstance() == null) {
			Dashboard.setInstance(new Dashboard());
		}

		Dashboard.getInstance().save();

//		test();

		DashboardView dbv = new DashboardView();
		dbv.view();

	}


//	private static void test() {
//		Calendar cal = new GregorianCalendar();
//		int startingYear = cal.get(Calendar.YEAR) - 2;
//		int endingYear = startingYear + 4;
//		cal.set(startingYear, 0, 1);
//
//		while (cal.get(Calendar.YEAR) <= endingYear) {
//			System.out.println(cal.getTime());
//			cal.add(Calendar.DAY_OF_MONTH, 1);
//		}
//
//	}

}
