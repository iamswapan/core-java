package com.jcod.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ttnd on 28/9/18.
 */
public class CompareSortPrimitive {
    public static void main(String[] args) {
        Integer[] arr={11,7,2,3,37,4,15,12};

        System.out.println("before sort="+Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println("after sort="+ Arrays.toString(arr));

        List<String> list=new ArrayList<>();
        list.add("bowl");
        list.add("ball");
        list.add("cat");
        list.add("apple");
        list.add("APPLE");
        System.out.println("Before sort list="+list);
        Collections.sort(list);
        System.out.println("After sort list="+list);


    }
}
