package com.kalaha.game.validate;

public enum ValidationError {

	WRONG_PIT_SELECTED("Please click your regular pit."),
	EMPTY_PIT_SELECTED("Please click non-empty pit: Your non-empty regular pit."),
	WRONG_PLAYER_TURN("It's the other player's turn."),
	GAME_OVER("Game is over."),
	INVALID_PIT("Invalid pit Id.");

	private final String text;

	private ValidationError(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
