package guessGame;

public enum TaskFactoryType {

	BINARY("application/octet-stream"), JPEG("image/jpeg"), GIF("image/gif"), TEXT("text/plain");

	/*
	 * Taken form http://www.freeformatter.com/mime-types-list.html
	 */

	
	public String description;
	TaskFactoryType(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
}
