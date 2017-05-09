package com.biscuit.commands.userStory;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.DateService;

public class ShowUserStory implements Command {

	UserStory us = null;


	public ShowUserStory(UserStory us) {
		super();
		this.us = us;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "title: " + ColorCodes.RESET + us.title);
		System.out.println(ColorCodes.BLUE + "description: ");
		System.out.println(ColorCodes.RESET + us.description);
		System.out.println(ColorCodes.BLUE + "state: " + ColorCodes.RESET + us.state);
		System.out.println(ColorCodes.BLUE + "business value: " + ColorCodes.RESET + us.businessValue);
		System.out.println(ColorCodes.BLUE + "initiated date: " + ColorCodes.RESET
				+ DateService.getDateAsString(us.initiatedDate));
		System.out.println(
				ColorCodes.BLUE + "planned date: " + ColorCodes.RESET + DateService.getDateAsString(us.plannedDate));
		System.out.println(ColorCodes.BLUE + "due date: " + ColorCodes.RESET + DateService.getDateAsString(us.dueDate));
		System.out.println(ColorCodes.BLUE + "points: " + ColorCodes.RESET + us.points);
		System.out.println();
		
		return true;
	}

}
