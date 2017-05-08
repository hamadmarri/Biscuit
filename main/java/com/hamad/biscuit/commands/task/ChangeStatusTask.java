package com.hamad.biscuit.commands.task;

import java.io.IOException;

import com.hamad.biscuit.ColorCodes;
import com.hamad.biscuit.commands.Command;
import com.hamad.biscuit.models.Task;
import com.hamad.biscuit.models.enums.State;

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
