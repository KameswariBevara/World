package com.cg.world.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
	@Id
	//@Column(length = 3)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
   @Column(name="code",columnDefinition = "char")
	
	private String code;

	@Column(columnDefinition = "char")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="enum('Africa','Asia','Europe','North America','Oceania','South America','Antarctica')")
	private Continent continent;

	@Column(columnDefinition = "char")
	private String region;

	@Column(name="surfacearea")
	private BigDecimal surfaceArea;

	@Column(name = "indepyear")

	private Short indepYear;

	@Column
	private Integer population;

	@Column(name="lifeexpectancy")
	private BigDecimal lifeExpectancy;

	@Column(name = "gnp")
	private BigDecimal gnp;

	@Column//(name = "GNP_old", precision = 10, scale = 2)
	private BigDecimal GNPOld;

	@Column(name = "localname",columnDefinition = "char")
	private String localName;

	@Column(name = "governmentform",columnDefinition = "char")
	private String governmentForm;

	@Column(name = "headofstate",columnDefinition = "char")
	private String headOfState;
	@Column
	private Integer capital;

	@Column(columnDefinition = "char")
	private String code2;
	
	@OneToMany(mappedBy = "countryCode", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<City> cities;
	
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CountryLanguage> countryLanguages;

    // Getters and setters
//    public List<City> getCities() {
//        return cities;
	
//	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<CountryLanguage> countryLanguages;
	
//	@OneToMany(mappedBy = "country")
//	private List<CountryLanguage> lang;
	
//	 @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	    private List<CountryLanguage> countryLanguages;
//	 
//	 @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	    private List<City> cities;


}
