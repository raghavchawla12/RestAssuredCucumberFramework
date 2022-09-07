package resources;

public enum APIResources {
	

	loginAPI("/api/ecom/auth/login"),
	getAllProductsAPI("/api/ecom/product/get-all-products"),
	createProductAPI("will place later"),
	getIndividualProductDetail("/api/ecom/product/get-product-detail/{key}");
	
	private String resource;

	
	APIResources(String resource){
		this.resource = resource;
	}
	
	
	public String getResource() {
		return resource;
	}

}
