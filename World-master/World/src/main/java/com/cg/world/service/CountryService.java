package com.cg.world.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.cg.world.DTO.CountryDto;
import com.cg.world.entity.City;
import com.cg.world.entity.Country;
import com.cg.world.entity.CountryLanguage;
import com.cg.world.entity.utility.CountryData;

public interface CountryService {
	public List<Country> getAllCountries();
	public Country getOneCountry(String name);
	public String getCityCountByCountryName(String countryName);
	public City getCapitalCityByCountryName(String countryName);
	
	public List<String> getCityNamesByCountry(String countryName);
	public Country getCountryWithHighestLifeExpectancy();
	public String populationLifeExpectency(String countryCode);
	 List<CountryLanguage> getAllLanguagesByRegion(String region);
	 public List<CountryDto> getTopTenGNPCountries();
	 Country updateGNP(String name, BigDecimal gnp);
	//public City getCapitalCityByCountryName(String name);
	//public ArrayList<CountryLanguage> getLanguagesByRegion(String region);
	public Collection<String> getDistinctGovernmentForms();
	public Collection<CountryData> getTop10PopulatedCountries();
	//public List<Country> getTop10CountriesByGNP();
	public Country updateHeadOfState(String countryCode, String newHeadOfState);
//	public Country updateGNP(String countryCode, BigDecimal newGNP);
	public Country updatePopulation(String countryCode, int newPopulation);
	
}
