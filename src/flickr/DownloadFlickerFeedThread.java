package flickr;

import guessGame.Challenge;
import guessGame.Task;
import guessGame.TaskType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

/**
 * Retrieves the Flicker feed and calls frame.loadImages()
 */
public class DownloadFlickerFeedThread extends Thread {

	private static final String FEED_URL = "https://api.flickr.com/services/feeds/photos_public.gne?id=20952345@N03&format=json";
	private FlickerFeed list;
	private final ArrayList<Challenge> challenges;

	public DownloadFlickerFeedThread(ArrayList<Challenge> challenges) {
		this.challenges = challenges;
	}

	public static String getFeedUrl() {
		return FEED_URL;
	}

	public FlickerFeed getList() {
		return list;
	}

	@Override
	public void run() {
		URL url = null;
		try {
			url = new URL(FEED_URL);
		} catch (final MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final Gson gson = new Gson();
		InputStream in = null;
		try {
			in = connection.getInputStream();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		final JsonReader jsonReader = new JsonReader(reader);
		jsonReader.setLenient(true);
		try {
			jsonReader.nextString();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = gson.fromJson(jsonReader, FlickerFeed.class);

		setChallenges();
	}

	public void setChallenges() {
		final ArrayList<Item> items = list.getItems();
		for (final Item item : items) {
			challenges.add(new Task(TaskType.JPEG, item.getMedia(), item.getTitle()));

		}
	}

}