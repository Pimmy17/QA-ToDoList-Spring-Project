package com.qa.todolistproject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.todolistproject.domain.Coin;

@Repository
public interface CoinRepo extends JpaRepository<Coin, Long> {

	@Query(value = "SELECT * FROM coin", nativeQuery = true)
	public List<Coin> allCoins();

}
