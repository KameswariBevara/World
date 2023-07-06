package com.cg.world.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.world.entity.CountryLanguage;
import com.cg.world.entity.IsOfficial;

@Repository
public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, String> {
	@Query("SELECT DISTINCT cl.language FROM CountryLanguage cl")
    List<String> findDistinctLanguage();
	List<CountryLanguage> findByLanguage(String language);
	List<CountryLanguage> findByCountryCode(String countryCode);
	List<CountryLanguage> findAllByIsOfficial(IsOfficial isOfficial);
	List<CountryLanguage> findByCountryCodeAndIsOfficial(String countryCode, IsOfficial isOfficial);
	 @Query(value = "SELECT cl.language, cl.percentage FROM CountryLanguage cl " +
	            "WHERE cl.country.code = :countryCode " +
	            "AND cl.percentage = (SELECT MAX(cl2.percentage) FROM CountryLanguage cl2 WHERE cl2.country.code = :countryCode)")
	    Object[] findLanguageWithMaxPercentage(String countryCode);
	CountryLanguage findByCountryCodeAndLanguage(String countryCode, String language);
//	@Query("SELECT cl FROM CountryLanguage cl WHERE cl.percentage = (SELECT MAX(cl2.percentage) FROM CountryLanguage cl2 WHERE cl2.country.countryCode = cl.country.countryCode)")
//    List<CountryLanguage> findLanguagesWithMaxPercentageByCountryCode();
	
	//Optional<CountryLanguage> findByCountryCodeAndLanguage(String countryCode, String language);
}
