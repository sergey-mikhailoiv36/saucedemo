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
        Response response = given().
                baseUri(url).
                basePath(path+userId).
                when().
                get(); //Выполняем запрос

        if (response.statusCode() != 200){
            faileProduct=+1;
            logger.error("Статус: {} Ответ: {}",response.statusCode(),response.asString());
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
                    logger.error("Ошибка: {} Статус: {} Ответ: \n{} ", error.getMessage(),response.statusCode(),response.asString());
                    }
            }
        if (faileProduct != 0){ //если НЕ пустой (есть ошибки)
            Assertions.fail("Тест c параметрами "+ url +path+userId+"  не пройден");
        }
    }

    @Test
    @Order(2)
    public void testPost() {
        int faileProduct = 0;
        //int id=1; // тест ошибки
        String title="Пост для проверки";
        String body="Содержимое поста";
        int userId=11;

        Map<String,Object> requestBody=new HashMap<>();
       // requestBody.put("id", id); // тест ошибки
        requestBody.put("title",title);
        requestBody.put("body", body);
        requestBody.put("userId", userId);

        logger.info("Post Запрос {} c параметрами {}" , url +post,requestBody);

        //Выполняем запрос POST
        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(post);

        if (response.statusCode()!=201){
            faileProduct=+1;
            logger.error("Статус: {}",response.statusCode());
        } else {
            try {
                response.then()
                       // .body("id",equalTo(id)) // тест ошибки
                        .body("title",equalTo(title))
                        .body("body",equalTo(body))
                        .body("userId",equalTo(userId));
                // Извлекаем данные из ответа
                int postId = response.jsonPath().getInt("id");
                String responseTitle = response.jsonPath().getString("title");

                logger.info("Тест пройден \nСоздан новый пост с ID: {} Заголовок: {}",postId,responseTitle);
                logger.info("Полный ответ: \n{}",response.asPrettyString());
            }
                catch (AssertionError error){
                    faileProduct=+1;
                    logger.error("Ошибка: {} Статус: {} Ответ: \n{} ", error.getMessage(),response.statusCode(),response.asString());
                }
        }
        if (faileProduct != 0) { //если НЕ пустой (есть ошибки)
            Assertions.fail("Тест c параметрами " + url + post + " " + requestBody+"  не пройден");
        }
    }

    @Test
    @Order(3)
    public void testPut() {

        int faileProduct = 0;
        int id=1;
        String title="Put для проверки";
        String body="Put для проверки";
        int userId=11;

        Map<String,Object> requestBody=new HashMap<>();
        //requestBody.put("id", id);
        requestBody.put("title", title);
        requestBody.put("body", body);
        requestBody.put("userId", userId);

        logger.info("Put Запрос {}/{} c параметрами {}" , url +post,userId,requestBody);

        Response response = given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(post+"/"+id);

        if (response.statusCode()!=200){
            faileProduct=+1;
            logger.error("Статус: {}",response.statusCode());
        } else {
            try {
                response.then()
                        .statusCode(200)
                        .body("id",equalTo(id))
                        .body("title",equalTo(title))
                        .body("body",equalTo(body))
                        .body("userId",equalTo(userId));

                logger.info("Тест пройден \nСтатус {} \nПолный ответ: \n{}",response.statusCode(),response.asPrettyString());
            }
            catch (AssertionError error){
                faileProduct=+1;
                logger.error("Ошибка: {} Статус: {} Ответ: \n{} ", error.getMessage(),response.statusCode(),response.asString());
            }
        }
        if (faileProduct != 0) { //если НЕ пустой (есть ошибки)
            Assertions.fail("Тест c параметрами " + url + post + " " + requestBody+"  не пройден");
        }
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