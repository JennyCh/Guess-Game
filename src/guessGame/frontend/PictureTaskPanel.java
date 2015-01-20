package guessGame.frontend;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PictureTaskPanel extends TaskPanel {
	private String link;
	private JLabel jlb;

	public PictureTaskPanel() {
		super();
		jlb = new JLabel();
		add(jlb);
	}

	@Override
	public void addTask(Object challenge) throws IOException {
		removeAll();

		final ImageIcon img = (ImageIcon) challenge;
		jlb = new JLabel();
		System.out.println(img.getIconHeight() + " " + img.getIconWidth());
		jlb.setIcon(img);
		add(jlb);

	}

}
