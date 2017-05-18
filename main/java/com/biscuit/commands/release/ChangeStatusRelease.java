package com.biscuit.commands.release;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Release;
import com.biscuit.models.enums.Status;

public class ChangeStatusRelease implements Command {
	Release r = null;
	Status state = null;


	public ChangeStatusRelease(Release r, Status state) {
		super();
		this.r = r;
		this.state = state;
	}


	@Override
	public boolean execute() throws IOException {

		Status oldState = r.state;

		r.state = state;

		r.save();

		System.out.println(ColorCodes.GREEN + "State of release " + r.name + " has been changed from " + oldState
				+ " to " + r.state + ColorCodes.RED);

		return true;
	}
}
