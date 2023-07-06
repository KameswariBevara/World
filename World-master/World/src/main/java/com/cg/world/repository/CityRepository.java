package com.cg.world.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.world.entity.City;
import com.cg.world.entity.Country;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    List<City> findAll();
    List<City> findByNameStartingWith(char ch);
    @Query("SELECT c FROM City c WHERE c.population = (SELECT MAX(c.population) FROM City c)")
    City findCityWithMaxPopulation();
    List<City> findTop10ByOrderByPopulationDesc();
//    @Query("SELECT c.name, cn.region FROM City c INNER JOIN c.country cn")
//    List<Object[]> getAllCityRegion();
//    List<City> findByCountryCode(String countryCode);
    @Query("SELECT DISTINCT c.district FROM City c")
    List<String> findDistinctDistricts();
    List<City> findByCountryCode(Country countryCode);
//    @Query("SELECT AVG(c.population) FROM City c WHERE c.district = :districtName")
//    Double findAveragePopulationByDistrict(@Param("districtName") String districtName);
//    City findByName(String name);
    
    
    
    
    }
