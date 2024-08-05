package shoopkeper;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int num, a, c;
        Scanner input = new Scanner(System.in);
        do {
        System.out.println("welcome in nakoda shop");
        System.out.println("Choose an option : \n 1 for Owner \n 2 for Customer \n 3 to Exit");
        num = input.nextInt();

      
            switch (num) {
                case 1: {
                    System.out.println("welcome Admin ");
                    jdbc_admin ad = new jdbc_admin();
                    System.out.println("*******---PRODUCT LIST---*******");
                    ad.productlist();
                    do {
                        System.out.println("1 for add items");
                        System.out.println("2 for update items");
                        System.out.println("3 for remove items");
                        System.out.println("4 for Logout");
                        a = input.nextInt();
                        switch (a) {
                            case 1:
                                ad.insert();
                                System.out.println("*******---PRODUCT LIST---*******");
                                ad.productlist();
                                break;
                            case 2:
                                ad.update();
                                System.out.println("*******---PRODUCT LIST---*******");
                                ad.productlist();
                                break;
                            case 3:
                                ad.delete();
                                System.out.println("*******---PRODUCT LIST---*******");
                                ad.productlist();
                                break;
                            case 4:
                                System.out.println("Logging out...");
                                break;
                        }
                    } while (a != 4);
                    break;
                }
                case 2: {
                    System.out.println("Welcome customer in nakoda shop");
                    jdbc_customer cst = new jdbc_customer();
                    System.out.println("*******---PRODUCT LIST---*******");
                    cst.productlist();
                    do {
                    System.out.println("1 for purachase Items");
                    System.out.println("2 for Ordered List");
                    System.out.println("3 for Total Payment");
                    System.out.println("4 for delete-purchase Items");
                    System.out.println("5 for Logout");

                    c = input.nextInt();
                    int id;
                    int quantity;
                   
                        switch (c) {
                            case 1:
                                cst.purchase();
                                System.out.println("If you want to see purchase-list press 2");
                                break;
                            
                            case 2: 
                                System.out.println("*******---ORDERED LIST---*******");
                                cst.order();
                                break;
                            case 3:
                                System.out.println("*******---ORDERED LIST---*******");
                            	cst.order();
                                System.out.println("*******---TOTAL PAYMENT---*******");
                                cst.payment();
                                break;
                            case 4:
                                cst.delete();
                                System.out.println("*******---PURCHASE LIST---*******");
                                cst.productlist();
                                break;
                            case 5:
                                System.out.println("Logging out...");
                                break;
                        }
                    } while (c!= 5);
                    break;
                }
                case 3:
                    System.out.println("\nByee thanks for coming\n");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (num!= 3);
        System.out.println("Thank You");
    }
    
}