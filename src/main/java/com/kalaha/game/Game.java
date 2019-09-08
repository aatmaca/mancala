package com.kalaha.game;

import com.kalaha.game.model.Pit;
import com.kalaha.game.model.Player;
import com.kalaha.game.util.GameResult;
import com.kalaha.game.util.GameStatus;
import com.kalaha.game.util.PitType;
import com.kalaha.game.util.PlayerId;
import com.kalaha.game.validate.PitValidator;
import com.kalaha.game.validate.ValidationError;
import com.kalaha.game.validate.Validator;

public class Game {

	private int id;
	private Player player1;
	private Player player2;
	private Board board;
	private PlayerId turn;

	private GameStatus status;
	private GameResult gameResult;

	private Validator validator;

	public Game(int id, Player p1, Player p2) {
		this.id = id;
		this.player1 = p1;
		this.player2 = p2;
		this.board = new Board();
		turn = PlayerId.PLAYER_1;
		status = GameStatus.PLAYING;
		validator = new PitValidator();
	}

	/**
	 * Update board according to player's selected pit. Game rules are enforced.
	 *
	 * @param pitId
	 *            id of the selected pit
	 * @return corresponding error if validation fails, null otherwise.
	 *
	 */
	public ValidationError playGame(int pitId) {
		Pit pit = board.getPit(pitId);

		ValidationError error = validator.validate(this, pit, turn);
		if (error != null) {
			return error;
		}

		applyGameRules(pit);
		return null;
	}

	/**
	 * Apply game rules in order. Update turn accordingly.
	 *
	 */
	private void applyGameRules(Pit pit) {
		distributeStonesRule(pit);
		capturingStonesRule(pit);
		endGameRule(pit);

		if (board.getLastUpdatedPit().getType() != PitType.BIG_PIT) {
			turn = pit.getOwner().nextTurn();
		}
	}

	/**
	 * Distribute stones to the right, one in each of the following pits, including
	 * his own big pit. No stones are put in the opponents' big pit.
	 *
	 */
	private void distributeStonesRule(Pit pit) {

		int stoneCount = pit.getStoneCount();
		pit.setStoneCount(0);

		Pit nextPit = pit;
		while (stoneCount > 0) {
			nextPit = board.getNextPit(nextPit);

			if (nextPit.isValidPitForStoneDistribution(pit.getOwner())) {
				nextPit.addStone(1);
				stoneCount--;
			}
		}

		board.setLastUpdatedPit(nextPit);
	}

	/**
	 * When the last stone lands in an own empty pit, the player captures his own
	 * stone and all stones in the opposite pit
	 *
	 */
	private void capturingStonesRule(Pit pit) {
		Pit lastUpdatedPit = board.getLastUpdatedPit();

		if (lastUpdatedPit.getType() != PitType.BIG_PIT && pit.getOwner() == lastUpdatedPit.getOwner()
				&& lastUpdatedPit.getStoneCount() == 1) {
			Pit oppositePit = board.getOppositePit(lastUpdatedPit);
			if (oppositePit.getStoneCount() > 0) {
				Pit bigPit = board.getBigPit(pit.getOwner());
				bigPit.addStone(oppositePit.getStoneCount() + 1);
				oppositePit.setStoneCount(0);
				lastUpdatedPit.setStoneCount(0);
			}
		}
	}

	/**
	 * The game is over if one of the sides runs out of stones. The player
	 * who has stones in his pits puts them in his big pit.
	 *
	 */
	private void endGameRule(Pit pit) {
		int player1RegularPitTotal = board.getPlayer1RegularPitTotal();
		int player2RegularPitTotal = board.getPlayer2RegularPitTotal();

		if (player1RegularPitTotal == 0 || player2RegularPitTotal == 0) {

			Pit player1BigPit = board.getBigPit(PlayerId.PLAYER_1);
			int player1Total = player1RegularPitTotal + player1BigPit.getStoneCount();
			player1BigPit.setStoneCount(player1Total);

			Pit player2BigPit = board.getBigPit(PlayerId.PLAYER_2);
			int player2Total = player2RegularPitTotal + player2BigPit.getStoneCount();
			player2BigPit.setStoneCount(player2Total);

			setWinner(player1Total, player2Total);
			board.resetRegularPits();
			status = GameStatus.GAME_OVER;
		}
	}

	/**
	 * Determine the winner.
	 *
	 */
	private void setWinner(int player1Total, int player2Total) {
		if (player1Total > player2Total) {
			gameResult = GameResult.PLAYER_1;
		} else if (player2Total > player1Total) {
			gameResult = GameResult.PLAYER_2;
		} else {
			gameResult = GameResult.TIE;
		}
	}

	public Board getBoard() {
		return board;
	}

	public PlayerId getTurn() {
		return turn;
	}

	public int getId() {
		return id;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public GameStatus getStatus() {
		return status;
	}

	public GameResult getGameResult() {
		return gameResult;
	}
}
