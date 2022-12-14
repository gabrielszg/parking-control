package one.digitalinnovation.parkingcontrol.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import one.digitalinnovation.parkingcontrol.model.dto.ParkingCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest {
	
	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
				.auth().basic("user", "12345")
				.when()
				.get("/parking")
				.then()
				.statusCode(HttpStatus.OK.value());
	}
	
	@Test
    void whenCreateThenCheckIsCreated() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("yellow");
        createDTO.setLicense("HKL-2345");
        createDTO.setModel("VW Fox");
        createDTO.setState("SP");

        RestAssured.given()
                .when()
                .auth().basic("user", "12345")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("HKL-2345"))
                .body("color", Matchers.equalTo("yellow"));
    }
	
}
