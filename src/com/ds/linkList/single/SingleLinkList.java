package com.ds.linkList.single;

import java.util.Objects;
import java.util.Scanner;

public class SingleLinkList {

    private Node head;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);
        Node n5 = new Node(50);
        Node n6 = new Node(60);

        SingleLinkList sl1 = new SingleLinkList();
        /*sl1.head = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        sl1.display();
        sl1.getSecondLast();*/

        //////////////
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("\nPress 1 for insert first:\nPress 2 for insert after(position):\nPress 3 for insert last:\n" +
                    "Press 4 for display list:\nPress 5 for display second last:\nPress 6 to delete:\nPress 7 to Serach Iterative:\nPress 8 to Search Recursive:\nPress 9 for EXIT:");
            int opt = s.nextInt();
            switch (opt) {
                case 1: {
                    System.out.print("\nEnter data:");
                    int val = s.nextInt();
                    sl1.insertFirst(val);
                }
                break;
                case 2: {
                    System.out.print("\nEnter data:");
                    int val = s.nextInt();
                    System.out.print("\nEnter position:");
                    int pos = s.nextInt();
                    sl1.insertAfter(pos, val);
                }
                break;
                case 3: {
                    System.out.print("\nEnter data:");
                    int val = s.nextInt();
                    sl1.insertLast(val);
                }
                break;
                case 4:
                    sl1.display();
                    break;
                case 5:
                    sl1.getSecondLast();
                    break;
                case 6: {
                    System.out.print("\nEnter key to delete:");
                    int val = s.nextInt();
                    sl1.deleteKey(val);
                }
                break;
                case 7: {
                    System.out.print("\nEnter key to search itr:");
                    int val = s.nextInt();
                    sl1.searchKeyIterative(sl1.head, val);
                }
                break;
                case 8: {
                    System.out.print("\nEnter key to search itr:");
                    int val = s.nextInt();
                    sl1.searchKeyIterative(sl1.head, val);
                }
                break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong option choose");
            }
        }
        /////////////
    }

    public void insertFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //Insert after the given position
    public void insertAfter(int position, int data) {
        Node newNode = new Node(data);
        Node traverse = head;
        int i = 1;
        if (Objects.isNull(traverse)) {
            System.out.println("No node available, New node added to first");
            insertFirst(data);
            return;
        }
        /*while (traverse != null) {
            if (i == position) {
                newNode.next = traverse.next;
                traverse.next = newNode;
                return;
            }
            traverse = traverse.next;
            ++i;
        }*/ //NOTE: Below code is also same.

        for (traverse = head, i = 1; traverse != null; traverse = traverse.next, ++i) {
            if (i == position) {
                newNode.next = traverse.next;
                traverse.next = newNode;
            }
        }
        System.out.println("Position not found");
    }

    public void insertLast(int data) {
        Node traverse = head;
        Node newNode = new Node(data);
        if (Objects.isNull(traverse)) {
            System.out.println("No node available, New node added to first");
            insertFirst(data);
        } else {
            for (; traverse.next != null; traverse = traverse.next) ;
            traverse.next = newNode;
        }
    }

    public void deleteKey(int key) {
        if (Objects.isNull(head)) {
            System.out.println("No node available");
            return;
        }
        Node traverse = head;
        if(traverse.data==key){
            Node delNode=traverse;
            head=head.next;
            System.out.println("First node to be deleted: "+delNode.data);
            delNode=null;
            return;
        }
        for (; traverse.next != null; traverse = traverse.next) {
            if (traverse.next.data == key) {
                Node delNode = traverse.next;
                System.out.println("Data Found with value=" + delNode.data);
                traverse.next = delNode.next;
                delNode = null;
                return;
            }
        }
        System.out.println("No key found");
    }

    public boolean searchKeyIterative(Node head, int key){
        if(head==null) {
            System.out.println("Blank list: Key not found");
            return false;
        }
        Node traverse=head;
        int i=0;
        for(;traverse!=null;traverse=traverse.next,++i){
            if(traverse.data==key){
                System.out.println(String.format("Key %s found at index:%s",key, i));
                return true;
            }
        }
        System.out.println("Key not found");
        return false;
    }

    public boolean searchKeyRecursive(Node head, int key){
        if(head==null) {
            System.out.println("Blank list: Key not found");
            return false;
        }
        if(head.data==key){
            System.out.println("Key found");
            return true;
        }
        return searchKeyRecursive(head.next, key);
    }

    public void display() {
        if (Objects.isNull(head)) {
            System.out.println("No node available");
            return;
        }
        Node n = head;
        System.out.print("Link list values=");
        for (; n != null; n = n.next)
            System.out.print("  " + n.data);
    }

    public int getSecondLast() {
        Node n = head;
        if (n == null || n.next == null) {
            System.out.println("\nMinimum 2 nodes required");
            return -1;
        }
        while (n != null) {
            if (n.next.next == null) {
                System.out.println("\nSecond Last Element==" + n.data);
                return n.data;
            }
            n = n.next;
        }
        return -1;
    }
}
