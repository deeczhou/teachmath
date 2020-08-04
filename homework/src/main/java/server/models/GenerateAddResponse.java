package server.models;

import java.util.List;

public class GenerateAddResponse {
    private List<AdditionQuestion> questions;

    public List<AdditionQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<AdditionQuestion> questions) {
        this.questions = questions;
    }
}
