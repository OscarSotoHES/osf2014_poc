package controllers;

import models.Citizen;
import models.City;

import com.fasterxml.jackson.databind.JsonNode;

import play.mvc.*;
import play.mvc.BodyParser.Json;
import play.cache.Cache;

public class Application extends Controller {

	//Switch to enable the cache.
	private static boolean cache = false;

    public static Result index() {
		//this.cache = true;
    	City city = new City("Paris");
        City.create(city);
    	City city1 = new City("London");
        City.create(city1);
        return ok("City & Citizen. Server 1");
    }
	
	//HELPER FUNCTIONS
	private static String getCache(String key) { return String.valueOf(Cache.get(key));}
	private static void setCache(String key, String value, int duration) { Cache.set(key, value, duration);}
    
	//CITIES
    public static Result allCities() {
		String cities = getCache("cities");
		if (cities == null || cities.equals("null")){
			cities = City.find.all().toString();
			if (cache) { setCache("cities", cities, 600);}  
		}
		return ok(cities + "\n\n\n" + City.find.findSet().toString() + "\n\n\n" + City.find.findList().toString());
    }
	public static Result getCity(Long idc) {
		String city = getCache("city_"+idc);
		if (city == null || city.equals("null")){
			city = City.find.ref(idc).toString();
			if (cache) { setCache("city_"+idc, city, 600);}
		}
        return ok(city);
    }
  
    @BodyParser.Of(Json.class)
    public static Result addCity() {
    	JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        if(name == null) {
            return badRequest("Missing parameter [name]");
        } 
        else if(name.equals(""))
        	return badRequest("Empty name");
        else {
        	City city = new City(name);
            City.create(city);
	    Cache.remove("cities");
            return created("City created with name: "+name);
        }
    }
    
    @BodyParser.Of(Json.class)
    public static Result updateCity(Long idc) {
    	JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        if(name == null) {
            return badRequest("Missing parameter [name] to update this city");
        } 
        else if(name.equals(""))
        	return badRequest("Empty name");
        else {
        	String old_name = City.find.ref(idc).getName();
        	City.find.ref(idc).setName(name);
            City.update(idc);
	    Cache.remove("cities");
	    Cache.remove("citiy_"+idc);
            return created("City " + old_name + " updated with name: "+name);
        }
    }
  
    public static Result deleteCity(Long idc) {
    	City.delete(idc);
	Cache.remove("cities");
	Cache.remove("city_"+idc);
    	return ok("City with id: "+idc+" is deleted");
    }
	//END CITIES
	
	//CITIZEN
    public static Result allCitizens() {
		String citizens = getCache("citizens");
		if (citizens == null || citizens.equals("null")){
			citizens = Citizen.find.all().toString();
			if (cache) { setCache("citizens", citizens, 600);}
		}
        return ok(citizens);
    }
    
    public static Result getCitizensOfCity(Long idc) {
		String citizens = getCache("cityCitizens_"+idc);
		if (citizens == null || citizens.equals("null")){
			citizens = City.find.ref(idc).getCitizen().toString();
			if (cache) { setCache("cityCitizens_"+idc, citizens, 600);}
		}
        return ok(citizens);
    }
  
    @BodyParser.Of(Json.class)
    public static Result addCitizen(Long idc) {
    	JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        String first_name = json.findPath("fname").textValue();
        if(name == null) {
            return badRequest("Missing parameter [name]");
        }
        else if(first_name == null) {
            return badRequest("Missing parameter [fname]");
        }
        else if(name.equals("")||first_name.equals(""))
        	return badRequest("Empty name or first name");
        else {
        	Citizen citizen = new Citizen(first_name, name);
            citizen.setCity(City.find.ref(idc));
            Citizen.create(citizen);
	    Cache.remove("citizens");
            return created("Citizen created with name: "+first_name+" "+name+" in city "+City.find.ref(idc).toString());
        }
    }
    
    @BodyParser.Of(Json.class)
    public static Result updateCitizen(Long id) {
    	JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        String first_name = json.findPath("fname").textValue();
        if(name == null) {
            return badRequest("Missing parameter [name] to update this citizen");
        } 
        else if(first_name == null) {
            return badRequest("Missing parameter [fname] to update this citizen");
        }
        else if(name.equals("") || first_name.equals(""))
        	return badRequest("Empty name or first name");
        else {
        	String old_first_name = Citizen.find.ref(id).getFirstName();
        	String old_name = Citizen.find.ref(id).getName();
        	Citizen.find.ref(id).setFirstName(first_name);
        	Citizen.find.ref(id).setName(name);
            	Citizen.update(id);
		Cache.remove("citizens");
		Cache.remove("cityCitizens_"+idc);
            return created("Citizen " + old_first_name + " " + old_name + " updated with name: " + first_name + " " + name);
        }
    }
  
    public static Result deleteCitizen(Long idc, Long id) {
    	Citizen.delete(id);
	Cache.remove("citizens");
	Cache.remove("cityCitizens_"+idc);
    	return ok("Citizen with id: "+id+" is deleted");
    }
	//END CITIZEN
}
