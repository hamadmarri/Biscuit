package com.biscuit.commands.userStory;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Status;

public class ChangeStatusUserStory implements Command {

	UserStory us = null;
	Status state = null;


	public ChangeStatusUserStory(UserStory us, Status state) {
		super();
		this.us = us;
		this.state = state;
	}


	@Override
	public boolean execute() throws IOException {

		Status oldState = us.state;

		us.state = state;

		us.save();

		System.out.println(ColorCodes.GREEN + "State of user story " + us.title + " has been changed from " + oldState
				+ " to " + us.state + ColorCodes.RED);

		return true;
	}

}
