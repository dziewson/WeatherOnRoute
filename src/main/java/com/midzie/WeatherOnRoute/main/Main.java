package com.midzie.WeatherOnRoute.main;

import java.util.Date;

import com.midzie.WeatherOnRoute.model.manager.NetManager;
import com.midzie.WeatherOnRoute.model.parser.Parser;

public class Main {

	public static void main(String[] args) {
	
		String from = "Łódź";
		String to = "Biłgoraj";
		Parser parser = new Parser();		
		Date date = new Date();		
		System.out.println(date);
		parser.getRoute(from, to, date);
		
	}

}
