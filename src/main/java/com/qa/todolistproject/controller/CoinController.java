package com.qa.todolistproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolistproject.domain.Coin;
import com.qa.todolistproject.service.CoinService;

@RestController
@RequestMapping("/")
public class CoinController {

	@Autowired
	CoinService service;

	public CoinController(CoinService coinService) {
		this.service = coinService;
	}

	@GetMapping("/home")
	public String homePage() {
		return "<h1>Welcome!</h1>";
	}

	// Works
	@PostMapping("/createCoin")
	public ResponseEntity<Coin> addCoin(@RequestBody Coin coin) {
		Coin createCoin = service.addCoin(coin);
		return new ResponseEntity<Coin>(createCoin, HttpStatus.CREATED);
	}

	// Works
	@GetMapping("/coins")
	public ResponseEntity<List<Coin>> getAllCoins() {
		List<Coin> coinData = service.allCoins();
		return new ResponseEntity<List<Coin>>(coinData, HttpStatus.OK);
	}

	@GetMapping("/coins/{id}")
	public ResponseEntity<Coin> getCoinById(@PathVariable Long id) {
		Coin singleCoin = service.findCoinById(id);
		return new ResponseEntity<Coin>(singleCoin, HttpStatus.OK);
	}

	@GetMapping("/coins/mycollection")
	public ResponseEntity<List<Coin>> getCoinsInCollection() {
		List<Coin> coinCollection = service.allCoinsInCollection();
		return new ResponseEntity<List<Coin>>(coinCollection, HttpStatus.OK);
	}

//	@GetMapping("/coins/{country}")
//	public ResponseEntity<List<Coin>> getCoinsByCountry(@PathVariable String country) {
//		List<Coin> countryCoins = service.allCountryCoins(country);
//		return new ResponseEntity<List<Coin>>(countryCoins, HttpStatus.OK);
//	}

	@PatchMapping("updateCoin/{id}")
	public ResponseEntity<Coin> updateCoin(@PathVariable Long id, @RequestBody Coin coin) {
		Coin oldCoin = this.service.findCoinById(id);

		oldCoin.setInCollection(coin.getInCollection());
		Coin updatedCoin = this.service.save(oldCoin, id);
		return ResponseEntity.ok(updatedCoin);
	}

	@DeleteMapping("/deleteCoin/{id}")
	public ResponseEntity<Boolean> deleteCoin(@PathVariable Long id) {

		Boolean deletedCoin = service.deleteByCoinID(id);

		return (deletedCoin) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}

}
