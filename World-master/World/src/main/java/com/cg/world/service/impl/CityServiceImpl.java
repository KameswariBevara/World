package com.cg.world.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.world.entity.City;
import com.cg.world.repository.CityRepository;
import com.cg.world.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	private CityRepository cityRepository;
	@Autowired
	public void setCityRepository(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public List<City> getAllCities() {
		return cityRepository.findAll();
	}

	

	@Override
	public List<String> getFirstTenCitiesStartingWithChar(char ch) {
		List<City> cities = cityRepository.findByNameStartingWith(ch);
        List<String> cityNames = new ArrayList<>();

        int count = 0;
        for (City city : cities) {
            cityNames.add(city.getName());
            count++;
            if (count >= 10) {
                break;
            }
        }

        return cityNames;
	}

	@Override
	public City getCityWithMaxPopulation() {
		return cityRepository.findCityWithMaxPopulation();
	}

	@Override
	public List<String> getTop10PopulatedCityNames() {
		List<City> top10Cities = cityRepository.findTop10ByOrderByPopulationDesc();
        return top10Cities.stream().map(City::getName).collect(Collectors.toList());
	}

//	@Override
//	public Map<String, List<String>> getCitiesAndDistrictsByCountryCode(String countryCode) {
//		List<City> cities = cityRepository.findByCountryCode(countryCode);
//	    Map<String, List<String>> citiesAndDistricts = new HashMap<>();
//
//	    for (City city : cities) {
//	        String cityName = city.getName();
//	        List<String> districts = city.getDistricts().stream()
//	                .map(district -> district.getName())
//	                .collect(Collectors.toList());
//	        citiesAndDistricts.put(cityName, districts);
//	    }
//
//	    return citiesAndDistricts;
//	}

	
//	@Override
//	public List<Object[]> getAllCityRegion() {
//		return cityRepository.getAllCityRegion();
//	}
//	@Override
//	public List<CityRegionDto> getAllCitiesWithRegions() {
//		 List<City> cities = cityRepository.findAll();
//	        List<CityRegionDto> cityRegionDTOs = new ArrayList<>();
//
//	        for (City city : cities) {
//	            CityRegionDto cityRegionDTO = new CityRegionDto();
//	            cityRegionDTO.setCityName(city.getName());
//	            cityRegionDTO.setRegion(city.getCountryCode().getRegion());
//	            cityRegionDTOs.add(cityRegionDTO);
//	        }
//
//	        return cityRegionDTOs;
//	    
//	}
//
//	@Override
//	public List<CityRegionDto> getCitiesAndDistrictsByCountryCode(String countryCode) {
//		List<City> cities = cityRepository.findAll();
//        List<CityRegionDto> cityRegionDtos = new ArrayList<>();
//
//        for (City city : cities) {
//            String region = "";
//            if (city.getCountryCode() != null && city.getCountryCode().getRegion() != null) {
//                region = city.getCountryCode().getRegion();
//            }
//
//            cityRegionDtos.add(new CityRegionDto(city.getName(), region));
//        }
//
//        return cityRegionDtos;
//	}
//
//	@Override
//	public Collection<String> fetchCityNamesAndRegions() {
//		return cityRepository.findDistinctDistricts();
//	}
//
//	@Override
//	public List<String> getDistinctDistricts() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Double getAveragePopulationByDistrict(String districtName) {
//		 return cityRepository.findAveragePopulationByDistrict(districtName);
//	}
//
//	@Override
//	@Transactional
//	public City updateDistrictByCityName(String cityName, String newDistrict) {
//		City city = cityRepository.findByName(cityName);
//        if (city != null) {
//            city.setDistrict(newDistrict);
//            return cityRepository.save(city);
//        } else {
//            return null;
//        }
//    
//
//	}
//	

	
	
	

}
