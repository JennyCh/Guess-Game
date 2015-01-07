package guessGame.frontend;

import java.awt.Dimension;
import java.io.IOException;

import guessGame.TaskType;

import javax.swing.JPanel;

public class TaskPanel extends JPanel implements TaskAdder {
	public TaskPanel(){
		this.setPreferredSize(new Dimension(600, 400));
	}
	@Override
	public void addTask(Object challenge) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
