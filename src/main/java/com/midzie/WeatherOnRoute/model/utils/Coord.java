package com.midzie.WeatherOnRoute.model.utils;

public class Coord {
	private String latitude;
	private String longtitude;
	private double minutes;
	private Weather weather;
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}	

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "Coord [latitude=" + latitude + ", longtitude=" + longtitude + ", minutes=" + minutes + ", weather="
				+ weather + "]";
	}


	
}
