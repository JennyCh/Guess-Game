package guessGame;

import java.io.Serializable;

public class Task implements Challenge, Serializable {

	private final Object challenge;
	private final String answer;
	private final TaskType tfType;

	public Task(TaskType tfType, Object challenge, String answer) {
		this.tfType = tfType;
		this.challenge = challenge;
		this.answer = answer;

	}

	public Task(ImageTask imageTask) {
		this.tfType = TaskType.JPEG;
		this.challenge = imageTask.getChallenge();
		this.answer = imageTask.getAnswer();
	}

	public boolean isCorrect() {
		return false;

	}

	@Override
	public Object getChallenge() {
		// TODO Auto-generated method stub
		return challenge;
	}

	@Override
	public String getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}

	@Override
	public TaskType getTaskType() {
		// TODO Auto-generated method stub
		return tfType;
	}

}
