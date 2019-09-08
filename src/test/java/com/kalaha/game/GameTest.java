package com.kalaha.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kalaha.game.Board;
import com.kalaha.game.Game;
import com.kalaha.game.factory.PlayerFactory;
import com.kalaha.game.model.Pit;
import com.kalaha.game.model.Player;
import com.kalaha.game.model.PlayerType;
import com.kalaha.game.util.PitType;
import com.kalaha.game.validate.ValidationError;

public class GameTest {

	private static Game setup(){
		PlayerFactory factory = new PlayerFactory();
		Player user1 = factory.getPlayer(PlayerType.USER, "Player 1");
		Player user2 = factory.getPlayer(PlayerType.USER, "Player 2");

		return new Game(0, user1, user2);
	}

	@Test
	public void testPlayGame() {
		Game game = setup();

		ValidationError error = game.playGame(0);
		assertEquals(null, error);

		Pit[] pits = game.getBoard().getPits();
		int expectedStoneCount = Board.INITIAL_STONE_COUNT_PER_PIT + 1;
		for (int i = 1; i <= Board.INITIAL_STONE_COUNT_PER_PIT; i++) {
			if (pits[i].getType() == PitType.BIG_PIT) {
				assertEquals(1, pits[i].getStoneCount());
			} else {
				assertEquals(expectedStoneCount, pits[i].getStoneCount());
			}
		}
	}
}
