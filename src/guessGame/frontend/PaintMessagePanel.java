package guessGame.frontend;

import Message.ClearMessage;
import Message.PaintMessage;
import Message.PaintMessageFactory;

public class PaintMessagePanel extends TaskPanel {

	private static final long serialVersionUID = 2646018017326072535L;
	// private boolean hasParent;
	private final PaintMessage pm = new ClearMessage();
	private final PaintMessageFactory messageFactory;
	private final PaintPanel panel;

	public PaintMessagePanel() {
		super();
		// TODO Auto-generated constructor stub
		this.messageFactory = new PaintMessageFactory();
		panel = new PaintPanel(pm);
		this.add(panel);

	}

	@Override
	public void addTask(Object challenge) {
		// TODO Auto-generated method stub
		System.out.println("ADD TASK");
		removeAll();
		final PaintMessage h = (PaintMessage) challenge;
		// String answer = g.getAnswer();
		this.panel.setPm(pm);
		// hasParent = true;

	}

}