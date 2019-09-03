package com.ds.search;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = { 2, 3, 4, 10, 40 };
        BinarySearch obj=new BinarySearch();
        obj.bSearch(arr, 0, arr.length-1, 12);
    }

    public void bSearch(int []arr, int l, int r, int data){
        int mid;
        while (l<=r){
            mid=(l+r)/2;
            if(arr[mid]==data){
                System.out.println(data+": Found at: "+mid);
                return;
            }else if(arr[mid]>data){
                r=mid-1;
            }else {
                l=mid+1;
            }
        }
        System.out.println(data +": Not Fund");
    }
}
