package api.endpoints;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import api.payloads.Pet;
import io.restassured.response.Response;

public class PetEndpoint {
    /* below are the end-points for pet module
        1. add new pet
        2. find pet by ID
        3. find pet by status
        4. update an existing pet
        5. update a pet in store using form data
        --6. upload an image of a pet
        7. delete a pet
     */

    // add new pet 
    public static Response addPet(Pet payload){
        Response response;
        response = given()
                    .contentType("application/json")
                    .accept("application/json")
                    .body(payload)
                   .when()
                    .post(Route.urlPatternOne);
        return response;
    }

    // find pet by ID
    public static Response findPetById(int id){
        Response response;
        response = given()
                        .pathParam("petId",id)
                        .accept("application/json")
                    .when()
                        .get(Route.urlPatternTwo);    
        return response;
    }

    // find pet by status
    public static Response findPetByStatus(String status){
        Response response;
        response = given()
                        .queryParam("status", status)
                        .accept("application/json")
                    .when()
                        .get(Route.findByStatusUrl);
        return response;
    }

    // update an existing pet
    public static Response updatePet(Pet payload){
        Response response;
        response = given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(payload)
                    .when()
                        .put(Route.urlPatternOne);
        return response;
    }

    // update a pet in store using form data
    public static Response updatePetForm(int id, String name, String status){
        Response response;
        response = given()
                        .contentType("application/x-www-form-urlencoded")
                        .accept("application/json")
                        .pathParam("petId", id)
                        .formParam("name", name)
                        .formParam("status", status)
                    .when()
                        .post(Route.urlPatternTwo);    
        return response;
    }

    // delete a pet
    public static Response deletePet(int id){
        Response response;
        response = given()
                        .accept("application/json")
                        .pathParam("petId", id)
                    .when()
                        .delete(Route.urlPatternTwo);
        return response;
    }

}
