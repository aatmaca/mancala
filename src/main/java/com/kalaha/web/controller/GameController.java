package com.kalaha.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalaha.game.GameWrapper;
import com.kalaha.web.service.GameService;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	/**
	 * Handle player move request.
	 *
	 * @param gameId
	 *            id of the game
	 * @param pitId
	 *            id of the selected pit
	 * @return Response will be current state of the game if pit and game is valid,
	 *         corresponding ValidationError otherwise.
	 *
	 */
	@PutMapping("/games/{gameId}/pits/{pitId}")
	public ResponseEntity<GameWrapper> playGame(@PathVariable int gameId, @PathVariable int pitId) {

		return ResponseEntity.ok(gameService.playGame(gameId, pitId));
	}
}
