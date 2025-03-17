package com.joom.automation.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonForUserUtility {
	public String readDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException  {
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader("C:\\Users\\HP\\git\\repository8\\com.joom.automation\\src\\test\\resources\\configData\\CommondataForUser.json"));
		JSONObject map = (JSONObject) obj;
		String value = (String) map.get(key);
		return value;
		
	}

}
