package flickr;

import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DownloadImageThread extends Thread {
	ImageIcon image = null;

	public DownloadImageThread(final JLabel label, final String url) {

		try {

			image = new ImageIcon(new URL(url));
			label.setIcon(image);
		} catch (final IOException e) {
		}
	}

	@Override
	public void run() {
		super.run();
	}
}
