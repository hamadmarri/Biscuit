package com.biscuit.commands.task;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Task;
import com.biscuit.models.enums.State;

public class ChangeStatusTask implements Command {

	Task t = null;
	State state = null;


	public ChangeStatusTask(Task t, State state) {
		super();
		this.t = t;
		this.state = state;
	}


	@Override
	public boolean execute() throws IOException {

		State oldState = t.state;

		t.state = state;

		t.save();

		System.out.println(ColorCodes.GREEN + "State of task " + t.title + " has been changed from " + oldState + " to "
				+ t.state + ColorCodes.RED);

		return true;
	}

}
