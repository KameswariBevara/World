package com.cg.world.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.world.DTO.CountryDto;
import com.cg.world.entity.City;
import com.cg.world.entity.Country;
import com.cg.world.entity.CountryLanguage;
import com.cg.world.entity.utility.CountryData;
import com.cg.world.service.CountryService;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
	
	private CountryService cuService ;
	
	@Autowired
	public void setCuService(CountryService cuService) {
		this.cuService = cuService;
	}



	@GetMapping//(value = "/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = cuService.getAllCountries();
        ResponseEntity<List<Country>> re=new ResponseEntity<List<Country>>(countries,HttpStatus.OK);
        return re;
    }
	
	@GetMapping(value = "/{name}")
	public ResponseEntity<Country> getProduct(@PathVariable String name) {
		
		Country country=cuService.getOneCountry(name);
				
		ResponseEntity<Country> re=new ResponseEntity<Country>(country,HttpStatus.OK);
		return re;
		
	}
	 @GetMapping("/{countrycode}/population")
	    public String getPopulationAndLifeExpectancy(@PathVariable String countrycode) {
	        return cuService.populationLifeExpectency(countrycode);
	    }
	 
	 @GetMapping("/highestlifeexpectancy")
	    public Country getCountryWithHighestLifeExpectancy() {
	        return cuService.getCountryWithHighestLifeExpectancy();
	    }
	 @GetMapping("/uniquegovernmentforms")
	 public ResponseEntity<Collection<String>> getDistinctGovernmentForms() {
		 Collection<String> governmentForms = cuService.getDistinctGovernmentForms();
	        if (!governmentForms.isEmpty()) {
	            return ResponseEntity.ok(governmentForms);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @GetMapping("/toptenpopulated")
	    public ResponseEntity<Collection<CountryData>> getTop10PopulatedCountries() {
	        Collection<CountryData> top10PopulatedCountries = cuService.getTop10PopulatedCountries();

	        if (!top10PopulatedCountries.isEmpty()) {
	            return ResponseEntity.ok(top10PopulatedCountries);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @PatchMapping("/updateheadofstate/{name}")
	    public ResponseEntity<Country> updateHeadOfState(@PathVariable("name") String countryName,
	                                                     @RequestBody String newHeadOfState) {
		 Country updatedCountry = cuService.updateHeadOfState(countryName, newHeadOfState);
	        if (updatedCountry != null) {
	            return ResponseEntity.ok(updatedCountry);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	 
	 @PatchMapping("/updatepopulation/{name}")
	    public ResponseEntity<Country> updatePopulation(
	            @PathVariable("name") String countryName,
	            @RequestBody Map<String, Object> populationUpdate) {

	        int newPopulation = (int) populationUpdate.get("population");
	        Country updatedCountry = cuService.updatePopulation(countryName, newPopulation);
	        
	        if (updatedCountry != null) {
	            return ResponseEntity.ok(updatedCountry);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 @GetMapping("/{name}/citycount")
	    public String getCityCountByCountryName(@PathVariable("name") String countryName) {
	        return cuService.getCityCountByCountryName(countryName);
	    }
	 @GetMapping("/{name}/cities")
	    public List<String> getCityNamesByCountry(@PathVariable("name") String countryName) {
	        return cuService.getCityNamesByCountry(countryName);
	    }
	 @GetMapping("/{name}/capital")
	    public ResponseEntity<String> getCapitalCityByCountryName(@PathVariable("name") String countryName) {
	        City capitalCity = cuService.getCapitalCityByCountryName(countryName);
	        String obj = null;
	        if (capitalCity != null) {
	            obj = capitalCity.getName();
	        }
	        return new ResponseEntity<String>(obj,HttpStatus.OK);
	    }
	 @GetMapping(value="/{region}/alllanguages")
	    public List<CountryLanguage> getAllLanguagesByRegion(@PathVariable String region) {
	        return cuService.getAllLanguagesByRegion(region);
	    }
	 @GetMapping("/toptengnp")
	    public ResponseEntity<List<CountryDto>> getTopTenGNPCountries() {
	        List<CountryDto> countries = cuService.getTopTenGNPCountries();
	        return new ResponseEntity<>(countries, HttpStatus.OK);
	    }
	 @PatchMapping("/updategnp/{name}")
	    public ResponseEntity<Country> updateGNP(@PathVariable String name, @RequestParam BigDecimal gnp) {
	        Country updatedCountry = cuService.updateGNP(name, gnp);
	        if (updatedCountry != null) {
	            return ResponseEntity.ok(updatedCountry);
	        }
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
//	 @GetMapping("/toptengnp")
//	    public ResponseEntity<List<Country>> getTop10CountriesByGNP() {
//	        List<Country> top10Countries = cuService.getTop10CountriesByGNP();
//	        if (!top10Countries.isEmpty()) {
//	            return ResponseEntity.ok(top10Countries);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
	 
//	 @GetMapping("/{name}/capital")
//	 public ResponseEntity<String> getCapitalCity(@PathVariable("name") String countryName) {
//	        City capitalCity = cuService.getCapitalCityByCountryName(countryName);
//	        if (capitalCity != null) {
//	            String capitalCityName = capitalCity.getName();
//	            return ResponseEntity.ok(capitalCityName);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
	 
//	 @GetMapping(value = "/{region}/alllanguages")
//	    public ResponseEntity<ArrayList<CountryLanguage>> getLanguagesByRegion(@PathVariable("region") String region) {
//	       ArrayList<CountryLanguage> languages = cuService.getLanguagesByRegion(region);
//	        if (!languages.isEmpty()) {
//	            return ResponseEntity.ok(languages);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
}
