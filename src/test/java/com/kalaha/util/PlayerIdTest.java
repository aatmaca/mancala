package com.kalaha.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kalaha.game.util.PlayerId;

public class PlayerIdTest {

	@Test
	public void testPlayGame() {
		assertEquals(PlayerId.PLAYER_2, PlayerId.PLAYER_1.nextTurn());
		assertEquals(PlayerId.PLAYER_1, PlayerId.PLAYER_2.nextTurn());
	}
}
