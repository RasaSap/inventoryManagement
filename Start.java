package inventoryManagement;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Start {

    private Scanner sc;

    public static void meniu() throws IOException, ParseException {

        Scanner sc = new Scanner(System.in);
        boolean stopProgram = false;
        while (!stopProgram) {
            System.out.println("****************************************************************");
            System.out.println("choose operation:");
            System.out.println("0 - Exit");
            System.out.println("1 - Item List");
            System.out.println("  - Item List is sorted by item name and dublicates are checked");
            System.out.println("2 - Checking Stock of Items");
            System.out.println("  - Stock of items will be checked by given amount and list will");
            System.out.println("  - appear with items with smaller quantities");
            System.out.println("3 - Checking Items Validity");
            System.out.println("  - Expiry dates of items will be checked by given date and list will");
            System.out.println("  - appear with items that expiration date is expired or matched");
            System.out.println("*****************************************************************");

            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    sc.close();
                    stopProgram = true;
                    return;

                case 1:
                    List<Item> list = Warehouse.checkForDublicates(Warehouse.readItemsFile("/Users/lenovo/desktop/testasJunior/sample.csv"));
                    Warehouse.printList(list);
                    break;

                case 2:
                    System.out.println("Please enter the item amount");
                    int amount = sc.nextInt();
                    Warehouse.checkStock(amount);
                    break;

                case 3:

                    String inDate;
                    do {
                        System.out.println("Enter the date (yyyy-MM-dd): ");
                        inDate = sc.next();
                    } while (!Warehouse.isValidDate(inDate));

                    Warehouse.checkDate(inDate);
                    break;

                default:
                    System.out.println("wrong input, choose again");

            }
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        meniu();
    }
}

