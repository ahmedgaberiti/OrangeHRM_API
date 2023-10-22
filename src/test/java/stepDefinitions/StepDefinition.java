package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;



import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDefinition extends Utils {


    Response response;
    String cookie;
    int id;
    TestDataBuild dataBuild = new TestDataBuild();

    @Given("User login to OrangeHRM with {string} and {string}")
    public void userLoginToOrangeHRMWithUsernameAndPassword(String username, String password) throws IOException {

        response = given().log().all().spec(requestSpecification())
                .formParam("_token", getGlobalValue("token"))
                .formParam("username", username)
                .formParam("password", password)
                .when().post("/auth/validate");
        cookie = response.headers().getValue("Set-Cookie");

    }

    @When("Add candidate with {string} and {string} and {string}")
    public void addCandidateWithFirstnameAndLastnameAndEmail(String firstname, String lastname, String email) throws IOException {

        response = given().log().all().spec(requestSpecification()).header("Content-Type", "application/json")
                .header("Cookie", cookie)
                .when()
                .body(dataBuild.addCandidatePayload(firstname, lastname, email))
                .post("/api/v2/recruitment/candidates");
        id = Integer.parseInt(getJsonPath(response, "ID"));

    }


    @And("delete the candidate")
    public void deleteTheCandidate() throws IOException {
        response = given().log().all().spec(requestSpecification()).header("Content-Type", "application/json")
                .header("Cookie", cookie)
                .when()
                .body(dataBuild.deletePayloadBody(id))
                .delete("/api/v2/recruitment/candidates");

    }

    @Then("candidate deleted success with status code {int}")
    public void candidateDeletedSuccessWithStatusCode(int int2) {
        assertEquals(response.getStatusCode(), 200);

    }

    @When("search for candidate with {string}")
    public void searchForCandidateWith(String jobTitle) throws IOException {
        // should contain file that maps the (jobTitle) to it's (jobTitleId) to be used in queryParam if I know all the jobTitleIds
        response = given().log().all().spec(requestSpecification()).header("Content-Type", "application/json")
                .header("Cookie", cookie)
                .queryParam("limit", 50)
                .queryParam("offset", 0)
                .queryParam("jobTitleID", 8)
                .queryParam("model", "list")
                .queryParam("sortField", "dateOfApplication")
                .queryParam("order", "DESC")
                .when().get("/api/v2/recruitment/candidates");

    }

    @Then("results should contain the name of {string}")
    public void resultsShouldContainTheNameOf(String jobTitle) {
        int size = Integer.parseInt(getJsonPath(response, "data.size()"));
        for (int i = 0; i < size; i++) {
            assertEquals(jobTitle, getJsonPath(response, "data[" + i + "].vacancy.name"));
        }


    }
}
