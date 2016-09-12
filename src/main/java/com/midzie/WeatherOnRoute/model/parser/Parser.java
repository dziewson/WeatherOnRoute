package com.midzie.WeatherOnRoute.model.parser;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.midzie.WeatherOnRoute.model.manager.NetManager;
import com.midzie.WeatherOnRoute.model.utils.Coord;
import com.midzie.WeatherOnRoute.model.utils.ModelStaticValues;
import com.midzie.WeatherOnRoute.model.utils.NextDaysWeather;
import com.midzie.WeatherOnRoute.model.utils.Route;
import com.midzie.WeatherOnRoute.model.utils.Weather;

public class Parser {
	private NetManager netManager = new NetManager();

	public Route getRoute(String from, String to, Date date) {
		Route route = new Route();
		route.setFrom(from);
		route.setTo(to);
		route.setTravelTime(date);
		route.setWeatherOnStart(getWeatherForLocation(from, true));
		route.setWeatherOnEnd(getWeatherForLocation(to, true));
		JSONArray step = getJSONElements(from, to, netManager);
		double dist = 0;
		for (int index = 0; index < step.length(); index++) {
			JSONObject steps = step.getJSONObject(index);
			JSONObject distance = steps.getJSONObject("distance");
			JSONObject location = steps.getJSONObject("end_location");
			Double acutalDist = Double
					.parseDouble(distance.getString("text").substring(0, distance.getString("text").indexOf(" ")));

			dist = setRouteCords(route, dist, location, acutalDist);
		}
		System.out.println(route);
		return route;
	}

	private Weather getWeatherForLocation(Object location, boolean isStartOrEndLocation) {
		JSONObject weatherObj = null;
		weatherObj = getWeatherJSONObject(location, weatherObj);
		Weather weather = new Weather();
		JSONObject query = weatherObj.getJSONObject("query");
		JSONObject results = query.getJSONObject("results");
		JSONObject channel = null;
		if (!isStartOrEndLocation) {
			channel = results.getJSONObject("channel");
		} else {
			JSONArray channelArray = results.getJSONArray("channel");
			channel = channelArray.getJSONObject(0);
		}
		JSONObject atmosphere = channel.getJSONObject("atmosphere");
		JSONObject wind = channel.getJSONObject("wind");
		JSONObject item = channel.getJSONObject("item");
		JSONObject condition = item.getJSONObject("condition");
		JSONArray forecast = item.getJSONArray("forecast");
		for (int index = 0; index < forecast.length(); index++) {
			getNextDayWeather(weather, forecast, index);
		}
		JSONObject locationFromJSON = channel.getJSONObject("location");
		setWeather(weather, atmosphere, wind, condition, locationFromJSON);
		return weather;
	}

	private void getNextDayWeather(Weather weather, JSONArray forecast, int index) {
		JSONObject nextDayForecast = forecast.getJSONObject(index);
		String date = nextDayForecast.getString("date");
		String highTemp = nextDayForecast.getString("high");
		String lowTemp = nextDayForecast.getString("low");
		String description = nextDayForecast.getString("text");
		String day = nextDayForecast.getString("day");
		weather.getNextDaysWeather().add(new NextDaysWeather(date, highTemp, lowTemp, description, day));
	}

	private JSONObject getWeatherJSONObject(Object location, JSONObject weatherObj) {
		if (location instanceof Coord) {
			Coord coord = (Coord) location;
			weatherObj = netManager.getJSONFromURL(ModelStaticValues.YAHOO_WEATHER_URL + coord.getLatitude()
					+ ModelStaticValues.COMMA + coord.getLongtitude() + ModelStaticValues.END_OF_YAHOO_WEATHER_URL);

		} else if (location instanceof String) {
			String loc = (String) location;
			weatherObj = netManager.getJSONFromURL(
					ModelStaticValues.YAHOO_WEATHER_URL + loc + ModelStaticValues.END_OF_YAHOO_WEATHER_URL);

		}
		return weatherObj;
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

	private double setRouteCords(Route route, double dist, JSONObject location, Double acutalDist) {
		dist += acutalDist;
		if (dist > 20 || acutalDist > 20) {
			Coord coord = new Coord();
			coord.setLatitude(location.get("lat").toString());
			coord.setLongtitude(location.get("lng").toString());
			Weather weather = getWeatherForLocation(coord, false);
			if (!weather.getCity().equals(route.getWeatherOnStart().getCity())
					&& !weather.getCity().equals(route.getWeatherOnEnd().getCity())) {
				coord.setWeather(weather);
			}
			if (coord.getWeather() != null) {
				route.getCoords().add(coord);
			}
			dist = 0;
		}
		return dist;
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
