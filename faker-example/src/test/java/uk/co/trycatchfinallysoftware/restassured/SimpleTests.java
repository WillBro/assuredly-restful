package uk.co.trycatchfinallysoftware.restassured;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.trycatchfinallysoftware.LifeCycleLoggerListener;
import uk.co.trycatchfinallysoftware.TestApplication;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;


@ContextConfiguration(
        classes = {
                TestApplication.class
        }
)
@TestExecutionListeners(
        listeners = {
                LifeCycleLoggerListener.class
        },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@RunWith(JUnitPlatform.class)
public class SimpleTests {
    private org.slf4j.Logger Logger = LoggerFactory.getLogger(getClass());

    public org.slf4j.Logger getLogger() {
        return Logger;
    }

    @Before
    public void beforeTest() {
        RestAssured.baseURI = "http://www.google.com";
        RestAssured.basePath = "/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testGetGoogle() {
        when()
                .get("/")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Ignore("Not implemented yet")
    public void testShowSomething() {
    }
}
