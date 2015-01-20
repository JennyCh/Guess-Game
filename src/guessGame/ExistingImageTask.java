package guessGame;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class ExistingImageTask extends Task implements Challenge, Serializable {

	private final ImageIcon image;
	private final String answer;
	private final TaskType type = TaskType.JPEG;

	public ExistingImageTask(ImageIcon image, String answer) {
		super(TaskType.JPEG, image, answer);
		this.image = image;
		this.answer = answer;
	}

	@Override
	public Object getChallenge() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public String getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}

	@Override
	public TaskType getTaskType() {
		// TODO Auto-generated method stub
		return type;
	}

}
