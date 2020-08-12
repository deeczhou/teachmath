package server.models;

public class MinusQuestion extends Question {
    int a;
    int b;
    int diff;

    public MinusQuestion(int a, int b, int diff) {
        this.a = a;
        this.b = b;
        this.diff = diff;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }
}
