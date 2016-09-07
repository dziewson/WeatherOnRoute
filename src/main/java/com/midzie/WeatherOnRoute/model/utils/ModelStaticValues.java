package com.midzie.WeatherOnRoute.model.utils;

public class ModelStaticValues {
	public static final String GOOGLE_MAPS_API_URL = "https://maps.googleapis.com/maps/api/directions/json?origin=";
	public static final String GOOGLE_MAPS_API_DESTINATION = "&destination=";
	public static final String GOOGLE_MAPS_API_KEY = "&key=AIzaSyDecNgrnxnf_l-fMbUV4FcrAjRGJ1i4Xq0";
	public static final String YAHOO_WEATHER_URL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20%28SELECT%20woeid%20FROM%20geo.places%20WHERE%20text%3D%22%28";
	public static final String COMMA = "%2C";
	public static final String END_OF_YAHOO_WEATHER_URL = "%29%22%29&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&u=c";

}
