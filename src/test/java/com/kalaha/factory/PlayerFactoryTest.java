package com.kalaha.factory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.kalaha.game.factory.PlayerFactory;
import com.kalaha.game.model.Computer;
import com.kalaha.game.model.Player;
import com.kalaha.game.model.PlayerType;
import com.kalaha.game.model.User;

public class PlayerFactoryTest {

	@Test
	public void testInstanceOf_user() {
		PlayerFactory factory = new PlayerFactory();

		Player player = factory.getPlayer(PlayerType.USER, "test");
		assertThat(player, instanceOf(User.class));
	}

	@Test
	public void testInstanceOf_computer() {
		PlayerFactory factory = new PlayerFactory();

		Player player = factory.getPlayer(PlayerType.COMPUTER, null);
		assertThat(player, instanceOf(Computer.class));
	}
}
