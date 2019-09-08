package com.kalaha.web.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.kalaha.game.Game;
import com.kalaha.game.GameWrapper;
import com.kalaha.game.factory.PlayerFactory;
import com.kalaha.game.model.Player;
import com.kalaha.game.model.PlayerType;
import com.kalaha.game.util.RequestStatus;
import com.kalaha.game.validate.ValidationError;

@Service
public class GameServiceImpl implements GameService {

	private static final Map<Integer, Game> map = new ConcurrentHashMap<>();
	private static int idGenerator;
	private PlayerFactory factory;

	public GameServiceImpl() {
		factory = new PlayerFactory();
		idGenerator = 0;
	}

	/**
	 * Create new Game and save into Map.
	 *
	 * @return Game object.
	 */
	@Override
	public Game createGame() {
		Player user1 = factory.getPlayer(PlayerType.USER, "Player 1");
		Player user2 = factory.getPlayer(PlayerType.USER, "Player 2");

		int id = generateId();
		Game game = new Game(id, user1, user2);
		map.put(id, game);
		return game;
	}

	/**
	 * Play game according to user's move.
	 *
	 * @param gameId
	 *            game id
	 * @param pitIndex
	 *            pit index
	 * @return GameWrapper will wrap current state of the game if pit and game is
	 *         valid and corresponding ValidationError otherwise.
	 */
	@Override
	public GameWrapper playGame(int gameId, int pitId) {
		Game game = map.get(gameId);
		if (game == null) {
			return new GameWrapper(null, RequestStatus.FAIL, GameWrapper.GAME_IS_NOT_FOUND);
		}

		ValidationError error = game.playGame(pitId);
		if (error != null) {
			return new GameWrapper(null, RequestStatus.FAIL, error.getText());
		}
		return new GameWrapper(game, RequestStatus.OK, null);
	}

	private int generateId() {
		int id;
		synchronized (this) {
			id = idGenerator++;
		}
		return id;
	}
}
