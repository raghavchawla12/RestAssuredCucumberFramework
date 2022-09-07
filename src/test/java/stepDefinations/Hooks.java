package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@GetIndividualProduct")
	public void before_getProductIdFromAllProductsAPI() throws Throwable {
		StepDefination m = new StepDefination();
		if (StepDefination.dataSetList == null) {
			m.login_with("testRestAssured@yopmail.com", "Test1234");
			m.get_all_products_api_payload();
			m.user_calls_something_something_request("getAllProductsAPI", "POST");
			m.is_present_in_productname_in_getallproducts_api_response_data_tab("zara coat 3");
		}
	}
}
