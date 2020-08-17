package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Flow;

public class Driver {

    private static int test = 0;

    private static void PrepareTestData() throws FileNotFoundException, IOException {
        TellerService service = new TellerService();
        TellPublisher pub1 = new TellPublisher();

        Scanner scan = new Scanner(System.in);
        System.out.print("\n\n1: Tell Details\n2: Request Details\nAny other integer to stop.\nEnter an option: ");
        int option = scan.nextInt();
        do {
            switch (option) {
                case 1:
                    System.out.print("Enter id: ");
                    int id = scan.nextInt();
                    System.out.print("Enter Tell title: ");
                    scan.nextLine();
                    String title = scan.nextLine();
                    System.out.print("Enter teller name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter keyword: ");
                    String keyword = scan.nextLine();

                        Tell tell = new Tell(id, name, title, keyword);
                        pub1.publish(tell, service);
                        System.out.println("\nTell successfully entered\n");
                    break;
                case 2:
                    scan.nextLine();
                    System.out.print("Enter subscriber name: ");
                    String subName = scan.nextLine();
                    System.out.print("Enter Tell title: ");
                    String rtitle = scan.nextLine();
                    System.out.print("Enter teller name: ");
                    String rname = scan.nextLine();
                    System.out.print("Enter keyword: ");
                    String rkeyword = scan.nextLine();

                    SubscriptionRequest request = new SubscriptionRequest(subName, new Message(rtitle,rname, rkeyword));
                    service.addRequest(request);
                    System.out.println("\nSubscriber details successfully entered\n");

            break;

                default:
                    System.exit(0);
            }
            System.out.print("\n\n1: Tell Details\n2: Request Details\nAny other integer to stop.\nEnter an option: ");
            option = scan.nextInt();
        }while(true);
    }



    public static void main(String[] args) throws Exception{

         if(args.length >= 1 && args[0].equals("test")){
             PrepareTestData();
             return;
         }

        TellerService service = new TellerService();

        // Create requests
        SubscriptionRequest request1 = new SubscriptionRequest("A", new Message(null, null, null));
        SubscriptionRequest request2 = new SubscriptionRequest("B", new Message("Grave yard", "David Johnson", null));

        service.addRequest(request1);
        service.addRequest(request2);

        Tell tell1 = new Tell(101,"Brian Bennett", "Little boy", "Tragedy");
        Tell tell2 = new Tell(102, "Chiamaka Nancy", "Great minds", "Love");
        Tell tell3 = new Tell(103,  "David Mark","Mr. perfect", "Humor");

        TellPublisher pub1 = new TellPublisher();
        pub1.publish(tell1,service);
        pub1.publish(tell2,service);
        pub1.publish(tell3,service);



        SubscriptionRequest request3 = new SubscriptionRequest("A", new Message("Mr. perfect", null, null));

        service.addRequest(request3);

    }
}
