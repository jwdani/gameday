package com.gameday.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class FileStorageException.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileStorageException extends RuntimeException {
	
	/** The file name. */
	private String fileName;
	
	/** The exception. */
	private Exception exception;
	
	/**
	 * Instantiates a new file storage exception.
	 *
	 * @param fileName the file name
	 */
	public FileStorageException(String fileName) {
		 
		this.fileName = fileName;
	}
	
	/**
	 * Instantiates a new file storage exception.
	 *
	 * @param fileName the file name
	 * @param exception the exception
	 */
	public FileStorageException(String fileName, Exception exception) {
		 
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
