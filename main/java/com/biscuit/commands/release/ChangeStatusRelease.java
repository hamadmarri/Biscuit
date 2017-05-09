package com.biscuit.commands.release;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Release;
import com.biscuit.models.enums.State;

public class ChangeStatusRelease implements Command {
	Release r = null;
	State state = null;


	public ChangeStatusRelease(Release t, State state) {
		super();
		this.r = t;
		this.state = state;
	}


	@Override
	public boolean execute() throws IOException {

		State oldState = r.state;

		r.state = state;

		r.save();

		System.out.println(ColorCodes.GREEN + "State of release " + r.name + " has been changed from " + oldState
				+ " to " + r.state + ColorCodes.RED);

		return true;
	}
}
