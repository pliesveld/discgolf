package com.pliesveld.discgolf.test;

import com.pliesveld.discgolf.domain.Course;
import com.pliesveld.discgolf.domain.GameStatus;
import com.pliesveld.discgolf.domain.Player;
import com.pliesveld.discgolf.test.core.AbstractDiscGolfIntegrationTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.collection.IsArray;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;

public class GameIntegrationTest extends AbstractDiscGolfIntegrationTest {

    private static final Logger LOG = LogManager.getLogger();


    @Test
    public void givenPlayer_whenSpecifiyingName_shouldReturn() throws Exception {
        when()
            .get("/player/name/patrick")
        .then()
            .statusCode(200).assertThat()
            .body("name", equalTo("patrick"));
    }

    @Test
    public void givenPlayer_whenFindByName_shouldReturn() throws Exception {
        when()
            .get("/player/find/patrick")
        .then()
            .statusCode(200).assertThat()
            .body("page.totalElements", greaterThan(0));
    }

    @Test
    public void givenPlayer_whenFindByPartialName_shouldReturnList() throws Exception {
        when()
            .get("/player/find/pat")
        .then().assertThat()
            .statusCode(200)
            .body("page.totalElements", greaterThan(0));
    }

    @Test
    public void givenInvalidPlayerId_whenFindById_shouldEmpty() throws Exception {
        when()
            .get("/player/id/INVALID-INVALID-INVALID")
        .then().assertThat()
            .statusCode(404);
    }

    @Test
    public void givenCourse_whenFindByName_shouldExist() throws Exception {
        when()
            .get("/course/name/Bull Run Regional Park")
        .then().assertThat()
            .statusCode(200);
    }

    @Test
    public void whenGame_shouldCreate() {

        Player patrick =
        when()
            .get("/player/name/patrick").andReturn().as(Player.class);

        assertNotNull(patrick);

        Course course =
        when()
            .get("/course/name/Bull Run Regional Park")
        .andReturn().as(Course.class);


        Response response =
        given()
            .param("players", singletonList(patrick.getId()))
            .param("course", course.getName())
        .when()
            .post("/game")
            .andReturn();

        response.then().assertThat().statusCode(200);
        response.then().log().all();
        response.then().assertThat().body("gameStatus", equalTo("NEW"));
        response.then().assertThat().body("players.size()", is(1));

        //response.then().assertThat().body("createdOn", notNullValue());
        //response.then().assertThat().body("lastUpdated", notNullValue());

        final String gameId = response.thenReturn().body().jsonPath().getString("id");
        assertNotNull(gameId);

        given()
            .pathParam("gameId", gameId)
        .when()
            .get("/game/id/{gameId}")
        .then()
            .log().ifValidationFails()
            .statusCode(200);

        final String playerName = patrick.getName();

        for (int i = 0; i < 18; i++) {
            recordScore(gameId, playerName, 3);
        }

        given()
            .pathParam("gameId", gameId)
        .when()
            .get("/game/id/{gameId}")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("scores.patrick.strokesList.size()", is(18));

        given().pathParam("gameId", gameId).pathParam("playerName", playerName).param("strokes", 3).when().post("/game/{gameId}/player/{playerName}/strokes").then().log().ifValidationFails().assertThat().statusCode(not(200));

    }

    private void recordScore(String gameId, String playerName, int strokes) {
        given().pathParam("gameId", gameId).pathParam("playerName", playerName).param("strokes", 3).when().post("/game/{gameId}/player/{playerName}/strokes").then().log().ifValidationFails().assertThat().statusCode(200);
    }

}
