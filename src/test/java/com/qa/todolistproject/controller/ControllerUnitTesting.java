package com.qa.todolistproject.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolistproject.domain.Coin;
import com.qa.todolistproject.service.CoinService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ControllerUnitTesting {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private CoinService service;

	// Test a successful create coin
	@Test
	public void createCoinTest() throws Exception {
		Coin testCoin = new Coin("1 Pound", 2010, "Round Pound", "1 Pound", "United Kingdom", false);
		String testCoinAsJSON = this.mapper.writeValueAsString(testCoin);

		Mockito.when(this.service.addCoin(testCoin)).thenReturn(testCoin);

		mvc.perform(post("/createCoin").content(testCoinAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(content().json(testCoinAsJSON));

		Mockito.verify(this.service, Mockito.times(1)).addCoin(testCoin);
	}

	// Test a successful read all
	@Test
	public void readAllCoinTest() throws Exception {
		List<Coin> testCoin = new ArrayList<>();
		testCoin.add(new Coin(1L, "2 Pound", 1997, "Bi-metallic Coin", "2 Pound", "United Kingdom", false));
		String testCoinAsJSON = this.mapper.writeValueAsString(testCoin);

		Mockito.when(this.service.allCoins()).thenReturn(testCoin);

		mvc.perform(get("/coins").content(testCoinAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(testCoinAsJSON));

		Mockito.verify(this.service, Mockito.times(1)).allCoins();
	}

	// Test a successful read coin by id
	@Test
	public void readCoinByID_ValidCoin_Successful() throws Exception {
		Coin testCoin = new Coin(1L, "One Pound", 1997, "Round Coin", "1 Pound", "UK", false);
		String testCoinAsJSON = this.mapper.writeValueAsString(testCoin);

		Mockito.when(this.service.findCoinById(testCoin.getId())).thenReturn(testCoin);

		mvc.perform(get("/coins/" + testCoin.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(testCoinAsJSON))
				.andExpect(jsonPath("coin_name", is(testCoin.getCoin_name())))
				.andExpect(jsonPath("year", is(testCoin.getYear())));
	}

	// Test a successful update coin by id
	// ***************************Not working currently****************************
	@Test
	public void updateCoinById_AddToCollection_UpdateCoin() throws Exception {
		Coin testCoin = new Coin(1L, "Letter 'A' 10 Pence", 2020, "Part of the Alphabet Coin Series", "10 Pence",
				"United Kingdom", false);
		Coin newCoin = new Coin();
		testCoin.setInCollection(true);
		String testCoinAsJSON = this.mapper.writeValueAsString(testCoin);

		Mockito.when(this.service.updateById(newCoin, testCoin.getId())).thenReturn(true);

		mvc.perform(patch("/updateCoin/" + testCoin.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(testCoinAsJSON))
				.andExpect(jsonPath("inCollection", is(testCoin.getInCollection())));
	}

	// Test a successful delete by id
	@Test
	public void deleteCoin_ValidCoin_Successful() throws Exception {
		Coin testCoin = new Coin(1L, "2 Euro", 1977, "Rockall went back in time and joined the Euro", "2 Euro",
				"Rockall", false);
		String testCoinAsJSON = this.mapper.writeValueAsString(testCoin.getId());

		Mockito.when(this.service.deleteByCoinID(testCoin.getId())).thenReturn(true);

		mvc.perform(delete("/deleteCoin/" + testCoinAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	// Test an unsuccessful delete due to an invalid id
	@Test
	public void deleteCoin_invalidCoin_Unsuccessful() throws Exception {
		Coin invalidCoin = new Coin(55L, "2 Euro Mintmark 'A'", 2002, "One of the 5 Mints in Germany", "2 Euro",
				"Germany", false);
		String testInvalidCoinAsJSON = this.mapper.writeValueAsString(invalidCoin.getId());

		Mockito.when(this.service.deleteByCoinID(invalidCoin.getId())).thenReturn(false);

		mvc.perform(delete("/deleteCoin/" + testInvalidCoinAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

}
