package com.test;

public class AnnonymsInnerTest {
    public static void main(String[] args) {
        int j=9;
        TestInner obj1 = new TestInner() {
            @Override
            public void display() {
                int i=5;
                i=7;
                System.out.println("My Ann Inner:"+i+j);
            }

            @Override
            public void newDisplay(String arg) {
                System.out.println("Method newDisplay():: "+arg);
            }


        };

        obj1.display();
        obj1.newDisplay("myValue");
    }
}

interface TestInner{
    void display();
    void newDisplay(String arg);
}
