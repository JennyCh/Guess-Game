package guessGame.frontend;

import guessGame.TaskType;

import java.io.IOException;
import java.util.HashMap;

public class TaskPanelFactory {
	/* All taskPanels extend UpperPanel */

	private final HashMap<TaskType, TaskPanel> taskPanelMap;

	public TaskPanelFactory() {
		taskPanelMap = new HashMap<TaskType, TaskPanel>();
		taskPanelMap.put(TaskType.TEXT, new TextPanel());
		taskPanelMap.put(TaskType.JPEG, new PictureTaskPanel());
		taskPanelMap.put(TaskType.GIF, new PictureTaskPanel());
		taskPanelMap.put(TaskType.PNG, new PictureTaskPanel());
		taskPanelMap.put(TaskType.BINARY, new PaintMessagePanel());

	}

	public TaskPanel generatePanel(Object challenge, TaskType taskType) throws IOException {
		final TaskPanel currentPanel = taskPanelMap.get(taskType);
		currentPanel.addTask(challenge);
		/*
		 * addTask exception will be handled in the client readInTask method
		 * along with all other UI errors
		 */
		return currentPanel;
	}
}
