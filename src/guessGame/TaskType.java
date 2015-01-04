package guessGame;

public enum TaskType {

	BINARY("application/octet-stream"), JPEG("image/jpeg"), GIF("image/gif"), TEXT("text/plain");

	/*
	 * Taken form http://www.freeformatter.com/mime-types-list.html
	 */

	
	public String description;
	TaskType(String description) {
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
