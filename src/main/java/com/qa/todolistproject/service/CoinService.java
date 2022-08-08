package com.qa.todolistproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.todolistproject.domain.Coin;
import com.qa.todolistproject.repo.CoinRepo;

@Service
public class CoinService {

	@Autowired
	private CoinRepo repo;

	@Autowired
	public CoinService(CoinRepo repo) {
		this.repo = repo;
	}

	// Create
	public Coin addCoin(Coin coin) {
		return repo.save(coin);
	}

	// Read
	public Coin findCoinById(Long id) {
		return repo.findById(id).get();
	}

	public List<Coin> allCoins() {
		return this.repo.findAll();
	}

	// Update
	public boolean updateById(Coin updateCoin, Long id) {
		Optional<Coin> currentCoin = this.repo.findById(id);

		if (currentCoin.get() instanceof Coin) {
			Coin oldCoin = currentCoin.get();
			oldCoin.setCoin_description(updateCoin.getCoin_description());
			oldCoin.setCoin_name(updateCoin.getCoin_name());
			oldCoin.setDenomination(updateCoin.getDenomination());
			oldCoin.setCountry(updateCoin.getCountry());
			oldCoin.setInCollection(updateCoin.getInCollection());
			oldCoin.setYear(updateCoin.getYear());
			this.repo.save(oldCoin);
			return true;
		} else {
			return false;
		}
	}

	// Delete
	public boolean deleteByCoinID(Long id) {
		Optional<Coin> currentCoin = this.repo.findById(id);
		boolean isPresent = (currentCoin.isPresent()) ? true : false;
		if (isPresent) {
			this.repo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public Coin save(Coin oldCoin, Long id) {
		Optional<Coin> currentCoin = this.repo.findById(id);
		Coin coin = currentCoin.get();
		coin.setInCollection(oldCoin.getInCollection());
		return this.repo.save(oldCoin);

	}

}
