package model.questions;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImagedQuestionTest {

    @Test
    void hasContent() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new ImagedQuestion("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology, new ImageIcon("test/resources/imaged_question_unit_test.png"));

        assertTrue(question.hasContent());
    }

    @Test
    void getContent() {
        ImageIcon image = new ImageIcon("test/resources/imaged_question_unit_test.png");
        image.setDescription("This is a unit testing description!");
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new ImagedQuestion("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology, image);

        assertEquals(image, question.getContent());
        assertEquals("This is a unit testing description!", question.getContent().getDescription());
    }
}