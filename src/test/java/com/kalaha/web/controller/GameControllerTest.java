package com.kalaha.web.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kalaha.game.Game;
import com.kalaha.game.GameWrapper;
import com.kalaha.game.factory.PlayerFactory;
import com.kalaha.game.model.Player;
import com.kalaha.game.model.PlayerType;
import com.kalaha.game.util.RequestStatus;
import com.kalaha.game.validate.ValidationError;
import com.kalaha.web.controller.GameController;
import com.kalaha.web.service.GameServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GameServiceImpl gameService;

	@Test
	public void testPlayGame_gameIdNotFound() throws Exception {
		when(gameService.playGame(anyInt(), anyInt()))
		.thenReturn(new GameWrapper(null, RequestStatus.FAIL, GameWrapper.GAME_IS_NOT_FOUND));

		RequestBuilder request = MockMvcRequestBuilders.put("/games/0/pits/0").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"game\":null,\"status\":\"FAIL\",\"message\":\"Game is not found.\"}"))
				.andReturn();
	}

	@Test
	public void testPlayGame_invalidPitId() throws Exception {

		when(gameService.playGame(anyInt(), anyInt()))
		.thenReturn(new GameWrapper(null, RequestStatus.FAIL, ValidationError.INVALID_PIT.getText()));

		RequestBuilder request = MockMvcRequestBuilders.put("/games/0/pits/0").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json(
				"{\"game\":null,\"status\":\"FAIL\",\"message\":\"" + ValidationError.INVALID_PIT.getText() + "\"}"))
				.andReturn();
	}

	@Test
	public void testPlayGame_valid() throws Exception {

		PlayerFactory factory = new PlayerFactory();
		Player user1 = factory.getPlayer(PlayerType.USER, "Player 1");
		Player user2 = factory.getPlayer(PlayerType.USER, "Player 2");
		Game game = new Game(0, user1, user2);
		game.playGame(0);

		GameWrapper wrapper = new GameWrapper(game, RequestStatus.OK, null);
		ObjectMapper objectMapper = new ObjectMapper();
		String gameAsString = objectMapper.writeValueAsString(wrapper);

		when(gameService.playGame(anyInt(), anyInt())).thenReturn(wrapper);
		RequestBuilder request = MockMvcRequestBuilders.put("/games/0/pits/0").accept(MediaType.APPLICATION_JSON);

		String responseString = mockMvc.perform(request).andReturn().getResponse().getContentAsString();
		JSONAssert.assertEquals(gameAsString, responseString, false);
	}
}
