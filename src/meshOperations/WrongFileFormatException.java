package meshOperations;

public class WrongFileFormatException extends Exception{
	String message;
	
	public WrongFileFormatException(String message) {
		super(message);
		this.message = message;
	}
}
