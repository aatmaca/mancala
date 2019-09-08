package com.kalaha.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kalaha.web.service.GameService;

@Controller
public class MainController {

	@Autowired
	private GameService gameService;

	/**
	 * Handler for welcome page of the game
	 *
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage() {
		return "welcome";
	}

	/**
	 * Creates a new game and returns game state to the client.
	 *
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/games")
	public String initGame(ModelMap model) {
		model.put("game", gameService.createGame());
		return "gameTable";
	}
}
