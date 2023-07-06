package com.cg.world.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.world.DTO.CountryLanguageDTO;
import com.cg.world.entity.CountryLanguage;
import com.cg.world.entity.IsOfficial;
import com.cg.world.repository.CountryLanguageRepository;
import com.cg.world.service.CountryLanguageService;

@Service
@Transactional
public class CountryLanguageServiceImpl implements CountryLanguageService {

	private  CountryLanguageRepository countryLanguageRepository;

	@Autowired
    public CountryLanguageServiceImpl(CountryLanguageRepository countryLanguageRepository) {
        this.countryLanguageRepository = countryLanguageRepository;
    }
	@Override
	public List<String> getAllUniqueLanguages() {
		 return countryLanguageRepository.findDistinctLanguage();
	}
	@Override
	public List<CountryLanguage> getLanguagesByCountryCode(String countryCode) {
		return countryLanguageRepository.findByCountryCode(countryCode);
	}
	@Override
	public List<CountryLanguage> getAllOfficialLanguages() {
		return countryLanguageRepository.findAllByIsOfficial(IsOfficial.T);
	}
	@Override
	public List<CountryLanguage> getUnofficialLanguagesByCountryCode(String countryCode) {
		return countryLanguageRepository.findByCountryCodeAndIsOfficial(countryCode, IsOfficial.F);
	}
//	@Override
//	public Map<String, CountryLanguage> getLanguagesWithMaxPercentageByCountryCode() {
//		List<CountryLanguage> allCountryLanguages = countryLanguageRepository.findAll();
//
//        return allCountryLanguages.stream()
//                .collect(Collectors.groupingBy(CountryLanguage::getCountryCode,
//                        Collectors.collectingAndThen(
//                                Collectors.maxBy((cl1, cl2) -> cl1.getPercentage().compareTo(cl2.getPercentage())),
//                                Optional::get
//                        )));
//	}
	@Override
	public String findLanguageWithMaxPercentage(String countryCode) {
		Object[] result = countryLanguageRepository.findLanguageWithMaxPercentage(countryCode);
	    if (result != null) { //&& result.length >= 2) {
	        String language = (String) result[0];
	        BigDecimal percentage = (BigDecimal) result[1];
	        return "Language: " + language + ", Percentage: " + percentage;
	    }
	    return "No language found for the given country code.";
	}
	
//	
//	@Override
//	public String updateIsOfficialFlag(String countryCode, String language, String isOfficial) {
//		 CountryLanguage countryLanguage = countryLanguageRepository.findByCountryCodeAndLanguage(countryCode, language);
//	        if (countryLanguage != null) {
//	            countryLanguage.setIsOfficial(parseIsOfficial(isOfficial));
//	            countryLanguageRepository.save(countryLanguage);
//	            return "Updated IsOfficial value";
//	        } else {
//	            return "Country Language not found";
//	        }
//	    }
//
//	    private IsOfficial parseIsOfficial(String isOfficial) {
//	        if (isOfficial.equalsIgnoreCase("T")) {
//            return IsOfficial.T;
//	        } else {
//	            return IsOfficial.F;
//	        }
//	    }
	public String updateIsOfficialFlag(String countryCode, String language, String isOfficial) {
        CountryLanguage countryLanguage = countryLanguageRepository.findByCountryCodeAndLanguage(countryCode, language);
        
        if (countryLanguage != null) {
            IsOfficial isOfficialEnum = IsOfficial.valueOf(isOfficial.toUpperCase()); // Convert String to IsOfficial enum
            countryLanguage.setIsOfficial(isOfficialEnum);
            countryLanguageRepository.save(countryLanguage);
            return "Updated successfully";
        } else {
            return "No record found";
        }
    }
	

	@Override
	public String updatePercentageByCountryAndLanguage(String countryCode, String language, BigDecimal percentage) {
		String s = new String();
		List<CountryLanguage> countryLanguages = countryLanguageRepository.findByLanguage(language);
		for (CountryLanguage countryLanguage : countryLanguages) {
			if(countryLanguage.getCountry().getCode().equals(countryCode))
					{
						countryLanguage.setPercentage(percentage);
						countryLanguageRepository.save(countryLanguage);
						s = "Percentage updated successfully. "+percentage;
						break;
						
					}
			else
			{
				s = "Country language not found.";
			}
			
		
		}
		return s;
	}
//	@Override
//	public List<CountryLanguageDTO> getLanguagesWithMaxPercentageByCountryCode() {
//		List<CountryLanguage> languages = countryLanguageRepository.findLanguagesWithMaxPercentageByCountryCode();
//        List<CountryLanguageDTO> result = new ArrayList<>();
//
//        for (CountryLanguage language : languages) {
//            CountryLanguageDTO dto = new CountryLanguageDTO();
//            dto.setCountryCode(language.getCountry().getCode());
//            dto.setLanguage(language.getLanguage());
//            result.add(dto);
//        }
//
//        return result;
//    
//	}
	@Override
	public String getLanguageWithMaxPercentage(String countryCode) {
		 List<CountryLanguage> languages = countryLanguageRepository.findByCountryCode(countryCode);
		    
		    if (languages.isEmpty()) {
		        return null; // Or throw an exception indicating no languages found for the given country code
		    }
		    
		    CountryLanguage languageWithMaxPercentage = Collections.max(languages, Comparator.comparing(CountryLanguage::getPercentage));
		    
		    return languageWithMaxPercentage.getLanguage() + " - " + languageWithMaxPercentage.getPercentage().toString();
	}
	@Override
	public Map<String, BigDecimal> getMaxPercentageByCountryCode() {
        List<CountryLanguage> countryLanguages = countryLanguageRepository.findAll();
        
        // Group country languages by country code and calculate the maximum percentage
        Map<Object, Object> maxPercentages = countryLanguages.stream()
                .collect(Collectors.groupingBy(
                        countryLanguage -> countryLanguage.getCountry().getCode(),
                        Collectors.mapping(CountryLanguage::getPercentage, Collectors.collectingAndThen(Collectors.maxBy(BigDecimal::compareTo), Optional::get))
                ));

        return (Map<String, BigDecimal>) (Map<?, ?>) maxPercentages;
    }
	
	
	



	

}
