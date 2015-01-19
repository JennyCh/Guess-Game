package guessGame.frontend;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import Message.PaintMessage;

public class PaintPanel extends JComponent {

	private final BufferedImage image;
	private PaintMessage pm;

	public PaintPanel(PaintMessage pm) {
		this.image = new BufferedImage(600, 800, BufferedImage.TYPE_INT_ARGB_PRE);
		this.pm = pm;
	}

	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("PAINT COMPONENT");
		g.drawImage(image, 0, 0, null);

		this.pm.apply((Graphics2D) g);
		System.out.println(pm.toString());
		this.repaint();

	}

	public PaintMessage getPm() {
		return pm;
	}

	public void setPm(PaintMessage pm) {
		this.pm = pm;
	}

	public BufferedImage getImage() {
		return image;
	}

}
