package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClass.GetAllProductsRequest;
import pojoClass.LoginAPI;

public class TestDataBuilder {

	public LoginAPI loginAPIPayload(String emailId, String userPassword) {
		LoginAPI login = new LoginAPI();
		login.setUserEmail(emailId);
		login.setUserPassword(userPassword);
		return login;
	}

	public GetAllProductsRequest getAllProductPayload() {
		GetAllProductsRequest getall = new GetAllProductsRequest();
		List<String> list = new ArrayList<String>();
		getall.setProductName("");
		getall.setMinPrice(0);
		getall.setMaxPrice(1000000000);
		getall.setProductCategory(list);
		getall.setProductFor(list);
		getall.setProductSubCategory(list);
		return getall;
	}
}
