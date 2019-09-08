package com.kalaha.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kalaha.game.Board;
import com.kalaha.game.model.Pit;
import com.kalaha.game.util.PitType;

public class BoardTest {

	@Test
	public void testBoard() {

		Board board = new Board();
		Pit[] pits = board.getPits();

		int expectedStoneCount = Board.INITIAL_STONE_COUNT_PER_PIT;
		for (int i = 0; i < pits.length; i++) {
			if (pits[i].getType() == PitType.BIG_PIT) {
				assertEquals(0, pits[i].getStoneCount());
			} else {
				assertEquals(expectedStoneCount, pits[i].getStoneCount());
			}
		}
	}

	@Test
	public void testNextPit() {

		Board board = new Board();
		Pit[] pits = board.getPits();

		assertEquals(pits[6], board.getNextPit(pits[5]));
		assertEquals(pits[0], board.getNextPit(pits[pits.length - 1]));
	}

	@Test
	public void testOppositePit() {

		Board board = new Board();
		Pit[] pits = board.getPits();

		assertEquals(pits[Board.REGULAR_PIT_COUNT_PER_PLAYER + 1],
				board.getOppositePit(pits[Board.REGULAR_PIT_COUNT_PER_PLAYER - 1]));
		assertEquals(pits[0], board.getOppositePit(pits[pits.length - 2]));
	}

	@Test
	public void testBigPit() {

		Board board = new Board();

		assertEquals(Board.REGULAR_PIT_COUNT_PER_PLAYER, board.getPlayer1BigPit().getId());
		assertEquals(board.getPitCount()-1, board.getPlayer2BigPit().getId());
	}

	@Test
	public void testRegularPitTotal() {

		Board board = new Board();

		int expectedStoneCount = Board.REGULAR_PIT_COUNT_PER_PLAYER * Board.INITIAL_STONE_COUNT_PER_PIT;
		assertEquals(expectedStoneCount, board.getPlayer1RegularPitTotal());
		assertEquals(expectedStoneCount, board.getPlayer2RegularPitTotal());
	}

	@Test
	public void testResetRegularPits() {

		Board board = new Board();
		board.resetRegularPits();
		Pit[] pits = board.getPits();

		int expectedStoneCount = 0;
		for (int i = 0; i < pits.length; i++) {
			if (pits[i].getType() == PitType.REGULAR_PIT) {
				assertEquals(expectedStoneCount, pits[i].getStoneCount());
			}
		}
	}

}
