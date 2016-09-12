package com.midzie.WeatherOnRoute.model.utils;

public class NextDaysWeather {
	String date;
	String highestTemperature;
	String lowestTemperature;
	String description;
	String day;

	
	public NextDaysWeather(String date, String highestTemperature, String lowestTemperature, String description,
			String day) {	
		this.date = date;
		setHighestTemperature(highestTemperature);
		setLowestTemperature(lowestTemperature);	
		this.description = description;
		this.day = day;
	
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHighestTemperature() {
		return highestTemperature;
	}

	public void setHighestTemperature(String highestTemperature) {
		Double temp = Double.parseDouble(highestTemperature);
		temp = round((temp - 32) / 1.8);
		this.highestTemperature = temp.toString() + " C";
	}

	public String getLowestTemperature() {
		return lowestTemperature;
	}

	public void setLowestTemperature(String lowestTemperature) {
		Double temp = Double.parseDouble(lowestTemperature);
		temp = round((temp - 32) / 1.8);
		this.lowestTemperature =  temp.toString() + " C";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	private double round(double value) {
		value *= 100;
		value = (double) Math.round(value);
		value /= 100;
		return value;
	}

	@Override
	public String toString() {
		return "NextDaysWeather [date=" + date + ", highestTemperature=" + highestTemperature + ", lowestTemperature="
				+ lowestTemperature + ", description=" + description + ", day=" + day + "]";
	}

}
