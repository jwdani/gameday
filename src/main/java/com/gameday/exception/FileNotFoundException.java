package com.gameday.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class FileNotFoundException.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1235816451564814860L;

	/** The file name. */
	private String fileName;
	
	/** The exception. */
	private Exception exception;
	
	/**
	 * Instantiates a new file not found exception.
	 *
	 * @param fileName the file name
	 */
	public FileNotFoundException(String fileName) {
		 
		this.fileName = fileName;
	}
	
	/**
	 * Instantiates a new file not found exception.
	 *
	 * @param fileName the file name
	 * @param exception the exception
	 */
	public FileNotFoundException(String fileName, Exception exception) {
		 
		this.fileName = fileName;
		this.exception = exception;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}	
}
