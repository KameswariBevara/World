package com.cg.world.service;

import java.util.List;
import java.util.Map;

import com.cg.world.entity.City;

public interface CityService {
	List<City> getAllCities();
   List<String> getFirstTenCitiesStartingWithChar(char ch);
    City getCityWithMaxPopulation();
   List<String> getTop10PopulatedCityNames();
  

}
