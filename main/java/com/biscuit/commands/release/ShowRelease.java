package com.biscuit.commands.release;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Release;
import com.biscuit.models.services.DateService;

public class ShowRelease implements Command {
	Release r = null;


	public ShowRelease(Release r) {
		super();
		this.r = r;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "name: " + ColorCodes.RESET + r.name);
		System.out.println(ColorCodes.BLUE + "description: ");
		System.out.println(ColorCodes.RESET + r.description);
		System.out.println(ColorCodes.BLUE + "state: " + ColorCodes.RESET + r.state);
		System.out.println(
				ColorCodes.BLUE + "Start date: " + ColorCodes.RESET + DateService.getDateAsString(r.startDate));
		System.out.println(ColorCodes.BLUE + "due date: " + ColorCodes.RESET + DateService.getDateAsString(r.dueDate));
		System.out.println(ColorCodes.BLUE + "Assigned Effort: " + ColorCodes.RESET + r.assignedEffort);
		System.out.println();

		return true;
	}
}
