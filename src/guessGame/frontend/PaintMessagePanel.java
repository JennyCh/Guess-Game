package guessGame.frontend;

import guessGame.Task;
import guessGame.paint.message.ClearMessage;
import guessGame.paint.message.PaintMessage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PaintMessagePanel extends TaskPanel {

	private static final long serialVersionUID = 2646018017326072535L;
	private String answer;
	private PaintMessage pm = new ClearMessage();
	private BufferedImage image;

	

	

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
	}

	@Override
	public void addTask(Object challenge) {
		// TODO Auto-generated method stub
		Task g = (Task) challenge;
		PaintMessage h = (PaintMessage) g.getChallenge();
		String answer = g.getAnswer();
		this.repaint(h);
		
	}
	

}