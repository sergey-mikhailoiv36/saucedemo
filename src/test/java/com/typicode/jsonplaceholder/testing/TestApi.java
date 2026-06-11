package com.typicode.jsonplaceholder.testing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApi extends TestBaseApi {
    String url ="https://jsonplaceholder.typicode.com";
    String path="/users/";
    String post="/posts";
    int userId=1;

    //@Disabled // <-- Этот тест будет пропущен
    @Test
    @Order(1)
    public void testGetSingleUser() {
        int faileProduct = 0;
        logger.info("Get Запрос " + url +path+userId);
        Response response = given().baseUri(url).basePath(path+userId).when().get(); //Выполняем запрос

        if (response.statusCode() != 200){
            faileProduct=+1;
            logger.error("Статус: "+response.statusCode());
            logger.error("Ответ: \n{}", response.asString());
        } else {
            try {
                response.then()
                        .body("id", equalTo(userId))                  // Проверяем, что id пользователя равен 1
                        .body("name", equalTo("Leanne Graham"))  // Проверяем имя
                        .body("email", equalTo("Sincere@april.biz")) // Проверяем email
                        .body("address.city", equalTo("Gwenborough"))
                        .body("address.geo.lng", equalTo("81.1496"));
                logger.info("Тест пройден: \n Статус: {}\n Тело ответа {}",response.statusCode(),response.asString());
                 }
                    catch (AssertionError error){
                        faileProduct=+1;
                    logger.error("Статус: "+response.statusCode());
                    logger.error("Ответ: \n{}", response.asString());
                    }
            }
        if (faileProduct != 0){ //если НЕ пустой (есть ошибки)
            Assertions.fail("Тест c параметрами "+ url +path+userId+"  не пройден");
        }
    }

    @Test
    @Order(2)
    public void testPost() {

        Map<String,Object> requestBody=new HashMap<>();
        requestBody.put("title","Пост для проверки");
        requestBody.put("body", "Содержимое поста");
        requestBody.put("userId", 3);

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(post)
                .then()
                .statusCode(201)
                .extract()
                .response();

        // Извлекаем данные из ответа
        int postId = response.jsonPath().getInt("id");
        String title = response.jsonPath().getString("title");

        System.out.println("Создан пост с ID: " + postId);
        System.out.println("Заголовок: " + title);
        System.out.println("Полный ответ: " + response.asPrettyString());
    }

    @Test
    @Order(3)
    public void testPut() {
        Map<String,Object> requestBody=new HashMap<>();
        requestBody.put("id", 1);
        requestBody.put("title", "Put для проверки");
        requestBody.put("body", "Содержимое Put1");
        requestBody.put("userId", 3);

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(post+"/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Полный ответ: " + response.asPrettyString());
    }

    @Test
    @Order(4)
    public void testPatch() {

        Map<String,Object> requestBody=new HashMap<>();
        requestBody.put("title","Patch для проверки");
        requestBody.put("body", "содержимое Patch");

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(post+"/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Полный ответ: " + response.asPrettyString());
    }

    @Test
    @Order(5)
    public void testDelete() {

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .when()
                .delete(post+"/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println("Полный ответ: " + response.statusCode());
    }
}