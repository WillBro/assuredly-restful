package uk.co.trycatchfinallysoftware;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * Simple test of GET parameters when using RestAssured
 *
 * @author William Brown
 */
public class ParametersTest {
    @BeforeClass
    public static void setBaseUriAndPath() {
        RestAssured.basePath = "/";
        RestAssured.baseURI = "http://www.google.co.uk";
    }

    /**
     * Request, using HTTPs GET verb, a URL from Google
     * with two simple parameters placed in a Map
     */
    @Test
    public void queryParametersAreEncoded() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("q", "What are you looking for");
        params.put("gws_rd", "ssl");

        /*
         Request method:	GET
         Request URI:	http://www.google.co.uk/?q=What%20are%20you%20looking%20for&gws_rd=ssl
         Query params:	q=What are you looking for
         gws_rd=ssl
         */

        given()
                .contentType(ContentType.HTML)
                .queryParams(params)
                .when()
                .get("/")
                .then()
                .assertThat()
                .statusCode(200)
        ;
    }
}
