package com.biscuit.commands.help;

import java.io.IOException;

import com.biscuit.commands.Command;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class UniversalHelp implements Command {

	@Override
	public boolean execute() throws IOException {

		V2_AsciiTable at = new V2_AsciiTable();

		executeChild(at);

		at.addRule();
		at.addRule();
		at.addRow(null, "Other Commands").setAlignment(new char[] { 'c', 'c' });
		at.addRule();

		at.addRow("clear", "Clear the terminal screen").setAlignment(new char[] { 'l', 'l' });
		at.addRow("exit", "Exit/terminate the program").setAlignment(new char[] { 'l', 'l' });
		at.addRow("dashboard", "Go to dashboard").setAlignment(new char[] { 'l', 'l' });
		at.addRow("go_to dashboard", "Go to dashboard").setAlignment(new char[] { 'l', 'l' });
		at.addRow("help", "Show help").setAlignment(new char[] { 'l', 'l' });

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.NO_BORDERS.get());
		rend.setWidth(new WidthLongestLine());

		RenderedTable rt = rend.render(at);

		System.out.println(rt.toString());

		return true;
	}


	protected void executeChild(V2_AsciiTable at) {
	}

}
