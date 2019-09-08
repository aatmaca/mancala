package com.kalaha.game.factory;

import com.kalaha.game.model.Computer;
import com.kalaha.game.model.Player;
import com.kalaha.game.model.PlayerType;
import com.kalaha.game.model.User;

public class PlayerFactory {
	/**
	 * Create a player
	 *
	 * @param type,
	 *            player type.
	 * @param name,
	 *            name of player.
	 *
	 */
	public Player getPlayer(PlayerType type, String name) {
		if (type == null) {
			return null;
		}

		switch (type) {
		case USER:
			return new User(name);
		case COMPUTER:
			return new Computer();
		default:
			break;
		}

		return null;
	}
}
