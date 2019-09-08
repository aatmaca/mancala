package com.kalaha.game.model;

import com.kalaha.game.util.PitType;
import com.kalaha.game.util.PlayerId;

public class Pit {

	private int id;
	private PitType type;
	private PlayerId owner;
	private int stoneCount;

	public Pit(int id, PitType type, PlayerId owner, int stoneCount) {
		this.id = id;
		this.type = type;
		this.owner = owner;
		this.stoneCount = stoneCount;
	}

	public int getId() {
		return id;
	}

	public PitType getType() {
		return type;
	}

	public PlayerId getOwner() {
		return owner;
	}

	public int getStoneCount() {
		return stoneCount;
	}

	public void addStone(int increment) {
		this.stoneCount += increment;
	}

	public void setStoneCount(int stoneCount) {
		this.stoneCount = stoneCount;
	}

	public boolean isValidPitForStoneDistribution(PlayerId stoneOwner) {
		return (type != PitType.BIG_PIT) || (owner == stoneOwner);
	}
}
