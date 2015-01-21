package guessGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoadExistingImageTasks extends Thread {

	private final List<Challenge> challenges;
	private List<String> fileNames;

	public LoadExistingImageTasks(List<Challenge> challenges) {
		this.challenges = challenges;

	}

	@Override
	public void run() {
		getAllFileNames();

		for (final String path : fileNames) {
			System.out.println(path);
		}

		BufferedImage img = null;
		for (final String path : fileNames) {
			try {
				img = ImageIO.read(new File(path));
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			final String answer = path.substring(23);
			final char[] ans = answer.toCharArray();

			final StringBuilder str = new StringBuilder();
			for (int i = 0; i < ans.length - 4; i++) {
				str.append(ans[i]);
			}
			System.out.println(str);
			challenges.add(new Task(TaskType.JPEG, new ImageIcon(img), str.toString()));
		}
	}

	private void getAllFileNames() {
		final String path = "src/guessGame/images/";
		final File folder = new File(path);
		final File[] listOfFiles = folder.listFiles();

		fileNames = new ArrayList<String>();
		for (final File file : listOfFiles) {
			/*
			 * checks if file is not a directory
			 */
			if (file.isFile()) {
				fileNames.add(path + file.getName());
			}
		}
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * new LoadExistingImageTasks(new ArrayList<Challenge>()); }
	 */
}
