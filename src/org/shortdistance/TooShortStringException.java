package org.shortdistance;

@SuppressWarnings("serial")
public class TooShortStringException extends RuntimeException{
	public TooShortStringException() {
		super("The String you entered is too short. The length should be at least 2 characters.");
	}

}
