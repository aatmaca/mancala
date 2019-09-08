package com.kalaha.game.validate;

import com.kalaha.game.Game;
import com.kalaha.game.model.Pit;
import com.kalaha.game.util.GameStatus;
import com.kalaha.game.util.PitType;
import com.kalaha.game.util.PlayerId;

public class PitValidator implements Validator {

	/**
	 * Validate pit
	 *
	 * @param game
	 *            Game object
	 * @param pit
	 *            validated pit
	 * @param turn
	 *            current player
	 *
	 * @return null if pit and game is valid, corresponding ValidationError otherwise.
	 */
	@Override
	public ValidationError validate(Game game, Pit pit, PlayerId turn) {

		if (game.getStatus() == GameStatus.GAME_OVER) {
			return ValidationError.GAME_OVER;
		} else if (pit == null) {
			return ValidationError.INVALID_PIT;
		} else if (pit.getOwner() != turn) {
			return ValidationError.WRONG_PLAYER_TURN;
		} else if (pit.getType() != PitType.REGULAR_PIT) {
			return ValidationError.WRONG_PIT_SELECTED;
		} else if (pit.getStoneCount() == 0) {
			return ValidationError.EMPTY_PIT_SELECTED;
		}

		return null;
	}

}
