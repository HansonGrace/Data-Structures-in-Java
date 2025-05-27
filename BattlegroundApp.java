import java.util.Scanner;

public class BattlegroundApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("=====================================");
            System.out.println("       DataStructure Battlegrounds ");
            System.out.println("=====================================");
            System.out.println("Select a data structure to explore:");
            System.out.println("1. Hashtable");
            System.out.println("2. CSV Table");
            System.out.println("3. Binary Table");
            System.out.println("4. Row");
            System.out.println("5. Linked List");
            System.out.println("6. Stack");
            System.out.println("7. Queue");
            System.out.println("8. Binary Search Tree");
            System.out.println("9. Graph");
            System.out.println("10. Exit");

            System.out.print("\nEnter your choice (1-10): ");
            choice = scanner.nextLine();

            System.out.println();

            switch (choice) {
                case "1":
                    System.out.println("You selected: Hashtable");
                    break;
                case "2":
                    System.out.println("You selected: CSV Table");
                    break;
                case "3":
                    System.out.println("You selected: Binary Table");
                    break;
                case "4":
                    System.out.println("You selected: Row");
                    break;
                case "5":
                    LinkedListDemo.run(scanner);
                    break;
                case "6":
                    System.out.println("You selected: Stack");
                    break;
                case "7":
                    System.out.println("You selected: Queue");
                    break;
                case "8":
                    System.out.println("You selected: Binary Search Tree");
                    break;
                case "9":
                    System.out.println("You selected: Graph");
                    break;
                case "10":
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                    break;
            }

            if (!choice.equals("10")) {
                System.out.print("\nPress Enter to return to the main menu...");
                scanner.nextLine();
            }

        } while (!choice.equals("10"));

        scanner.close();
    }
}
