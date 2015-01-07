package guessGame.frontend;

import java.io.IOException;

import guessGame.TaskType;

public interface TaskAdder {
	public void addTask(Object challenge) throws IOException;
}
