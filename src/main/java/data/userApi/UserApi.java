package data.userApi;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static data.Endpoints.*;

public class UserApi {
    private String token;

    public String getToken() {
        return this.token;
    }

    public UserApi() {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("Создание пользователя")
    public Response createUser(CreateUser request) {
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post(CREATE_USER);

        if (response.getStatusCode() == 200) {
            this.token = response.path("accessToken");
        }
        return response;
    }

    @Step("Авторизация пользователя")
    public Response loginUser(LoginUser request) {
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post(LOGIN_USER);

        if (response.getStatusCode() == 200) {
            this.token = response.path("accessToken");
        }
        return response;
    }

    @Step("Удаление пользователя")
    public Response deleteUser() {
        return RestAssured.given()
                .header("Authorization", token)
                .delete(DELETE_USER);
    }

    public void deleteUserByCredentials(String email, String password) {
        LoginUser loginData = new LoginUser(email, password);
        this.loginUser(loginData);
        this.deleteUser();
    }
}
