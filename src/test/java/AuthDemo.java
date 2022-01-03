import com.tmb.constants.FrameworkConstants;
import com.tmb.utils.ApiUtils;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class AuthDemo {

    @Test
    public void basicAuthTest(){
        //blacklist headers
        Response response = given()
                .header("Authorization","Basic cG9zdG1hbjpwYXNzd29yZA==")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();
    }

    @Test
    public void getAllWorkspace(){
                given()
                        .header("X-Api-Key","PMAK-6126f517539a6600470f5954-7e16b29a34abd3dea3ffc200c57443127d")
                        .log()
                        .all()
                        .get("https://api.getpostman.com/workspaces")
                        .prettyPrint();


    }

    @Test
    public void getRepositories(){
        given()
                .header("Authorization","Bearer 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")
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .queryParam("per_page",1)
                .log()
                .all()
                .get("https://api.github.com/user/repos")
                .prettyPrint();
    }


    @Test
    public void createIssue(){

        String body = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderpath()+"/request1.json")
                        .replace("KEY","DEM");

        Response response = given()
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization","Content-Type")))
                .header("Authorization","Basic dGVzdGluZ21pbmlieXRlczpBbWJhdHR1cjEh")
                .header("Content-Type","application/json")
                .log()
                .all()
                .body(body)
                .post("http://localhost:8080/rest/api/2/issue/");

        response.prettyPrint();
    }
}
