package server.models;

public class MutiplyQuestion extends Question {
    int a;
    int b;
    int product;

    public MutiplyQuestion(int a, int b, int product) {
        this.a = a;
        this.b = b;
        this.product = product;
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

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
