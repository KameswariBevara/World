package com.cg.world.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Table(name="city")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(columnDefinition = "char")
    private String name;
//	@Column
//    private String countryCode;
	@Column(columnDefinition = "char")
    private String district;
	@Column
    private Integer population;
	public City() {
		super();
	}
	
	@ManyToOne
    @JoinColumn(name = "countrycode",referencedColumnName = "code",columnDefinition = "char")
	
    private Country countryCode;
	
	

}
