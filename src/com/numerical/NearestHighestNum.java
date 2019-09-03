package com.numerical;

import java.util.Arrays;

public class NearestHighestNum {
    public static void main(String[] args) {
        Integer num=4321;
        NearestHighestNum obj=new NearestHighestNum();
        System.out.println(obj.findNearestHighest(num));
    }

    public Integer findNearestHighest(Integer n){
        String strNum=n.toString();
        char[] charData=strNum.toCharArray();
        boolean flag=false;
        int i=0, j=0;
        for(i=charData.length-1; i>0;--i){
            for(j=i-1; j>=0;--j) {
                int a = Integer.parseInt(String.valueOf(charData[i]));
                int b = Integer.parseInt(String.valueOf(charData[j]));
                if (a > b) {
                    swapCharData(charData, i, j);
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }
        if(flag){
            Arrays.sort(charData, j+1, charData.length);
            int result=Integer.parseInt(String.valueOf(charData));
            System.out.println("Done, next highest=="+result);
            return result;
        }else {
            System.out.println("Not possible, data="+n);
            return 0;
        }
    }

    public void swapCharData(char[] charData, int l, int r){
        char temp=charData[l];
        charData[l]=charData[r];
        charData[r]=temp;
    }
}
