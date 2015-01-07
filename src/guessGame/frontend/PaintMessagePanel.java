package guessGame.frontend;

import guessGame.Task;
import guessGame.paint.message.ClearMessage;
import guessGame.paint.message.PaintMessage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PaintMessagePanel extends TaskPanel {

	private static final long serialVersionUID = 2646018017326072535L;
	private boolean hasParent;
	private PaintMessage pm = new ClearMessage();
	

	

	

	public PaintMessagePanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		this.pm.apply((Graphics2D) g);
		System.out.println(pm.toString());

	}

	public void repaint(PaintMessage pm) {
		this.pm = pm;
		repaint();
	}

	@Override
	public void addTask(Object challenge) {
		// TODO Auto-generated method stub
		removeAll();
		PaintMessage h = (PaintMessage) challenge;
		//String answer = g.getAnswer();
		this.repaint(h);
		hasParent = true;
		
	}
	

}