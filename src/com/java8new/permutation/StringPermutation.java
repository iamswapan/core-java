package com.java8new.permutation;

public class StringPermutation {
    public static void main(String[] args) {
        String str="ABC";
        StringPermutation per=new StringPermutation();
        per.permutation(str, 0, str.length()-1);
    }

    public static void permutation(String str, int l, int r){
        if(l==r) {
            System.out.println(str);
        }else {
            for (int i = l; i <= r; ++i) {
                str = swapChar(str, l, i);
                permutation(str, l + 1, r);
            }
        }
    }

    public static String swapChar(String st, int l, int r){
        char[] charArray=st.toCharArray();
        char ch=charArray[l];
        charArray[l]=charArray[r];
        charArray[r]=ch;
        return String.valueOf(charArray);
    }
}
