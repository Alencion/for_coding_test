package test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();

        results.add(4);
        results.add(5);
        results.add(5);
        results.add(5);
        results.add(5);

    }
}


class Parent{

    public void print(){
        System.out.println("p");
    }

    public void print(String element){
        System.out.println(element);
    }
}

class Child extends Parent{
    String element;

    public Child(String element) {
        this.element = element;
    }

    @Override
    public void print() {
        super.print();
        print(element);
    }

    @Override
    public void print(String param) {
        super.print(param);
        System.out.print(param + element);
    }
}
