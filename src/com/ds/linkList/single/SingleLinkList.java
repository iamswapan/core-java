package com.ds.linkList.single;

public class SingleLinkList {

    private Node head;

    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
        }
    }

    public static void main(String[] args) {
        Node n1=new Node(10);
        Node n2=new Node(20);
        Node n3=new Node(30);
        Node n4=new Node(40);
        Node n5=new Node(50);
        Node n6=new Node(60);

        SingleLinkList sl1=new SingleLinkList();
        sl1.head=n1;
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;
        sl1.display();
        sl1.getSecondLast();
    }

    public void insertFirst(int data){
        Node newNode=new Node(data);
        newNode.next=head;
        head=newNode;
    }

    public void insertAfter(int position, int data){
        Node newNode=new Node(data);
        Node traverse=head;
        int i=1;
        while (traverse!=null){
            if(i==position){

            }

        }
    }

    public void insertLast(){

    }

    public void display(){
        Node n=head;
        System.out.println("Link list values=");
        for (;n!=null;n=n.next)
            System.out.print("  "+n.data);
    }

    public int getSecondLast(){
        Node n=head;
        if(n==null || n.next==null){
            System.out.println("\nMinimum 2 nodes required");
            return -1;
        }
        while (n!=null){
            if(n.next.next==null){
                System.out.println("\nFound=="+n.data);
                return n.data;
            }
            n=n.next;
        }
        return -1;
    }
}
