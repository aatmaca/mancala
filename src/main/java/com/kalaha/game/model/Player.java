package com.kalaha.game.model;

import java.util.List;

public abstract class Player {
	private String name;

	// We may want to keep game history of the player.
	List<History> historyList;

	public Player() {
	}

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
}
