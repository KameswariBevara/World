package com.cg.world.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.world.entity.CountryLanguage;
import com.cg.world.service.CountryLanguageService;

@RestController
@RequestMapping("/api/countrylang")
public class CountryLanguageController {

	private final CountryLanguageService countryLanguageService;

	@Autowired
	public CountryLanguageController(CountryLanguageService countryLanguageService) {
		this.countryLanguageService = countryLanguageService;
	}

	@GetMapping
	public List<String> getAllUniqueLanguages() {
		return countryLanguageService.getAllUniqueLanguages();
	}

	@GetMapping("/{countrycode}")
	public ResponseEntity<List<CountryLanguage>> getLanguagesByCountryCode(
			@PathVariable("countrycode") String countryCode) {
		List<CountryLanguage> languages = countryLanguageService.getLanguagesByCountryCode(countryCode);
		if (languages.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(languages);
	}
	@GetMapping("/allofficial")
    public List<CountryLanguage> getAllOfficialLanguages() {
        return countryLanguageService.getAllOfficialLanguages();
    }
	@GetMapping("/unofficial/{countrycode}")
    public List<CountryLanguage> getUnofficialLanguagesByCountryCode(@PathVariable String countrycode) {
        return countryLanguageService.getUnofficialLanguagesByCountryCode(countrycode);
    }
	@GetMapping("/maxspokenlang/{countrycode}")
    public String getLanguageWithMaxPercentage(@PathVariable("countrycode") String countryCode) {
        String result = countryLanguageService.findLanguageWithMaxPercentage(countryCode);
        if (result != null) {
            return result;
        } else {
            return "No language found for the given country code.";
        }
    }
	@GetMapping("/max-percentages")
    public ResponseEntity<Map<String, BigDecimal>> getMaxPercentagesByCountryCode() {
        Map<String, BigDecimal> maxPercentages = countryLanguageService.getMaxPercentageByCountryCode();
        return ResponseEntity.ok(maxPercentages);
    }
//	@PatchMapping("/updatepercentage/{ctycode}/{lang}")
//    public ResponseEntity<String> updatePercentage(@PathVariable("ctycode") String countryCode, @PathVariable("lang") String language,@RequestBody CountryLanguage lang) {
//        String result = countryLanguageService.updatePercentageByCountryAndLanguage(countryCode, language,lang.getPercentage() );
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//	@GetMapping("/maxspokenlang")
//    public ResponseEntity<List<CountryLanguageDTO>> getLanguagesWithMaxPercentageByCountryCode() {
//        List<CountryLanguageDTO> languages = countryLanguageService.getLanguagesWithMaxPercentageByCountryCode();
//        return new ResponseEntity<>(languages, HttpStatus.OK);
//    }
	@PatchMapping("/updatepercentage/{ctycode}/{lang}")
	public ResponseEntity<String> updatePercentage(
	        @PathVariable String ctycode,
	        @PathVariable String lang,
	        @RequestParam BigDecimal percentage
	) {
	    String result = countryLanguageService.updatePercentageByCountryAndLanguage(ctycode, lang, percentage);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@PatchMapping("/setofficial/{ctycode}/{lang}")
    public ResponseEntity<String> updateIsOfficialFlag(
            @PathVariable String ctycode,
            @PathVariable String lang) {
        String isOfficial = "T"; // Set the desired IsOfficial value here

        String result = countryLanguageService.updateIsOfficialFlag(ctycode, lang, isOfficial);
        HttpStatus status = result.startsWith("Updated") ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(result, status);
    }

}
