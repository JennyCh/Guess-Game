package guessGame.frontend;

import guessGame.Task;
import guessGame.threads.DownloadImageThread;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PictureUpperPanel extends UpperPanel {
	private String link;
	private JLabel jlb;
	public PictureUpperPanel(String imageLink) throws IOException {
		this.link = imageLink;
		URL imgUrl = new URL(link);
		BufferedImage img = ImageIO.read(imgUrl);
		jlb = new JLabel();
		jlb.setPreferredSize(new Dimension(300,300));
		new DownloadImageThread(this,jlb,imageLink).start();;
		
		add(jlb);
	}


}
