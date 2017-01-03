package com.hamad.biscuit.commands;

import java.io.IOException;

public interface Command {

	boolean execute() throws IOException;
}
