package guessGame.message;

import java.util.Scanner;

import charnetskaya.paint.Canvas;

public class PaintMessageFactory {
	/*
	 * private final Socket socket; private final OutputStream out;
	 */
	private final Canvas canvas;

	/*
	 * public Socket getSocket() { return socket; }
	 * 
	 * public OutputStream getOut() { return out; }
	 */
	public PaintMessageFactory(Canvas canvas) {
		this.canvas = canvas;

		/*
		 * 3773 192.168.177.107
		 * 
		 * 192.168.177.152
		 * 
		 * this.canvas = canvas;
		 * 
		 * socket = new Socket("localhost", 3773); // once connection is made,
		 * send it back to the UI
		 * 
		 * out = socket.getOutputStream();
		 */

	}

	public PaintMessage getMessage(String paintMessage) {

		final Scanner s = new Scanner(paintMessage);
		PaintMessage message = null;
		System.out.println("message " + paintMessage);
		switch (MessageType.valueOf(s.next())) {
		case LINE:
			// System.out.println("LINE");
			message = new LineMessage(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
			break;
		case SHAPE:
			// System.out.println("SHAPE");
			message = new ShapeMessage(ShapeType.valueOf(s.next()), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(),
					s.nextInt(), s.nextInt(), Boolean.valueOf(s.next()));
			break;
		case CLEAR:
			// System.out.println("CLEAR");
			message = new ClearMessage(canvas);
			break;
		case BUCKET_FILL:
			// System.out.println("FILL");
			message = new BucketFillMessage(s.nextInt(), s.nextInt(), s.nextInt(), canvas);
			break;
		}
		return message;
	}
}
