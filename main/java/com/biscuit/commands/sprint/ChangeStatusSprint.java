package com.biscuit.commands.sprint;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Sprint;
import com.biscuit.models.enums.Status;

public class ChangeStatusSprint implements Command {
	Sprint s = null;
	Status state = null;


	public ChangeStatusSprint(Sprint s, Status state) {
		super();
		this.s = s;
		this.state = state;
	}


	@Override
	public boolean execute() throws IOException {

		Status oldState = s.state;

		s.state = state;

		s.save();

		System.out.println(ColorCodes.GREEN + "State of sprint " + s.name + " has been changed from " + oldState
				+ " to " + s.state + ColorCodes.RED);

		return true;
	}
}
