package com.hamad.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.hamad.biscuit.commands.task.ChangeStatusTask;
import com.hamad.biscuit.commands.task.ShowTask;
import com.hamad.biscuit.factories.TaskCompleterFactory;
import com.hamad.biscuit.models.Task;
import com.hamad.biscuit.models.enums.State;

import jline.console.completer.Completer;

public class TaskView extends View {

	Task task = null;


	public TaskView(View previousView, Task task) {
		super(previousView, task.title);
		this.task = task;
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(TaskCompleterFactory.getTaskCompleters(task));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		}
		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("change_status_to")) {
			if (State.values.contains(words[1])) {
				(new ChangeStatusTask(task, State.valueOf(words[1].toUpperCase()))).execute();
				return true;
			}
		}
		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("show")) {
			(new ShowTask(task)).execute();
			return true;
		}
		return false;
	}

}
