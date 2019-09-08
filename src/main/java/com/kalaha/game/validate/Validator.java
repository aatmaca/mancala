package com.kalaha.game.validate;

import com.kalaha.game.Game;
import com.kalaha.game.model.Pit;
import com.kalaha.game.util.PlayerId;

public interface Validator {

	public ValidationError validate(Game game, Pit pit, PlayerId turn);
}
