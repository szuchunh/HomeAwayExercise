package com.homeaway.tests;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Map;

public class ExerciseTwoTests {
	
	private Integer id;
	private String stationName = "HYATT AUSTIN";
	private String location = "Austin TX";
	private String api_key = "FHJJ0GyGEonx9vjHgwNw1ygL7nLJScO71AYfHjlY";
	private String ev_network = "ChargePoint Network";
	
	
	@Test
	public void nearestStationTest() {
		
		//Make call to API
		Response response = createConnectionToRestAPI(location, api_key, ev_network, null);
		
		//Assert HYATT AUSTIN is in list of stations
		response.then().body("fuel_stations.station_name", hasItems(stationName));
		
		//Save station ID
		ArrayList<Map<String,?>> jsonAsArrayList = response.then().extract().path("fuel_stations");
		for (Map<String, ?> stations : jsonAsArrayList) {
			if (stations.get("station_name").equals(stationName)) {
				id = (Integer) stations.get("id");
			}
		}
		
		//Send another query to get verify street address
		response = createConnectionToRestAPI(null, api_key, null, id);
		
		//Assert address
		response.then().body("alt_fuel_station.street_address", equalTo("208 Barton Springs Rd"));
		response.then().body("alt_fuel_station.state", equalTo("TX"));
		response.then().body("alt_fuel_station.zip", equalTo("78704"));
		
	}
	
	//Method for calling API
    public Response createConnectionToRestAPI(String location, String api_key, String ev_network, Integer id) {
        RestAssured.baseURI = "https://developer.nrel.gov/api/alt-fuel-stations/v1";
        Response response = null;
        
        try {
			if (id == null) {
			    RequestSpecification reqSpecification = RestAssured.given().contentType("application/json").queryParam("api_key",api_key).queryParam("location",location).queryParam("ev_network",ev_network).when();
			    response = reqSpecification.get("/nearest.json");
			} else if (id != null) {
				RequestSpecification reqSpecification = RestAssured.given().contentType("application/json").queryParam("api_key", api_key).when();
			    response = reqSpecification.get("/"+id+".json");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        return response;
    }
}
