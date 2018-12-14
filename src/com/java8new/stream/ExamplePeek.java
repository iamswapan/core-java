package com.java8new.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ttnd on 24/9/18.
 */
public class ExamplePeek {

    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            arrList.add(i);
        }

        Integer ele=arrList.stream()
                .peek(s -> System.out.println("processing: " + s))
                /*.filter(e -> {
                    System.out.println("***************--" + e + "===" + Thread.currentThread().getName());
                    if (e % 3 == 0)
                        return true;
                    else
                        return false;
                })*/
                .filter(e->e%3==0)
                .peek(s -> System.out.println("after filter: " + s))
                .findFirst().orElse(0);

        System.out.println("=============="+ele);


    }
}
