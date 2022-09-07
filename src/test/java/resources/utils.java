package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	
	String apiResponse; JsonPath js; 

	public static RequestSpecification req;
	
	public RequestSpecification loginRequestSpecification() throws IOException {
		RequestSpecification req2 = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl")).build();
		return req2;
	}
	
	public RequestSpecification commonRequestSpecification(String token) throws IOException {
		if(req==null) {
			PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));
			req = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl")).addHeader("authorization", token)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			return req;
			}
			return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String jsonParsingUsingJsonPathClass(Response response, String key) {
		apiResponse = response.asString();
		js = new JsonPath(apiResponse);
		String value = js.get(key).toString();
		return value;
	}
}
