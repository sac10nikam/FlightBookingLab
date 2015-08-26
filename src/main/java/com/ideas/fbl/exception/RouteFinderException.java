package com.ideas.fbl.exception;

import java.io.Serializable;

/**
 * Route Finder exception class.
 * @author sachin_nikam
 *
 */
public class RouteFinderException extends RuntimeException implements Serializable {
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 4147165202670184905L;
	/**
	 * Exception message
	 */
    private String message = null;
 
    /**
     * No argument constructor
     */
    public RouteFinderException() {
        super();
    }
 
    /**
     * Constructor with exception message parameter
     */
    public RouteFinderException(final String message) {
        super(message);
        this.message = message;
    }
 
    /**
     * Constructor with {@link Throwable} class reference
     */
    public RouteFinderException(final Throwable cause) {
        super(cause);
    }
 
    /**
     * Gets the exception message.
     * @return The message
     */
    @Override
    public String toString() {
        return message;
    }
 
    /**
     * Gets the exception message.
     * @return The message
     */
    @Override
    public String getMessage() {
        return message;
    }
}