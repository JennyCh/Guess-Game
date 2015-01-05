package guessGame.threads;

import guessGame.frontend.UpperPanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Loads a single image from a url into a JLabel
 */
public class DownloadImageThread extends Thread {
	private String url;
	private JLabel jlb;
	private UpperPanel up;

	public DownloadImageThread(UpperPanel up,final JLabel label, final String url) {

		this.url = url;
		this.jlb = label;
		this.up = up;

	}

	public void run() {
		try {

			URL imgUrl = new URL(url);
			BufferedImage img = ImageIO.read(imgUrl);
			this.jlb.setIcon(new ImageIcon(img));
			//up.add(jlb);
			this.jlb.getParent().getParent().revalidate();

		} catch (MalformedURLException m) {
			m.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}