package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author brdde
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {
        
        /**
         * Create a new Library object and load books/members from the
         * libraryData.json file
         */
        Library library = new Library();

        library.load();

        // Intro image for fun :)
        String introMessage = """
              __...--~~~~~-._   _.-~~~~~--...__
            //    LIBRARY    `V'      BY       \\\\ 
           //     MANAGER     |  DEWALD BREED   \\\\ 
          //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ 
         //__.....----~~~~._\\ | /_.~~~~----.....__\\\\
         ===================\\\\|//====================
                            `---`
        -----------------------------------------------""";

        System.out.println(introMessage);
        Scanner scan = new Scanner(System.in);
        // Choice used for switch case below
        int choice;
        // used for sub-menu options
        int option;


        Thread dueDateProcessor = new Thread(new DueDateProcessor(library));
        dueDateProcessor.start();

//        Thread notificationProcessor = new Thread(new NotificationProcessor(library));
//        notificationProcessor.start();
        /**
         * This loop continues to display the menu, prompting the user for an
         * option until "0" is input, at which point the loop exits and the
         * program ends.
         */
        do {

            // Print the navigation menu
            showMenu();

            // Prompt and validate input before passing choice to the switch.
            while (true) {

                System.out.print("Option: ");

                try {
                    choice = Integer.parseInt(scan.nextLine());
                    if (choice > 5 || choice < 0) {
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(
                            "Invalid Choice. Please input a valid digit (0 to 5)"
                    );
                }
            }

            /**
             * Switch statement with corresponding menu choice procedures. See
             * the Book, Member and Library classes for further documentation on
             * each method.
             */
            switch (choice) {
                // Add Book
                case 1 -> {
                    library.addBook(Prompt.ForNewBook());
                }
                // Add Member
                case 2 -> {
                    library.addMember(Prompt.ForNewMember());
                }
                // Check In/Out
                case 3 -> {

                    System.out.println("""
                                       Would you like to check In or Out?
                                       
                                       0. Back to Menu
                                       1. Check In
                                       2. Check Out
                                       
                                       """);

                    // Validate Input
                    while (true) {
                        System.out.print("Option: ");

                        try {
                            option = Integer.parseInt(scan.nextLine());
                            if (option > 2 || option < 0) {
                                throw new Exception();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println(
                                    "Invalid Choice. Please input a valid digit (0 to 2)"
                            );
                        }
                    }

                    if (option == 1) {
                        library.checkIn();
                    }

                    if (option == 2) {
                        library.checkOut();
                    }

                }
                // View...
                case 4 -> {
                    System.out.println("""
                                       
                                       What would you like to view?
                                       
                                       0. Back to Menu
                                       1. All Books
                                       2. Checked Out Books
                                       3. All Members
                                       4. Notification Log
                                       """);
                    // Validate Input
                    while (true) {
                        System.out.print("Option: ");

                        try {
                            option = Integer.parseInt(scan.nextLine());
                            if (option > 4 || option < 0) {
                                throw new Exception();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println(
                                    "Invalid Choice. Please input a valid digit (0 to 4)"
                            );
                        }
                    }

                    // View books
                    if (option == 1) {
                        library.viewBooks(library.libraryBooks);
                    }
                    // view checked out books
                    if (option == 2) {
                        List<Book> checkedOutBooks = new ArrayList<>();

                        for (Book book : library.libraryBooks) {
                            if (book.dueDate != null) {
                                checkedOutBooks.add(book);
                            }
                        }
                        library.viewBooks(checkedOutBooks);
                    }
                    // view members
                    if (option == 3) {
                        library.viewMembers(library.libraryMembers);
                    }
                    // view notification log
                    if (option == 4) {

                    }

                }
                // Search...
                case 5 -> {
                    System.out.println("""
                                       
                                       What would you like to search?
                                       
                                       0. Back to Menu
                                       1. Books
                                       2. Members
                                       """);

                    // Validate Input
                    while (true) {
                        System.out.print("Option: ");

                        try {
                            option = Integer.parseInt(scan.nextLine());
                            if (option > 2 || option < 0) {
                                throw new Exception();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println(
                                    "Invalid Choice. Please input a valid digit (0 to 2)"
                            );
                        }
                    }

                    // Search for Books
                    if (option == 1) {

                        library.viewBooks(library.searchBooks(Prompt.ForBookSearch()));
                    }
                    // Search for Members
                    if (option == 2) {
                        library.viewMembers(library.searchMembers(Prompt.ForMemberSearch()));
                    }
                }
            }
            
            // Write all books/members to libraryData.json 
            library.save();
            
        } while (choice != 0);
        
        // Close threads on exit
        dueDateProcessor.interrupt();
//        notificationProcessor.interrupt();

        // Save on Exit
        library.save();
    }

    /**
     * Method prints the navigation menu.
     */
    public static void showMenu() {

        System.out.println("""
                           What Would You Like To Do?
                           -----------------------------------------------
                           Press 0 to Exit 
                           Press 1 to Add New Book 
                           Press 2 to Register New Member
                           Press 3 to Check Book In/out
                           Press 4 to View...
                           Press 5 to Search...
                           -----------------------------------------------""");
    }

    static class DueDateProcessor implements Runnable {

        private final Library library;
        int checkOutPeriod;
        int dayLateFee;

        public DueDateProcessor(Library library) {
            this.library = library;
            this.checkOutPeriod = library.checkOutPeriod;
            this.dayLateFee = library.dayLateFee;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {

                    // Loop sets books to overdue
                    for (Book book : library.libraryBooks) {
                        book.isOverDue = book.checkIfOverDue(library.checkOutPeriod);
                    }

                    long totalOwed = 0;
                    // Calculate the late fee for each borrowed book (if overdue) and set to member.feesOwed.
                    for (Member member : library.libraryMembers) {
                        for (Book book : member.borrowedBooks) {
                            totalOwed += book.calcFees(checkOutPeriod, dayLateFee);
                        }
                        member.feesOwed = totalOwed;
                    }
                    Thread.sleep(60000);
                }
            } catch (Exception e) {
                System.out.println("DueDateProcessor has encountered an interruption.\n" + e.getMessage());
            }
        }
    }

    static class NotificationProcessor implements Runnable {

        private final Library library;

        public NotificationProcessor(Library library) {
            this.library = library;

        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    
                }
                Thread.sleep(60000);
            } catch (Exception e) {
                System.out.println("NotificationProcessor has encountered an interruption.\n" + e.getMessage());
            }
        }
    }
}