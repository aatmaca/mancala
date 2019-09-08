package com.kalaha.game.model;

public class Computer extends Player {

	// We may want to inject a strategy to computer.
	private Strategy strategy;

	public Computer() {
		setName("Computer");
		strategy = new Strategy() {
		};
	}
}
