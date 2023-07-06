package com.cg.world.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.world.entity.Country;
import com.cg.world.entity.utility.CountryData;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
	public Country findByCode(String code);
	public Country findByName(String name);
	
	@Query("SELECT c.name FROM Country c WHERE c.code = :countryCode")
    List<String> findCityNamesByCountryCode(String countryCode);
	int countByCode(String code);
	Country findFirstByOrderByLifeExpectancyDesc();
//	@Query("SELECT c.city FROM Country c WHERE c.name = :countryName")
//	City findCapitalCityByCountryName(@Param("name") String name);
	// public List<Country> findByRegion(String region);
	List<Country> findByRegion(String region);
	List<Country> findTop10ByOrderByGnpDesc();
	@Query("SELECT DISTINCT c.governmentForm FROM Country c")
    Collection<String> findDistinctGovernmentForms();
	 @Query("SELECT new com.cg.world.entity.utility.CountryData(c.name, c.population) FROM Country c ORDER BY c.population DESC")
	public Collection<CountryData>findTop10ByOrderByPopulationDesc();
	//public List<Country> findTop10ByOrderByGnpDesc();
	 
}
