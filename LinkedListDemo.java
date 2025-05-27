
import java.util.Scanner;

public class LinkedListDemo {

    // node inner class
    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    // Head of the list
    private Node head;

    // Add a node to the end of the list
    public void add(String data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Display the linked list
    public void display() {
        Node current = head;
        System.out.print("Current Linked List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // entry point
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        LinkedListDemo list = new LinkedListDemo();

        System.out.println("Welcome to the Linked List Demonstration!");
        System.out.println("A linked list is great for inserting/removing elements efficiently at any position.");
        System.out
                .println("Unlike arrays, they don't need to shift elements or allocate fixed memory ahead of time!\n");

        System.out.print("How many items would you like to add to the linked list? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= count; i++) {
            System.out.print("Enter item " + i + ": ");
            String input = scanner.nextLine();
            list.add(input);
        }

        System.out.println();
        list.display();

        System.out.println("\n Linked Lists are best when:");
        System.out.println("-> You need to insert or delete elements frequently.");
        System.out.println("-> You don't know the number of elements in advance.");
        System.out.println("-> You want to avoid the cost of shifting elements like in arrays.\n");
    }
}
