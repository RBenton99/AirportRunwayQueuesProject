package AirportRunwayTester;

import java.util.Queue;
import java.util.LinkedList;

/*
Josh Riddle
CS 331 - 001
Tue, Thurs 9:15 - 10:45
Fall 2022
*/
public class QueueHandler {
    // Represents a single node
    class Node{
        Airplane plane;
        Node next;
        
        public Node(Airplane plane){
            this.plane = plane;
            this.next = null;
        }
    }

    // Head and tail of Node
    public Node head = null;
    public Node tail = null;
    
    void addNode(Airplane plane, Queue landing){
        // Landing Queue
        
        // New Node
        Node newNode = new Node(plane);
        
        // Is is linked list empty
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    
    public void printNode () {
        System.out.println("This is a ");
    }
}
