package com.cg.world.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countrylanguage")
@Entity
public class CountryLanguage {
	

//	@Column
//	private String countryCode;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="language",nullable = false,columnDefinition = "char")
	private String language;

	@Enumerated(EnumType.STRING)
	@Column(name="isofficial",columnDefinition="enum('T','F')")
	private IsOfficial isOfficial;

	@Column
	private BigDecimal percentage;

	@ManyToOne
	@JoinColumn(name = "countrycode" )
	private Country country;
	
//	public String getCountryCode() {
//        if (country != null) {
//            return country.getCode();
//        }
//        return null;
//    }

}
