/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit;

import com.hamad.biscuit.models.Root;
import com.hamad.biscuit.views.Dashboard;

public class App {

	public static void main(String[] args) {
		initialize();
		test1();
	}


	private static void initialize() {

		Root.setInstance(Root.load());

		if (Root.getInstance() == null) {
			Root.setInstance(new Root());
		}

		Root.getInstance().save();
	}


	private static void test1() {
		Dashboard db = new Dashboard();
		db.view();
	}

}
