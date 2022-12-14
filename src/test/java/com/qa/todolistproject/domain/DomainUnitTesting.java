package com.qa.todolistproject.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import javax.validation.constraints.AssertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DomainUnitTesting {

	static Coin testCoin;

	@BeforeAll
	public static void buildCoin() {
		testCoin = new Coin(1L, "1 Pound", 2010, "Round Pound", "1 Pound", "United Kingdom", false);
	}

	// Tests the Hashcode and Equals
	@Test
	public void testHashcode() {
		Coin x = new Coin("1 Pound", 2010, "Round Pound", "1 Pound", "United Kingdom", false);
		Coin y = new Coin("1 Pound", 2010, "Round Pound", "1 Pound", "United Kingdom", false);
		Coin z = new Coin("2 Pound", 2012, "Big Coin", "Two Pound", "United Kingdom", false);
		assertTrue(x.equals(y) && y.equals(x));
		assertFalse(x.equals(z) && y.equals(z));
		assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void toString_String_CoinInstance() {
		String expected = "Coin [id=" + testCoin.getId() + ", coin_name=" + testCoin.getCoin_name() + ", year="
				+ testCoin.getYear() + ", coin_description=" + testCoin.getCoin_description() + ", denomination="
				+ testCoin.getDenomination() + ", country=" + testCoin.getCountry() + ", inCollection="
				+ testCoin.getInCollection() + "]";
		assertEquals(expected, testCoin.toString());
	}

	@Test
	public void constructor_NewCoin_Coin() {
		Coin coinOne = new Coin();
		assertTrue(coinOne instanceof Coin == true);

		Coin coinTwo = new Coin("2 Pound", 2015, "Bi-Metallic Coin", "2 Pound", "United Kingdom", false);
		assertTrue(coinTwo instanceof Coin == true);
		assertEquals("2 Pound", coinTwo.getCoin_name());
		assertEquals(2015, coinTwo.getYear());
		assertEquals("Bi-Metallic Coin", coinTwo.getCoin_description());

	}

}
