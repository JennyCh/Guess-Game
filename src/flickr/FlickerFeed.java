package flickr;

import java.util.ArrayList;

/**
 * The class that represents the json feed from Flicker out of json
 */
public class FlickerFeed {

	// TODO: add code here

	ArrayList<Item> items;

	public FlickerFeed() {

	}

	@Override
	public String toString() {
		return items + " \n";
	}

	public ArrayList<Item> getItems() {
		return items;
	}

}