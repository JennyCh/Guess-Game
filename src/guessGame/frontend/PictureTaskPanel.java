package guessGame.frontend;

import guessGame.threads.DownloadImageThread;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
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
	public void addTask(Object imageLink) throws IOException {
		removeAll();
		this.link = imageLink.toString();
		final URL imgUrl = new URL(link);
		final BufferedImage img = ImageIO.read(imgUrl);
		jlb = new JLabel();
		jlb.setPreferredSize(new Dimension(300, 300));
		new DownloadImageThread(this, jlb, link).start();
		add(jlb);
	}

}
