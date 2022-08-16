package com.qa.todolistproject.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String coin_name;

	@Column(nullable = false)
	private int year;

	@Column(nullable = false)
	private String coin_description;

	@Column(nullable = false)
	private String denomination;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private Boolean incollection;

	public Coin() {
	}

	public Coin(String coin_name, int year, String coin_description, String denomination, String country,
			Boolean incollection) {
		this.coin_name = coin_name;
		this.year = year;
		this.coin_description = coin_description;
		this.denomination = denomination;
		this.country = country;
		this.incollection = incollection;
	}

	public Coin(Long id, String coin_name, int year, String coin_description, String denomination, String country,
			Boolean incollection) {
		this.id = id;
		this.coin_name = coin_name;
		this.year = year;
		this.coin_description = coin_description;
		this.denomination = denomination;
		this.country = country;
		this.incollection = incollection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoin_name() {
		return coin_name;
	}

	public void setCoin_name(String coin_name) {
		this.coin_name = coin_name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCoin_description() {
		return coin_description;
	}

	public void setCoin_description(String coin_description) {
		this.coin_description = coin_description;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Boolean getInCollection() {
		return incollection;
	}

	public void setInCollection(Boolean incollection) {
		this.incollection = incollection;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coin_description, coin_name, denomination, country, incollection, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coin other = (Coin) obj;
		return Objects.equals(coin_description, other.coin_description) && Objects.equals(coin_name, other.coin_name)
				&& Objects.equals(denomination, other.denomination) && Objects.equals(country, other.country)
				&& Objects.equals(incollection, other.incollection) && year == other.year;
	}

	@Override
	public String toString() {
		return "Coin [id=" + id + ", coin_name=" + coin_name + ", year=" + year + ", coin_description="
				+ coin_description + ", denomination=" + denomination + ", country=" + country + ", inCollection="
				+ incollection + "]";
	}

}
