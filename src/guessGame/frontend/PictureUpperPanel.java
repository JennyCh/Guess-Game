package guessGame.frontend;

import java.awt.Graphics;

import guessGame.ImageTask;
import guessGame.Task;

import javax.swing.ImageIcon;

public class PictureUpperPanel extends UpperPanel {
	private ImageIcon icon = new ImageIcon();
	public PictureUpperPanel(LowerPanel lowerPanel) {
		super(lowerPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		icon.paintIcon(this, g, 0, 0);
	}

	@Override
	public void addTaskContent(Object content) {
		
		Task h = (Task) content;
		String answer = h.getAnswer();
		icon =(ImageIcon) h.getChallenge();
		repaint();
		setTaskSolution(answer);
		
		
	}
}
