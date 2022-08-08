package com.qa.todolistproject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todolistproject.domain.Coin;
import com.qa.todolistproject.repo.CoinRepo;

@SpringBootTest
public class ServiceUnitTesting {

	@Autowired
	private CoinService service;

	@MockBean
	private CoinRepo repo;

	// Testing a successful create
	@Test
	public void createCoin_ValidCoin_SaveCoin() {
		Coin validCoin = new Coin(1L, "One Pound", 1997, "Round Coin", "1 Pound", "UK", false);
		Coin saveCoin = new Coin("One Pound", 1997, "Round Coin", "1 Pound", "UK", false);

		Mockito.when(this.service.addCoin(saveCoin)).thenReturn(validCoin);
		assertEquals(validCoin, this.service.addCoin(saveCoin));
		Mockito.verify(this.repo, Mockito.times(1)).save(saveCoin);
	}

	// Testing a successful read all
	@Test
	public void readCoins_ValidCoins_Coins() {
		List<Coin> coins = new ArrayList<>();
		coins.add(new Coin(1L, "One Pound", 1997, "Round Coin", "1 Pound", "UK", false));

		Mockito.when(this.service.allCoins()).thenReturn(coins);
		assertEquals(coins, this.service.allCoins());
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	// Testing a successful read by id
	@Test
	public void readOneCoin_ValidCoin_Coin() {
		Coin validCoin = new Coin(1L, "One Pound", 1997, "Round Coin", "1 Pound", "UK", false);

		Mockito.when(this.repo.findById(validCoin.getId())).thenReturn(Optional.of(validCoin));
		assertThat(this.service.findCoinById(validCoin.getId())).isEqualTo(validCoin);
		Mockito.verify(this.repo, Mockito.times(1)).findById(validCoin.getId());
	}

	// Testing a successful update by id
	@Test
	public void updateCoin_ValidCoin_UpdatedCoin() {
		Coin coin = new Coin(1L, "One Pound", 1997, "Round Coin", "1 Pound", "UK", false);

		Coin newCoin = new Coin();
		coin.setCoin_name("Paul");

		Mockito.when(this.repo.findById(coin.getId())).thenReturn(Optional.of(coin));
		this.service.updateById(newCoin, coin.getId());

		Mockito.verify(this.repo, Mockito.times(1)).save(newCoin);
		Mockito.verify(this.repo, Mockito.times(1)).findById(coin.getId());
	}

	// Testing a successful delete by id
	@Test
	public void deleteCoin_Coin() {
		Coin validCoin = new Coin(1L, "One Pound", 1997, "Round Coin", "1 Pound", "UK", false);

		Mockito.when(this.repo.findById(validCoin.getId())).thenReturn(Optional.of(validCoin));
		this.service.deleteByCoinID(validCoin.getId());
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(validCoin.getId());
	}

	// Testing an unsuccessful delete by id where an invalid id is passed
	@Test
	public void deleteCoin_invalidCoin_Unsuccessful() {
		Coin invalidCoin = new Coin();
		invalidCoin.setId(89L);
		invalidCoin.setCoin_name("3 Pounds");
		invalidCoin.setYear(5000);

		Mockito.when(this.repo.findById(invalidCoin.getId())).thenReturn(Optional.ofNullable(null));
		this.service.deleteByCoinID(invalidCoin.getId());
	}

}
