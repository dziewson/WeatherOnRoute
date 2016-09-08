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
		this.windSpeed = speed+" km/h";
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		double direction = Double.parseDouble(windDirection);
	
		this.windDirection = convertDegreeToCardinalDirection(direction);
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
	private String convertDegreeToCardinalDirection(double direction) {
		if( (direction >= 348.75 && direction <= 360) || (direction >= 0 && direction <= 11.25)) {
			return "N";
		}
		if(direction > 11.25 && direction <= 56.25) {
			return "NE";
		}
		if(direction > 56.25 && direction <= 101.25) {
			return "E";
		}
		if(direction > 101.25 && direction <= 146.25) {
			return "SE";
		}
		if(direction > 146.25 && direction <= 191.25 ) {
			return "S";
		}
		if(direction > 191.25 && direction <= 236.25) {
			return "SW";
		}
		if(direction > 236.25 && direction <= 281.25) {
			return "W";
		}
		if(direction > 281.25 && direction < 348.75) {
			return "NW";
		}
		return "Brak danych o kierunku wiatru";
	}

	
	@Override
	public String toString() {
		return "Weather [city=" + city + ", country=" + country + ", windSpeed=" + windSpeed + ", windDirection="
				+ windDirection + ", humidity=" + humidity + ", pressure=" + pressure + ", visibility=" + visibility
				+ ", temperature=" + temperature + ", description=" + description + "]";
	}
	
	
}
