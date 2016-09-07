package com.midzie.WeatherOnRoute.model.parser;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.midzie.WeatherOnRoute.model.manager.NetManager;
import com.midzie.WeatherOnRoute.model.utils.Coord;
import com.midzie.WeatherOnRoute.model.utils.ModelStaticValues;
import com.midzie.WeatherOnRoute.model.utils.Route;

public class Parser {
	NetManager netManager = new NetManager();

	public Route getRoute(String from, String to, Date date) {
		Route route = new Route();
		route.setFrom(from);
		route.setTo(to);
		route.setTravelTime(date);
		JSONArray step = getJSONElements(from, to, netManager);
		double dist = 0;
		double min = 0;
		for (int index = 0; index < step.length(); index++) {
			JSONObject steps = step.getJSONObject(index);
			JSONObject duration = steps.getJSONObject("duration");
			JSONObject distance = steps.getJSONObject("distance");
			JSONObject location = steps.getJSONObject("end_location");
			Double acutalDist = Double
					.parseDouble(distance.getString("text").substring(0, distance.getString("text").indexOf(" ")));
			min = parseMinutesFromStartPoint(min, duration);
			dist = setRoutCordsAndMinutes(route, dist, min, location, acutalDist);
		}
		return route;
	}

	private void getWeatherForCords(Coord coord) {
		JSONObject weatherObj = netManager.getJSONFromURL(ModelStaticValues.YAHOO_WEATHER_URL + coord.getLatitude() + ModelStaticValues.COMMA + coord.getLongtitude() + ModelStaticValues.END_OF_YAHOO_WEATHER_URL);
		int i = 0 ;
		while(i < 100000000) {
			i++;
		}		
		System.out.println(weatherObj);
	}

	private double setRoutCordsAndMinutes(Route route, double dist, double min, JSONObject location,
			Double acutalDist) {
		dist += acutalDist;
		if (dist > 20 || acutalDist > 20) {
			Coord coord = new Coord();
			coord.setLatitude(location.get("lat").toString());
			coord.setLongtitude(location.get("lng").toString());
			coord.setMinutes(min);
			getWeatherForCords(coord);
			route.getCoords().add(coord);
			dist = 0;
		}
		return dist;
	}

	private double parseMinutesFromStartPoint(double min, JSONObject duration) {
		String time = duration.getString("text");
		if (time.contains("hour")) {
			min = Double.parseDouble(time.substring(0, time.indexOf(" "))) * 60;
			min += Double.parseDouble(time.substring(time.indexOf('r') + 1, time.lastIndexOf(" ")).trim());
		} else {
			min += Double.parseDouble(time.substring(0, time.indexOf(" ")));
		}
		return min;
	}

	private JSONArray getJSONElements(String from, String to, NetManager netManager) {
		JSONObject jsonObject = netManager.getJSONFromURL(ModelStaticValues.GOOGLE_MAPS_API_URL + from
				+ ModelStaticValues.GOOGLE_MAPS_API_DESTINATION + to + ModelStaticValues.GOOGLE_MAPS_API_KEY);
		JSONArray routesArray = jsonObject.optJSONArray("routes");
		JSONObject rout = routesArray.getJSONObject(0);
		JSONArray legs = rout.getJSONArray("legs");
		JSONObject leg = legs.getJSONObject(0);
		JSONArray step = leg.getJSONArray("steps");
		return step;
	}

}
