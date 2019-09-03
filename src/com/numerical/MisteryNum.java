package com.numerical;

public class MisteryNum {
    //TODO: A mystery number is that number which can be expressed as sum of two numbers and those two numbers should be reverse of each other.
    //eg: Input : n = 121
    //Output : 29 92
    //
    //Input : n = 22
    //Output : 11 11

    public static void main(String[] args) {
        int n=121;

        MisteryNum obj=new MisteryNum();
        obj.isMystry(n);

    }

    public void isMystry(int n){
        for (int i=0;i<=n/2;++i){
            if(i+revNum(i)==n){
                System.out.println("Magic=="+i+"=="+revNum(i));
                return;
            }
        }
        System.out.println("Not magic");
        return;
    }

    public int revNum(int n){
        int s=0;
        for(int i=n; i>0;i=i/10){
            int r=i%10;
            s=s*10+r;
        }
        //System.out.println(n+"rev=="+s);
        return s;
    }
}
