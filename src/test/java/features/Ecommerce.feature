Feature: Ecommerce E2E API Test

@Login 
Scenario Outline: Verify that User is able to login to Ecommerce Application through API
		Given Login API Payload with "<email>", "<password>"
		When User calls "loginAPI" "POST" Request
		Then Status Code in "loginAPI" is 200
		And "message" in Response Body is "Login Successfully"
		And "userId" in Response Body is "6314675ac4d0c51f4f166db7"
		
Examples:
		| email 											| password 	|
		|	testRestAssured@yopmail.com	| Test1234	|
	#	|	testtestAssured@yopmail.com	| test1234	|
	
@GetAllProduct @Regression
Scenario Outline: Verify that Zara Coat3 Product is present in Get All Products API Response
	Given Login with "<email>", "<password>"
	And Get All Products API Payload
	When User calls "getAllProductsAPI" "POST" Request
	Then Status Code in "getAllProductsAPI" is 200
	And "zara coat 3" is present in productName in getAllProducts API Response Data Tab
	
Examples:
	 	| email 											| password 	|
		|	testRestAssured@yopmail.com	| Test1234	|

@GetIndividualProduct	@Regression
Scenario Outline: Verify that API is able to fetch Individual Product Detail Successfully
	Given Login with "<email>", "<password>"
	And Individual Product Detail Path Parameter
	When User calls "getIndividualProductDetail" "GET" Request
	Then Status Code in "getIndividualProductDetail" is 200

Examples:
	 | 	email 											| password 	|
	 |	testRestAssured@yopmail.com	| Test1234	|
	 
	 
	