package com.biscuit.commands.project;

import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;

public class ShowProject implements Command {

	Project p = null;


	public ShowProject(Project p) {
		super();
		this.p = p;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "title: " + ColorCodes.RESET + p.name);
		System.out.println(ColorCodes.BLUE + "description: ");
		System.out.println(ColorCodes.RESET + p.description);
		System.out.println();

		return true;
	}

}
