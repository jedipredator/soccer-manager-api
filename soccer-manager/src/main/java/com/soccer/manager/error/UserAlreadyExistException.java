package com.soccer.manager.error;

public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String msg) {
        super(msg);
    }
}
