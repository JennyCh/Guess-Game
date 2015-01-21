package guessGame.flickr;

import java.io.Serializable;

public class Item implements Serializable {

	private Media media;
	private String title;

	public String getTitle() {
		return title;
	}

	public Media getMedia() {
		return media;
	}

	@Override
	public String toString() {
		return media + "\n";
	}

}
