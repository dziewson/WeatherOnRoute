package com.midzie.WeatherOnRoute.model.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Route {
	private List<Coord> coords = new ArrayList<>();
	private String from;
	private String to;
	private Date travelTime;
	private Weather weatherOnStart;
	private Weather weatherOnEnd;

	public Route() {
	}

	public Route(List<Coord> coords, String from, String to) {
		this.coords = coords;
		this.from = from;
		this.to = to;
	}

	public List<Coord> getCoords() {
		return coords;
	}

	public void setCoords(List<Coord> coords) {
		this.coords = coords;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Date travelTime) {
		this.travelTime = travelTime;
	}

	public Weather getWeatherOnStart() {
		return weatherOnStart;
	}

	public void setWeatherOnStart(Weather weatherOnStart) {
		this.weatherOnStart = weatherOnStart;
	}

	public Weather getWeatherOnEnd() {
		return weatherOnEnd;
	}

	public void setWeatherOnEnd(Weather weatherOnEnd) {
		this.weatherOnEnd = weatherOnEnd;
	}

	@Override
	public String toString() {
		return "Route [ from=" + from + ", to=" + to +", weatherOnStart=" + weatherOnStart + ", weatherOnEnd=" + weatherOnEnd + "]"+ " coords =" + coords ;
	}


	
}
