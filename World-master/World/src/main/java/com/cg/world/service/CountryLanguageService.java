package com.cg.world.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.cg.world.DTO.CountryLanguageDTO;
import com.cg.world.entity.CountryLanguage;

public interface CountryLanguageService {
	
	public List<String> getAllUniqueLanguages();
	public List<CountryLanguage> getLanguagesByCountryCode(String countryCode);
	List<CountryLanguage> getAllOfficialLanguages();
	public List<CountryLanguage> getUnofficialLanguagesByCountryCode(String countryCode);
//	public Map<String, CountryLanguage> getLanguagesWithMaxPercentageByCountryCode();
	public String findLanguageWithMaxPercentage(String countryCode); 
//	public String updatePercentageByCountryAndLanguage(String countryCode, String language, BigDecimal percentage);
	public String updateIsOfficialFlag(String countryCode, String language, String isOfficial);
	public String updatePercentageByCountryAndLanguage(String countryCode, String language, BigDecimal percentage);
	 //public List<CountryLanguageDTO> getLanguagesWithMaxPercentageByCountryCode();
	 public String getLanguageWithMaxPercentage(String countryCode);
	
	 public Map<String, BigDecimal> getMaxPercentageByCountryCode();
}
