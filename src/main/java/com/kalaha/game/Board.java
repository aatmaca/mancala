package com.kalaha.game;

import com.kalaha.game.model.Pit;
import com.kalaha.game.util.PitType;
import com.kalaha.game.util.PlayerId;

public class Board {

	public static final int REGULAR_PIT_COUNT_PER_PLAYER = 6;
	public static final int INITIAL_STONE_COUNT_PER_PIT = 6;

	private int pitCount;
	private int player1BigPitIndex;
	private int player2BigPitIndex;
	private Pit[] pits;
	private Pit lastUpdatedPit;

	/**
	 * Instantiates a new Board.
	 */
	public Board() {
		pitCount = REGULAR_PIT_COUNT_PER_PLAYER * 2 + 2;
		player1BigPitIndex = REGULAR_PIT_COUNT_PER_PLAYER;
		player2BigPitIndex = pitCount - 1;

		pits = new Pit[pitCount];

		fillBoard();
	}

	/**
	 * Put stones into the pits.
	 */
	private void fillBoard() {
		int i = 0;
		while (i < REGULAR_PIT_COUNT_PER_PLAYER) {
			pits[i] = new Pit(i, PitType.REGULAR_PIT, PlayerId.PLAYER_1, INITIAL_STONE_COUNT_PER_PIT);
			i++;
		}

		pits[i] = new Pit(i, PitType.BIG_PIT, PlayerId.PLAYER_1, 0);
		i++;

		while (i < pitCount - 1) {
			pits[i] = new Pit(i, PitType.REGULAR_PIT, PlayerId.PLAYER_2, INITIAL_STONE_COUNT_PER_PIT);
			i++;
		}

		pits[i] = new Pit(i, PitType.BIG_PIT, PlayerId.PLAYER_2, 0);
	}

	/**
	 * Return pit.
	 */
	public Pit getPit(int pitId) {
		if (pitId < 0 || pitId >= pitCount) {
			return null;
		}

		return pits[pitId];
	}

	/**
	 * Returns the next valid pit for distribution of stones.
	 *
	 * @param pit
	 *            selected pit object
	 * @return next valid pit
	 *
	 */
	public Pit getNextPit(Pit pit) {
		int nextId = pit.getId() + 1;

		if (nextId == pitCount) {
			return pits[0];
		}

		return pits[nextId];
	}

	/**
	 * Returns the opposite pit of opponent.
	 *
	 * @param pit
	 *            selected pit object
	 * @return opposite pit of opponent
	 *
	 */
	public Pit getOppositePit(Pit pit) {
		return pits[pitCount - 2 - pit.getId()];
	}

	public Pit getLastUpdatedPit() {
		return lastUpdatedPit;
	}

	public void setLastUpdatedPit(Pit pit) {
		lastUpdatedPit = pit;
	}

	/**
	 * Returns the big pit of this player
	 *
	 * @param owner
	 *            id of the owner of pit
	 * @return big pit of this player
	 *
	 */
	public Pit getBigPit(PlayerId owner) {
		if (owner == PlayerId.PLAYER_1) {
			return pits[player1BigPitIndex];
		} else {
			return pits[player2BigPitIndex];
		}
	}

	public Pit getPlayer1BigPit() {
		return pits[player1BigPitIndex];
	}

	public Pit getPlayer2BigPit() {
		return pits[player2BigPitIndex];
	}

	/**
	 * Retrieve total stone count in regular pits
	 */
	public int getPlayer1RegularPitTotal() {
		Integer total = 0;
		for (int i = 0; i < REGULAR_PIT_COUNT_PER_PLAYER; i++) {
			total += pits[i].getStoneCount();
		}
		return total;
	}

	/**
	 * Retrieve total stone count in regular pits
	 */
	public int getPlayer2RegularPitTotal() {
		Integer total = 0;
		for (int i = REGULAR_PIT_COUNT_PER_PLAYER + 1; i < pitCount - 1; i++) {
			total += pits[i].getStoneCount();
		}
		return total;
	}

	/**
	 * Reset stone counts.
	 */
	public void resetRegularPits() {
		int i = 0;
		while (i < REGULAR_PIT_COUNT_PER_PLAYER) {
			pits[i].setStoneCount(0);
			i++;
		}
		i++;
		while (i < pitCount - 1) {
			pits[i].setStoneCount(0);
			i++;
		}
	}

	public int getRegularPitCount() {
		return REGULAR_PIT_COUNT_PER_PLAYER;
	}

	public int getPitCount() {
		return pitCount;
	}

	public int getPlayer1BigPitIndex() {
		return player1BigPitIndex;
	}

	public int getPlayer2BigPitIndex() {
		return player2BigPitIndex;
	}

	public Pit[] getPits() {
		return pits;
	}
}
