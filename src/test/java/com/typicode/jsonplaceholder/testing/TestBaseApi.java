package com.typicode.jsonplaceholder.testing;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.lang.reflect.Method;

public class TestBaseApi {

    public static Logger logger = LoggerFactory.getLogger(TestBaseApi.class);

    @BeforeEach
    public void startTest(TestInfo t){
        logger.info("Начало теста {}",t.getDisplayName());
    }

    @AfterEach
    public void stopTest (TestInfo t){
        logger.info("Окончание теста {}",t.getDisplayName());
    }
}
