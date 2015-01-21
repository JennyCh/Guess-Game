package guessGame.message;

import java.awt.Graphics2D;

import charnetskaya.paint.Canvas;

public class ClearMessage implements PaintMessage {

	private final int width;
	private final int height;
	private final Canvas canvas;

	public ClearMessage(Canvas canvas) {
		width = canvas.getWidth();
		height = canvas.getHeight();
		this.canvas = canvas;
	}

	@Override
	public void apply(Graphics2D g2) {
		canvas.setClearBackground(canvas.getActiveLayer());
	}

	@Override
	public String toString() {

		return "CLEAR\n";
	}

}
