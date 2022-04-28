package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class APITestBase {
    public RequestSpecification request = RestAssured.given();
    //Response responseToken;
    String baseUri = "https://supervillain.herokuapp.com";

    @BeforeTest
    public void setUp(){
        request.accept(ContentType.JSON);
        request.baseUri(baseUri);
        RestAssured.requestSpecification = request;
        System.out.println("Setting up request specification..");
    }
}
