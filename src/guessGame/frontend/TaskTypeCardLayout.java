package guessGame.frontend;

import guessGame.TaskType;

import java.awt.CardLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class TaskTypeCardLayout extends CardLayout {
	private PictureTaskPanel picPanel;
	private HashMap<String, TaskPanel> panelMap;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskTypeCardLayout(AnswerPanel lowerPanel) {
		panelMap = new HashMap<String, TaskPanel>();
		// commented out mmandel
		// panelMap.put(TaskType.BINARY.getDescription(),new
		// PaintMessagePanel(lowerPanel));
		// panelMap.put(TaskType.JPEG.getDescription(), new
		// PictureTaskPanel(lowerPanel));
		panelMap.put(TaskType.JPEG.getDescription(), new PictureTaskPanel());

		setCardLayout();

	}

	private void setCardLayout() {
		for (final Entry<String, TaskPanel> es : panelMap.entrySet()) {
			this.addLayoutComponent(es.getValue(), es.getKey());
		}
	}

	public TaskTypeCardLayout(int hgap, int vgap) {
		super(hgap, vgap);
		// TODO Auto-generated constructor stub
	}

	public void show(JPanel upperPanel, Object obj, String value) throws IOException {
		final TaskPanel currentPanel = panelMap.get(value);
		// currentPanel.addTaskContent(obj);commented out mmandel
		currentPanel.addTask(obj);
		currentPanel.repaint();
		super.show(upperPanel, value);
		currentPanel.repaint();
	}

}
