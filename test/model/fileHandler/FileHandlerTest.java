package model.fileHandler;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resources.utilResources.Constants;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

class FileHandlerTest {
    FileHandler test;

    @BeforeEach
    void setUp() {
        test = new FileHandler(new ArrayList<>(), Paths.get(Constants.QUESTIONS_FILE_URL),
                Paths.get(Constants.IMAGED_QUESTIONS_FILE_URL));
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