package com.joom.automation.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.protobuf.TextFormat.ParseException;

public class JsonForAdminUtility {
	public String readDataFromJson(String key) throws Throwable, IOException, ParseException {
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader(
				"D:\\HP1\\Desktop\\Workspace\\Workspace\\com.joom.automation\\src\\test\\resources\\configData\\commondataForAdmin.json"));
		// convert java object in to json object using down casting
		JSONObject map = (JSONObject) obj;
		String data = (String) map.get(key);

		return data;

	}

}
