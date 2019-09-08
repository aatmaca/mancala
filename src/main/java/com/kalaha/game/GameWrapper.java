package com.kalaha.game;

import com.kalaha.game.util.RequestStatus;

/**
 * Wrapper class for returning response to Ajax calls.
 *
 */
public class GameWrapper {

	private Game game;
	private RequestStatus status;
	private String message;

	public static final String GAME_IS_NOT_FOUND = "Game is not found.";

	public GameWrapper(Game game, RequestStatus status, String message) {
		this.game =game;
		this.status = status;
		this.message = message;
	}

	public Game getGame() {
		return game;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
