package model.questions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void setQuestionText() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        question.setQuestionText("changedTestQuestion");
        assertEquals("changedTestQuestion",question.getQuestionText());
    }

    @Test
    void setCorrectAnswer() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        question.setCorrectAnswer("newAnswer");
        assertEquals("newAnswer",question.getCorrectAnswer());
    }

    @Test
    void setAnswers() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        question.setAnswers(new ArrayList<>(List.of("newAnswer1","newAnswer2","newAnswer3","newAnswer4")));
        assertEquals("newAnswer1",question.getAnswers().get(0));
        assertEquals("newAnswer2",question.getAnswers().get(1));
        assertEquals("newAnswer3",question.getAnswers().get(2));
        assertEquals("newAnswer4",question.getAnswers().get(3));
    }

    @Test
    void setDifficulty() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        question.setDifficulty(Difficulty.Easy);
        assertEquals(Difficulty.Easy, question.getDifficulty());
        question.setDifficulty(Difficulty.Hard);
        assertEquals(Difficulty.Hard, question.getDifficulty());
    }

    @Test
    void setCategory() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        question.setCategory(Category.Films);
        assertEquals(Category.Films, question.getCategory());
        question.setCategory(Category.HipHopGroups);
        assertEquals(Category.HipHopGroups, question.getCategory());
        question.setCategory(Category.Formula1);
        assertEquals(Category.Formula1, question.getCategory());
    }

    @Test
    void getQuestionText() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        assertEquals("testQuestion", question.getQuestionText());
    }

    @Test
    void getCorrectAnswer() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        assertEquals("answer1", question.getCorrectAnswer());
    }

    @Test
    void getAnswers() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        assertEquals("answer1",question.getAnswers().get(0));
        assertEquals("answer2",question.getAnswers().get(1));
        assertEquals("answer3",question.getAnswers().get(2));
        assertEquals("answer4",question.getAnswers().get(3));
    }

    @Test
    void getDifficulty() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        assertEquals(Difficulty.Medium, question.getDifficulty());
    }

    @Test
    void getCategory() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        assertEquals(Category.Technology, question.getCategory());
    }

    @Test
    void hasContent() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        assertFalse(question.hasContent());
    }

    @Test
    void getContent() {
        List<String> answers = new ArrayList<>(List.of("answer1","answer2","answer3","answer4"));
        Question question = new Question("testQuestion","answer1",answers,Difficulty.Medium,
                Category.Technology);

        assertNull(question.getContent());
    }

    @Test
    void testHashCode() {
        Question question1 = new Question();
        Question question2 = new Question();
        assertEquals(question1.hashCode(),question2.hashCode());
        question1 = new Question();
        question1.setCategory(Category.Cars);
        question2 = new Question();
        question2.setCategory(Category.Cars);
        assertEquals(question1.hashCode(),question2.hashCode());
    }
}