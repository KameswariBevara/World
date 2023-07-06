package com.cg.world.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.world.entity.City;
import com.cg.world.service.CityService;

@RestController
@RequestMapping(value="/api/cities")
public class CityController {

    private  CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities  = cityService.getAllCities();
		return new ResponseEntity<List<City>>(cities,HttpStatus.OK);
      //  return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping( value="/firsttencities/{ch}")
    public ResponseEntity<List<String>> getFirstTenCitiesStartingWithChar(@PathVariable char ch) {
        List<String> cities = cityService.getFirstTenCitiesStartingWithChar(ch);
        return ResponseEntity.ok(cities);
    }
    @GetMapping("/maxpopulated")
    public ResponseEntity<City> getCityWithMaxPopulation() {
        City city = cityService.getCityWithMaxPopulation();
        if (city != null) {
            return ResponseEntity.ok(city);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/toptenpopulatedcities")
    public List<String> getTop10PopulatedCities() {
        return cityService.getTop10PopulatedCityNames();
    }

    
    
    
    
    
    
    
       
    
}