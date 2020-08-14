package server.models;

public class DivisionQuestion extends Question {
    int numerator;
    int denominator;
    int answer;

    public DivisionQuestion(int numerator, int denominator, int answer) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.answer = answer;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
