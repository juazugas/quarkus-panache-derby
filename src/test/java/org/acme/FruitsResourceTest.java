package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import javax.transaction.Transactional;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.derby.DerbyDatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(DerbyDatabaseTestResource.class)
public class FruitsResourceTest {

    @Test
    public void testListAll() {
        Long count = Fruit.count();
        List<Fruit> result = given()
            .when().get("/fruit")
            .thenReturn().as(new TypeRef<List<Fruit>>() {});
        assertThat(result, hasSize(count.intValue()));
    }

    @Test
    public void testCreate() {
        given()
            .when()
            .body("{\"name\":\"FRUIT\"}")
            .contentType(ContentType.JSON)
            .post("/fruit")
            .then()
            .statusCode(200)
            .body("name", equalTo("FRUIT"));
    }

    @Test
    public void testDelete () {
        Fruit fruit = Fruit.<Fruit>listAll().get(0);

        given().when()
            .delete("/fruit/"+fruit.id)
            .then()
            .statusCode(204);
    }

}