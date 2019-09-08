package com.kalaha.web.service;

import com.kalaha.game.Game;
import com.kalaha.game.GameWrapper;

public interface GameService {

	Game createGame();
	GameWrapper playGame(int gameId, int pitId) ;
}