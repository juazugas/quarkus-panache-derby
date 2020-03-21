package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.derby.DerbyDatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(DerbyDatabaseTestResource.class)
public class FruitsResourceTest {

    @Test
    public void testmethodname() {
        List<Fruit> result = given()
            .when().get("/fruits")
            .thenReturn().as(new TypeRef<List<Fruit>>() {});
        assertThat(result, hasSize(3));
    }

}