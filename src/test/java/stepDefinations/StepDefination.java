package stepDefinations;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClass.DataGetAllProductsResponse;
import pojoClass.GetAllProductsResponse;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.utils;

public class StepDefination extends utils {

	TestDataBuilder testDataBuilder = new TestDataBuilder();
	RequestSpecification req;
	Response res;
	String apiResponse;
	static String token; static List<DataGetAllProductsResponse> dataSetList;

	@Given("Login API Payload with {string}, {string}")
	public void login_api_payload_with(String email, String password) throws IOException {
		req = RestAssured.given().spec(loginRequestSpecification()).contentType("application/json")
				.body(testDataBuilder.loginAPIPayload(email, password));
	}

	@When("User calls {string} {string} Request")
	public void user_calls_something_something_request(String resource, String httpMethod) throws Throwable {
		APIResources resourceAPI = APIResources.valueOf(resource);
		if (httpMethod.equalsIgnoreCase("POST"))
			res = req.when().post(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("GET"))
			res = req.when().get(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("DELETE"))
			res = req.when().delete(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("PUT"))
			res = req.when().put(resourceAPI.getResource()).then().extract().response();
	}

	@Then("Status Code in {string} is {int}")
	public void status_code_in_is(String string, Integer int1) {
		assertEquals(res.statusCode(), 200);
	}

	@Then("{string} in Response Body is {string}")
	public void in_response_body_is(String string, String string2) {
		jsonParsingUsingJsonPathClass(res, string);
	}

	@Given("Login with {string}, {string}")
	public void login_with(String email, String password) throws IOException {
		APIResources resourceAPI = APIResources.valueOf("loginAPI");
		RequestSpecification reqLogin = RestAssured.given().spec(loginRequestSpecification())
				.contentType("application/json").body(testDataBuilder.loginAPIPayload(email, password));
		Response resLogin = reqLogin.when().post(resourceAPI.getResource());
		token = jsonParsingUsingJsonPathClass(resLogin, "token");
	}

	@Given("Get All Products API Payload")
	public void get_all_products_api_payload() throws IOException {
		req = RestAssured.given().spec((commonRequestSpecification(token))).contentType("application/json")
				.body(testDataBuilder.getAllProductPayload());
	}

	@Then("{string} is present in productName in getAllProducts API Response Data Tab")
	public void is_present_in_productname_in_getallproducts_api_response_data_tab(String value) {
		GetAllProductsResponse responseGetAllProducts = res.as(GetAllProductsResponse.class);
		dataSetList = responseGetAllProducts.getData();
		for (int i = 0; i < dataSetList.size(); i++) {
			if (dataSetList.get(i).getProductName() == value) {
				assertEquals(true, true);
			}
		}
	}

	@Given("Individual Product Detail Path Parameter")
	public void individual_product_detail_path_parameter() throws IOException {
		String product_id = dataSetList.get(0).get_id();
		req = RestAssured.given().spec((commonRequestSpecification(token))).contentType("application/json")
				.pathParam("key", product_id);
	}
}