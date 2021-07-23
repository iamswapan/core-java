package com.java8new.basic;

interface MyInterface{
    public void m1();
}
public class AnnonymsVsLambda {
    int x=100;

    public void m2() {
        MyInterface i1 = new MyInterface() {
            int x = 200;
            @Override
            public void m1() {
                int x=400;
                System.out.println("var annonyms=" + x);
                System.out.println("instance var annonyms=" + this.x);
            }
        };
        i1.m1();
    }

    public void m3(){
        int y=2100;
        MyInterface i2=()->{
            int x = 300;
            System.out.println("var lambda=" + x);
            System.out.println("instance var lambda=" + this.x);
            x=600;
            System.out.println("updated instance var lambda=" + x);
            //y=2200;             //TODO: Updation of any local variable which is used inside lambda exp not allowed,
                                // that vaiable automaically become FINAL, i.e effective final.
            System.out.println("updated loacl var inside lambda not allowed=" + y);
        };
        i2.m1();
    }

    public static void main(String aa[]){
        AnnonymsVsLambda al=new AnnonymsVsLambda();
        al.m2();
        al.m3();
    }

}
