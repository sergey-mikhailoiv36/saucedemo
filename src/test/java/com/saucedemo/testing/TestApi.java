package com.saucedemo.testing;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApi extends TestBase {


    //@Disabled // <-- Этот тест будет пропущен
    @Test
    @Order(1)
    public void testGetSingleUser() throws InterruptedException {
        logger.info("Get Запрос 'https://jsonplaceholder.typicode.com/users/1'  ");
        String responseBody = given()
                .baseUri("https://jsonplaceholder.typicode.com") // Устанавливаем базовый URL
                .basePath("/users/1")                           // Указываем путь к ресурсу
                .log().all()
                .when()
                .get()                                   // Выполняем GET-запрос
                .then()
                .statusCode(200)                                // Проверяем статус-код
                .body("id", equalTo(1))                  // Проверяем, что id пользователя равен 1
                .body("name", equalTo("Leanne Graham"))  // Проверяем имя
                .body("email", equalTo("Sincere@april.biz")) // Проверяем email
                .body("address.city", equalTo("Gwenborough"))
                .body("address.geo.lng", equalTo("81.1496"))
                .extract().asString(); // Проверяем вложенное поле city
        //System.out.println(responsBody);

        logger.info("Ответ: \n{}", responseBody);
    }

    @Disabled // <-- Этот тест будет пропущен
    @Test
    @Order(2)
    public void testGetSingle() throws InterruptedException {
        logger.info("Get Запрос 'https://jsonplaceholder.typicode.com/users/1'  ");
        String responseBody = given()
                .baseUri("https://jsonplaceholder.typicode.com") // Устанавливаем базовый URL
                .basePath("/users/2")                           // Указываем путь к ресурсу
                .log().all()
                .when()
                .get()                                   // Выполняем GET-запрос
                .then()
                .statusCode(200)                                // Проверяем статус-код
                //.body("id", equalTo(1))                  // Проверяем, что id пользователя равен 1
                //.body("name", equalTo("Leanne Graham"))  // Проверяем имя
                //.body("email", equalTo("Sincere@april.biz")) // Проверяем email
                //.body("address.city", equalTo("Gwenborough"))
                .extract().asString(); // Проверяем вложенное поле city
        logger.info("Ответ: \n{}", responseBody);
    }

    @Test
    @Order(3)
    public void testPost() {
        String requestBody = """
            {
                "title": "Пост для проверки",
                "body": "Содержимое поста",
                "userId": 3
            }
            """;

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
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
    @Order(4)
    public void testPut() {
        String requestBody = """
            {
                "id": 1,
                "title": "Put для проверки",
                "body": "Содержимое Put",
                "userId": 3
            }
            """;
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Извлекаем данные из ответа
        //  int postId = response.jsonPath().getInt("id");
      //  String title = response.jsonPath().getString("title");

       // System.out.println("Изменен пост с ID: " + postId);
       // System.out.println("Заголовок: " + title);
        System.out.println("Полный ответ: " + response.asPrettyString());
    }

    @Test
    @Order(5)
    public void testPatch() {
        String requestBody = """
            {
                "title": "Patch для проверки",
                "body": "содержимое Patch"
            }
            """;
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Извлекаем данные из ответа
        //  int postId = response.jsonPath().getInt("id");
        //  String title = response.jsonPath().getString("title");

        // System.out.println("Изменен пост с ID: " + postId);
        // System.out.println("Заголовок: " + title);
        System.out.println("Полный ответ: " + response.asPrettyString());
    }

    @Test
    @Order(5)
    public void testDelete() {

        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();



        // Извлекаем данные из ответа
        //  int postId = response.jsonPath().getInt("id");
        //  String title = response.jsonPath().getString("title");

        // System.out.println("Изменен пост с ID: " + postId);
        // System.out.println("Заголовок: " + title);
        System.out.println("Полный ответ: " + response.statusCode());
    }
}