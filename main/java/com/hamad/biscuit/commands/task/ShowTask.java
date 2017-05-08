package com.hamad.biscuit.commands.task;

import java.io.IOException;

import com.hamad.biscuit.ColorCodes;
import com.hamad.biscuit.commands.Command;
import com.hamad.biscuit.models.Task;
import com.hamad.biscuit.models.services.DateService;

public class ShowTask implements Command {

	Task t = null;


	public ShowTask(Task t) {
		super();
		this.t = t;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "title: " + ColorCodes.RESET + t.title);
		System.out.println(ColorCodes.BLUE + "description: ");
		System.out.println(ColorCodes.RESET + t.description);
		System.out.println(ColorCodes.BLUE + "state: " + ColorCodes.RESET + t.state);
		System.out.println(
				ColorCodes.BLUE + "initiated date: " + ColorCodes.RESET + DateService.getDateAsString(t.initiatedDate));
		System.out.println(
				ColorCodes.BLUE + "planned date: " + ColorCodes.RESET + DateService.getDateAsString(t.plannedDate));
		System.out.println(ColorCodes.BLUE + "due date: " + ColorCodes.RESET + DateService.getDateAsString(t.dueDate));
		System.out.println(ColorCodes.BLUE + "estimated time: " + ColorCodes.RESET + t.estimatedTime);
		System.out.println();

		return true;
	}

}
