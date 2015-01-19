package flickr;

import java.io.Serializable;

public class Media implements Serializable {
	private String m;

	public String getM() {
		return m;
	}

	@Override
	public String toString() {
		return m + "\n";
	}
}
