package se.webstep.springboot101;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SpringBoot101ApplicationIT {

   @Value("${local.server.port}")
   private int port;

   @Before
   public void setUp() {
      RestAssured.port = port;
   }

   @Test
   public void helloEndpointsReturnsHello() {
      when().
         get("/hello").
         then().
         statusCode(HttpStatus.SC_OK).
         body("greeting", is("Hello!"));
   }

   @Test
   public void thereShouldBeTwoEmployeesInDb() {
      when().
         get("/websteper").
         then().
         statusCode(HttpStatus.SC_OK).
         body("[0].firstName", is("Magnus")).
         body("[1].firstName", is("Eje"));
   }

}
