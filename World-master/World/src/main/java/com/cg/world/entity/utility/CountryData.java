package com.cg.world.entity.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CountryData {
	private String name;
    private int population;

    public CountryData(String name, int population) {
        this.name = name;
        this.population = population;
    }

}
