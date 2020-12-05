package test;


class A {
    int a;

    public A(int a) {
        this.a = a;
    }

    public void printA() {
        System.out.println(a);
    }
}

class B extends A {
    int a;

    public B(int a) {
        super(a);
        this.a = 2;
    }

    @Override
    public void printA() {
        System.out.println(a);
    }
}

public class Main {

    public static void main(String[] args) {
        A w = new A(5);
        A x = new B(5);
        B y = new B(5);

        w.printA();
        x.printA();
        System.out.println(x.a);
        y.printA();
        System.out.println(y.a + " == 2 " + ((A) y).a + " == 5");
    }

}


