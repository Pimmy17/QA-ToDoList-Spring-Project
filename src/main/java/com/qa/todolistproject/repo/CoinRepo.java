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

//	@Query(value = "SELECT * FROM coin WHERE country = :country", nativeQuery = true)
//	public List<Coin> findByCountry(@Param("country") String country);
//
//	@Query(value = "SELECT * FROM coin WHERE year = ?1 ORDER BY country DESC, denomination DESC", nativeQuery = true)
//	public List<Coin> allYearCoins(@Param("year") int year);
//
	@Query(value = "SELECT * FROM coin WHERE in_collection = true", nativeQuery = true)
	public List<Coin> allCoinsInCollection();

}
