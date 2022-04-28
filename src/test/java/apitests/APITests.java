package apitests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class APITests extends APITestBase{

    @Test
    public void generateToken(ITestContext context) {
        String key = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        ObjectMapper om = new ObjectMapper();
        ObjectNode requestNode = om.createObjectNode();
        requestNode.put("key", key);
        requestNode.put("email", "crazyFrog@yopmail.com");

        System.out.println("Request body: " + requestNode.toPrettyString());

        RestAssured.baseURI = "https://supervillain.herokuapp.com";
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.body(requestNode.toString());
        request.basePath("/auth/gentoken");

        Response response = request.post();

        if(response.getStatusCode() == 200){
            JsonNode node = response.getBody().as(JsonNode.class);
            String token = node.get("emailTo").asText();
            System.out.println("Token generated: " + token);
            context.setAttribute("token", token);
        }else{
            Assert.fail();
        }
    }

    @Test(dependsOnMethods = {"generateToken"})
    public void registerNewUserViaAPI(ITestContext context){
        String username = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        String password = "praob";

        ObjectMapper om = new ObjectMapper();
        ObjectNode requestNode = om.createObjectNode();
        requestNode.put("username", username);
        requestNode.put("password", password);

        String token = context.getAttribute("token").toString();

        Response response = RestAssured.given().baseUri("https://supervillain.herokuapp.com")
                .basePath("/auth/user/register").contentType(ContentType.JSON)
                .header("Authorization","Bearer " + token)
                .body(requestNode).post();

        Assert.assertEquals(200, response.getStatusCode());
        JsonNode responseBody = response.getBody().as(JsonNode.class);
        //Validate response body here

    }

    //@Test(dependsOnMethods = {"generateToken"})
    //public void
}
