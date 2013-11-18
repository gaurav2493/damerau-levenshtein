package org.shortdistance;

@SuppressWarnings("serial")
public class InvalidTypeException extends RuntimeException{

	public InvalidTypeException() {
		super("Invalid List type found. the function only supports List<java.lang.String> type");
	}
}
