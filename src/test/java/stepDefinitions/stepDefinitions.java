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
import packages.APIResources;
import packages.TestDatabuild;
import packages.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class stepDefinitions extends Utils {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDatabuild data = new TestDatabuild();

    @Given("Add Place Payload {string} , {string} , {string}")
    public void addPlacePayload(String name, String language, String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }
    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {
        // Write code here that turns the phrase above into concrete actions
        //Constructor will be called with value of resource which you pass
        APIResources resourcesAPI = APIResources.valueOf(resource);
        System.out.println(resourcesAPI.getResource());

        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST"))
            response = res.when().post(resourcesAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(resourcesAPI.getResource());

    }
    @Then("The API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), 200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectValue) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(getJsonPath(response,keyValue), expectValue);
    }
    @Then("Verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        //requestSpec
        String place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");

        String actualName = getJsonPath(response,"name");
        assertEquals(actualName, expectedName);

    }




}
