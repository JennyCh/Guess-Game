package guessGame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class ImageTask  implements Challenge,Serializable {

	private final String link;
	private final String answer;

	public ImageTask(String link, String answer) throws IOException {
		
		this.link = link;
		this.answer = answer;
	}

	@Override
	public Object getChallenge() {
		// TODO Auto-generated method stub
		return link;
	}

	@Override
	public String getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}

	@Override
	public TaskType getTaskType() {
		// TODO Auto-generated method stub
		return TaskType.JPEG;
	}

}
