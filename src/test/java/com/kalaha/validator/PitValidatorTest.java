package com.kalaha.validator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.kalaha.game.Board;
import com.kalaha.game.Game;
import com.kalaha.game.factory.PlayerFactory;
import com.kalaha.game.model.Pit;
import com.kalaha.game.model.Player;
import com.kalaha.game.model.PlayerType;
import com.kalaha.game.util.GameStatus;
import com.kalaha.game.util.PlayerId;
import com.kalaha.game.validate.PitValidator;
import com.kalaha.game.validate.ValidationError;
import com.kalaha.game.validate.Validator;

public class PitValidatorTest {

	private static Game setup() {
		PlayerFactory factory = new PlayerFactory();
		Player user1 = factory.getPlayer(PlayerType.USER, "Player 1");
		Player user2 = factory.getPlayer(PlayerType.USER, "Player 2");

		return new Game(0, user1, user2);
	}

	@Test
	public void validate_invalidPitType() {

		Game game = setup();
		Pit pit = game.getBoard().getPit(Board.REGULAR_PIT_COUNT_PER_PLAYER);

		Validator validator = new PitValidator();
		ValidationError actualResult = validator.validate(game, pit, PlayerId.PLAYER_1);

		ValidationError expectedResult = ValidationError.WRONG_PIT_SELECTED;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void validate_emptyPit() {

		Game game = setup();
		Pit pit = game.getBoard().getPit(0);
		pit.setStoneCount(0);

		Validator validator = new PitValidator();
		ValidationError actualResult = validator.validate(game, pit, PlayerId.PLAYER_1);

		ValidationError expectedResult = ValidationError.EMPTY_PIT_SELECTED;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void validate_wrongPlayerTurn() {

		Game game = setup();
		Pit pit = game.getBoard().getPit(0);

		Validator validator = new PitValidator();
		ValidationError actualResult = validator.validate(game, pit, PlayerId.PLAYER_2);

		ValidationError expectedResult = ValidationError.WRONG_PLAYER_TURN;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void validate_gameOver() {

		Game game = spy(setup());
		when(game.getStatus()).thenReturn(GameStatus.GAME_OVER);

		Pit pit = game.getBoard().getPit(0);
		Validator validator = new PitValidator();
		ValidationError actualResult = validator.validate(game, pit, PlayerId.PLAYER_1);

		ValidationError expectedResult = ValidationError.GAME_OVER;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void validate_invalidPitId() {

		Game game = setup();

		Validator validator = new PitValidator();
		ValidationError actualResult = validator.validate(game, null, PlayerId.PLAYER_1);

		ValidationError expectedResult = ValidationError.INVALID_PIT;

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void validate_validInput() {
		Game game = setup();
		Pit pit = game.getBoard().getPit(0);
		Validator validator = new PitValidator();
		ValidationError actualResult = validator.validate(game, pit, PlayerId.PLAYER_1);

		ValidationError expectedResult = null;

		assertEquals(expectedResult, actualResult);
	}
}
