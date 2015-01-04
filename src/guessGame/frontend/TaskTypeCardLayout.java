package guessGame.frontend;

import guessGame.TaskType;

import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class TaskTypeCardLayout extends CardLayout {
	private PaintMessagePanel pmPanel;
	private PictureUpperPanel picPanel;
	private HashMap<String,UpperPanel> panelMap;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskTypeCardLayout(LowerPanel lowerPanel) {
		panelMap = new HashMap<String, UpperPanel>();
		panelMap.put(TaskType.BINARY.getDescription(),new PaintMessagePanel(lowerPanel));
		panelMap.put(TaskType.JPEG.getDescription(), new PictureUpperPanel(lowerPanel));
		setCardLayout();
		
	}

	private void setCardLayout() {
		for(Entry<String, UpperPanel> es: panelMap.entrySet()){
			this.addLayoutComponent(es.getValue(),es.getKey());
		}
	}

	public TaskTypeCardLayout(int hgap, int vgap) {
		super(hgap, vgap);
		// TODO Auto-generated constructor stub
	}

	public void show(JPanel upperPanel, Object obj, String value) {
		UpperPanel currentPanel =panelMap.get(value);
		currentPanel.addTaskContent(obj);
		currentPanel.repaint();
		super.show(upperPanel, value);
		currentPanel.repaint();
	}


}
