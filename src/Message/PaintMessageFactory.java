package Message;

import java.util.Scanner;

public class PaintMessageFactory {

	public PaintMessageFactory() {
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
		/*
		 * case CLEAR: // System.out.println("CLEAR"); message = new
		 * ClearMessage(canvas); break; case BUCKET_FILL: //
		 * System.out.println("FILL"); message = new
		 * BucketFillMessage(s.nextInt(), s.nextInt(), s.nextInt(), canvas);
		 * break;
		 */
		}
		return message;
	}
}
