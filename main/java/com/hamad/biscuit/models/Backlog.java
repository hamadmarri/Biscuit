/*
	Author: Hamad Al Marri;
 */

package com.hamad.biscuit.models;

import java.util.ArrayList;
import java.util.List;

public class Backlog {

	public List<UserStory> userStories = new ArrayList<UserStory>();


	public void addUserStory(UserStory userStory) {
		this.userStories.add(userStory);
	}
}
