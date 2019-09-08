package com.kalaha.web.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kalaha.game.Game;
import com.kalaha.game.GameWrapper;
import com.kalaha.game.util.RequestStatus;
import com.kalaha.web.service.GameService;
import com.kalaha.web.service.GameServiceImpl;

public class GameServiceTest {

	@Test
	public void testCreateGame() {

		GameService service = new GameServiceImpl();
		Game game0 = service.createGame();
		assertEquals(0, game0.getId());

		Game game1 = service.createGame();
		assertEquals(1, game1.getId());
	}

	@Test
	public void testPlayGame_gameIdNotFound() {

		GameService service = new GameServiceImpl();
		service.createGame();

		GameWrapper gameWrapper = service.playGame(99, 0);
		assertEquals(RequestStatus.FAIL, gameWrapper.getStatus());
		assertEquals(null, gameWrapper.getGame());
	}

	@Test
	public void testPlayGame_invalidPitId() {

		GameService service = new GameServiceImpl();
		Game game = service.createGame();

		GameWrapper gameWrapper = service.playGame(0, game.getBoard().getPitCount());
		assertEquals(RequestStatus.FAIL, gameWrapper.getStatus());
		assertEquals(null, gameWrapper.getGame());
	}

	@Test
	public void testPlayGame_valid() {

		GameService service = new GameServiceImpl();
		Game game = service.createGame();

		GameWrapper gameWrapper = service.playGame(0, 0);
		assertEquals(RequestStatus.OK, gameWrapper.getStatus());
		assertEquals(game, gameWrapper.getGame());
	}
}
