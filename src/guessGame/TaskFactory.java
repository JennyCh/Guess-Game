package guessGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import flickr.DownloadFlickerFeedThread;

public class TaskFactory {

	/*
	 * This class has an array list of all the challenges
	 */
	private Task task;
	protected ArrayList<Challenge> challenges;
	private int currentIndex = 0;

	public TaskFactory() throws IOException {

		challenges = new ArrayList<Challenge>();
		new DownloadFlickerFeedThread(challenges).start();
		new LoadExistingImageTasks(challenges).start();
		// new DownloadHTMLImagesThread(challenges).start();

	}

	public Challenge getNextTask() {
		if (currentIndex + 1 == challenges.size()) {
			currentIndex = 0;
			return challenges.get(currentIndex);
		} else {
			return challenges.get(++currentIndex);
		}

	}

	public Challenge getRandomTask() {
		final Random random = new Random();
		final int index = random.nextInt(challenges.size());
		/* final int index = (int) (Math.random() * challenges.size() ); */
		return challenges.get(index);
	}

}
