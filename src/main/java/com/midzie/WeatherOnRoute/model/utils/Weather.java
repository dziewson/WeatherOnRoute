package com.midzie.WeatherOnRoute.model.utils;

public class Weather {
	private String city;
	private String country;
	private String windSpeed; //
	private String windDirection; //
	private String humidity; //
	private String pressure; //
	private String visibility; //
	private String temperature; 
	private String description;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}	
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		Double speed = Double.parseDouble(windSpeed);
		speed *= 1.61;		
		this.windSpeed = speed.toString()+" km/h";
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity +  " %";
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure + " hPa";
	}
	public String getVisibility() {		
		return visibility;
	}
	public void setVisibility(String visibility) {
		Double visib = Double.parseDouble(visibility);
		visib *= 1.61;
		visib /= 1000;
		this.visibility = visib.toString() + " km";
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		Double temp = Double.parseDouble(temperature);
		temp = (temp-32)/1.8;
		this.temperature = temp.toString() + " C";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Weather [city=" + city + ", country=" + country + ", windSpeed=" + windSpeed + ", windDirection="
				+ windDirection + ", humidity=" + humidity + ", pressure=" + pressure + ", visibility=" + visibility
				+ ", temperature=" + temperature + ", description=" + description + "]";
	}
	
	
}
