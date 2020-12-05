package model.fileHandler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

class FileHandlerTest {
    FileHandler test;

    @BeforeEach
    void setUp() {
        test = new FileHandler(new ArrayList<>(), Paths.get("data/questions/textQuestions/textQuestions.txt"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readQuestions() {
        try{
            test.readQuestions();
        }catch(IOException exception) {
            System.out.println("Could not find text file containing the questions.");
        }
    }

}