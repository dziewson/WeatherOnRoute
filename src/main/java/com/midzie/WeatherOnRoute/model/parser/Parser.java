package com.midzie.WeatherOnRoute.model.parser;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.midzie.WeatherOnRoute.model.manager.NetManager;
import com.midzie.WeatherOnRoute.model.utils.Coord;
import com.midzie.WeatherOnRoute.model.utils.ModelStaticValues;
import com.midzie.WeatherOnRoute.model.utils.Route;
import com.midzie.WeatherOnRoute.model.utils.Weather;

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

	private int getWeatherForCords(Coord coord) {		
		JSONObject weatherObj = netManager.getJSONFromURL(ModelStaticValues.YAHOO_WEATHER_URL + coord.getLatitude() + ModelStaticValues.COMMA + coord.getLongtitude() + ModelStaticValues.END_OF_YAHOO_WEATHER_URL);		
		Weather weather = new Weather();
		JSONObject query = weatherObj.getJSONObject("query");		
		JSONObject results = query.getJSONObject("results");
		JSONObject channel = results.getJSONObject("channel");
		JSONObject atmosphere = channel.getJSONObject("atmosphere");
		JSONObject wind = channel.getJSONObject("wind");
		JSONObject item = channel.getJSONObject("item");
		JSONObject condition = item.getJSONObject("condition");
		JSONObject location = channel.getJSONObject("location");		
		setWeather(weather, atmosphere, wind, condition, location);	
		System.out.println(weather);
		int x = 0;
		for(int index = 0 ; index < 1000000 ; index++) {
			x++;
		}
		return x;
	}

	private void setWeather(Weather weather, JSONObject atmosphere, JSONObject wind, JSONObject condition,
			JSONObject location) {
		weather.setHumidity(atmosphere.getString("humidity"));
		weather.setPressure(atmosphere.getString("pressure"));
		weather.setVisibility(atmosphere.getString("visibility"));		
		weather.setWindDirection(wind.getString("direction"));
		weather.setWindSpeed(wind.getString("speed"));
		weather.setTemperature(condition.getString("temp"));
		weather.setDescription(condition.getString("text"));	
		weather.setCity(location.getString("city"));
		weather.setCountry(location.getString("country"));
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
