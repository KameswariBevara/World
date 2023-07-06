package com.cg.world.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.world.DTO.CountryDto;
import com.cg.world.entity.City;
import com.cg.world.entity.Country;
import com.cg.world.entity.CountryLanguage;
import com.cg.world.entity.utility.CountryData;
import com.cg.world.repository.CityRepository;
import com.cg.world.repository.CountryLanguageRepository;
import com.cg.world.repository.CountryRepository;
import com.cg.world.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService
{
	private CountryRepository countryRepository;
	
	
	@Autowired
	public void setCountryRepository(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}
	
	private CountryLanguageRepository languageRepository;
	@Autowired
	public void setLanguageRepository(CountryLanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
	
	private CityRepository cityRepository;
	@Autowired
	public void setCityRepository(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public List<Country> getAllCountries() {
		 return countryRepository.findAll();
		 
	}

	@Override
	public Country getOneCountry(String name) {
		return countryRepository.findByName(name);
		 
	}
	
	
	
	public String populationLifeExpectency(String countryCode)
	{
		Country country = countryRepository.findByCode(countryCode);
		String s;
		String s1 = Integer.toString(country.getPopulation());
		String s2 = String.valueOf(country.getLifeExpectancy());
		s = "Country Code: "+countryCode+" "+"Population: "+s1+" Life Expectancy: "+ s2;
		return s;
	}

//	@Override
//	public List<City> cities(String countryName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

	

	 @Override
	    public List<String> getCityNamesByCountry(String countryName) {
	        Country country = countryRepository.findByName(countryName);
	        if (country == null) {
	            // Handle the case when the country does not exist
	            throw new IllegalArgumentException("Country not found");
	        }

	        List<String> cityNames = country.getCities().stream()
	                .map(City::getName)
	                .collect(Collectors.toList());

	        return cityNames;
	    }
	

	@Override
	public Collection<String> getDistinctGovernmentForms() {
		 List<Country> countries = countryRepository.findAll();
	        Set<String> governmentForms = new HashSet<>();
	        for (Country country : countries) {
	            governmentForms.add(country.getGovernmentForm());
	        }
	        return governmentForms;
	    }

	@Override
	public Collection<CountryData> getTop10PopulatedCountries() {
		Collection<CountryData> countries = countryRepository.findTop10ByOrderByPopulationDesc();
        List<CountryData> countryDataList = new ArrayList<>();

        for (CountryData country : countries) {
            CountryData countryData = new CountryData(country.getName(), country.getPopulation());
            countryDataList.add(countryData);
        }

        return countryDataList;
    }

	@Override
	    public Country updateHeadOfState(String countryCode, String newHeadOfState) {
	        Country country = countryRepository.findByCode(countryCode);
	        if (country == null) {
	            throw new EntityNotFoundException("Country with code " + countryCode + " not found.");
	        }

	        country.setHeadOfState(newHeadOfState);
	        return countryRepository.save(country);
	    }

	@Override
    public Country updateGNP(String name, BigDecimal gnp) {
        Country country = countryRepository.findByName(name);
        if (country != null) {
            country.setGnp(gnp);
            return countryRepository.save(country);
        }
        return null;
    }

	@Override
	public Country updatePopulation(String countryCode, int newPopulation) {
		Country country = countryRepository.findByCode(countryCode);
        if (country == null) {
            throw new EntityNotFoundException("Country with code " + countryCode + " not found.");
        }

        country.setPopulation(newPopulation);
        return countryRepository.save(country);
    
	}

	@Override
	public String getCityCountByCountryName(String countryName) {
		Country country = countryRepository.findByName(countryName);
        if (country != null) {
            int cityCount = country.getCities().size();
            return "Total count of cities in " + countryName + ": " + cityCount;
        } else {
            return "Country not found.";
        }
    
	}

	@Override
	public Country getCountryWithHighestLifeExpectancy() {
		return countryRepository.findFirstByOrderByLifeExpectancyDesc();
	}

	@Override
	public City getCapitalCityByCountryName(String countryName) {
		Country country = countryRepository.findByName(countryName);
        return cityRepository.findById(country.getCapital()).get();
	}

	@Override
	public List<CountryLanguage> getAllLanguagesByRegion(String region) {
		List<Country> countries = countryRepository.findByRegion(region);
		List<CountryLanguage> allLanguages = new ArrayList<>();

        if(countries!=null) {
        	for (Country country : countries) {
                List<CountryLanguage> countryLanguages = country.getCountryLanguages();
                allLanguages.addAll(countryLanguages);
            }
        }

        return allLanguages;
    
	}

	@Override
	public List<CountryDto> getTopTenGNPCountries() {
		List<Country> countries = countryRepository.findTop10ByOrderByGnpDesc();
        return countries.stream()
                .map(country -> new CountryDto(country.getName(), country.getGnp()))
                .collect(Collectors.toList());
    
	}
	

	

	

	

	

//	@Override
//	public List<Country> getTop10CountriesByGNP() {
//		return countryRepository.findTop10ByOrderByGnpDesc();
//	}
	

	




//	@Override
//	public ArrayList<CountryLanguage> getLanguagesByRegion(String region) {
//		List<Country> countries = countryRepository.findByRegion(region);
//		ArrayList<CountryLanguage> languages = new ArrayList<CountryLanguage>();
//        for(Country c:countries) {
//        	List<CountryLanguage> lang = languageRepository.findByCountry(c.getCode());
//        	for(CountryLanguage l:lang) {
//        		languages.add(l);
//        	}
//        }
//
//        
//        return languages;
//	}





	





//	@Override
//	public City getCapitalCityByCountryName(String name) {
//		 return countryRepository.findCapitalCityByCountryName(name);
//	}

}
