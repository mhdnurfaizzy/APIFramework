package stepDefinitions;

import BDD.POJO.addPlace;
import BDD.POJO.location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinitions {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;

    @Given("Add Place Payload")
    public void add_place_payload() {
        // Write code here that turns the phrase above into concrete actions
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        addPlace aps = new addPlace();

        aps.setAccuracy(50);
        aps.setName("Arindam Dalal");
        aps.setPhone_number("(+91) 983 893 3937");
        aps.setAddress("B-408,Arcadia,Sinhagad Road,Pune,Maharashtra,India");
        aps.setWebsite("http://google.com");
        aps.setLanguage("French-IN");
        List<String> typelist = new ArrayList<String>();
        typelist.add("shoe park");
        typelist.add("shop");
        aps.setTypes(typelist);
        location loc = new location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);

        aps.setLocation(loc);

        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        res = given().spec(req).body(aps);
    }
    @When("User calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions
        response = res.when().post("/maps/api/place/add/json")
                .then().spec(resSpec).extract().response();
    }
    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), 200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectValue) {
        // Write code here that turns the phrase above into concrete actions
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        assertEquals(js.get(keyValue).toString(), expectValue);
    }




}
