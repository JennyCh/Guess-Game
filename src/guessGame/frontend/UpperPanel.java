package guessGame.frontend;

import guessGame.paint.message.ClearMessage;
import guessGame.paint.message.PaintMessage;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class UpperPanel extends JPanel {

	private static final long serialVersionUID = 2646018017326072535L;
	private final String answer = "rectangle";
	private PaintMessage pm = new ClearMessage();

	@Override
	protected void paintComponent(Graphics g) {
		this.pm.apply((Graphics2D) g);
		System.out.println(pm.toString());
		
	}
	
	public void repaint(PaintMessage pm){
		this.pm  = pm;
	}
	
}