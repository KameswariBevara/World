package com.cg.world.DTO;

	public class CityRegionDto {

	    private String cityName;
	    private String region;
	   
	    public CityRegionDto() {
	    	
	    }
	    public String getCityName() {
			return cityName;
		}


		public void setCityName(String cityName) {
			this.cityName = cityName;
		}


		public String getRegion() {
			return region;
		}


		public void setRegion(String region) {
			this.region = region;
		}


		public CityRegionDto(String cityName, String region) {
	        this.cityName = cityName;
	        this.region = region;
	    }

}
