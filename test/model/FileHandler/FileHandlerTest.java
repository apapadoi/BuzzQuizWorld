package model.FileHandler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    FileHandler test;

    @BeforeEach
    void setUp() {
        test = new FileHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readQuestions() {
        test.readQuestions();
    }

}