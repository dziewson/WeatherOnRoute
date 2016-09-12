package com.midzie.WeatherOnRoute.main;

import java.util.Date;

import com.midzie.WeatherOnRoute.model.parser.Parser;

public class Main {

	public static void main(String[] args) {

		String from = "Lodz";
		String to = "Sulejow";
		Parser parser = new Parser();
		Date date = new Date();		
		parser.getRoute(from, to, date);

	}

}
