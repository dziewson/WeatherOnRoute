package com.midzie.WeatherOnRoute.main;

import com.midzie.WeatherOnRoute.model.manager.NetManager;
import com.midzie.WeatherOnRoute.model.parser.Parser;

public class Main {

	public static void main(String[] args) {
	
		String from = "Katowice";
		String to = "Biłgoraj";
		Parser parser = new Parser();
		parser.getRoute(from, to);
		NetManager net = new NetManager();
	String temp = net.getStringFromURL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20%28SELECT%20woeid%20FROM%20geo.places%20WHERE%20text%3D%22%2851.4135356%2C19.6357025%29%22%29&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
	System.out.println(temp);
	}

}
