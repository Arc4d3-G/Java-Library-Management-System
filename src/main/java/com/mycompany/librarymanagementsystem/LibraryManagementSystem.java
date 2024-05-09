package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author brdde
 */
public class LibraryManagementSystem {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        /**
         * Create a new Library object and load books/members from the
         * libraryData.json file.
         */
        Library library = new Library();
        library.load();
        System.out.println("Library configured with " + library.checkOutPeriod 
                + "-day Check Out Period & R" + library.dayLateFee 
                + " late fee per day.");

        // Intro image for fun :)
        System.out.println("""
              __...--~~~~~-._   _.-~~~~~--...__
            //    LIBRARY    `V'      BY       \\\\ 
           //    MANAGER v2   |  DEWALD BREED   \\\\ 
          //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ 
         //__.....----~~~~._\\ | /_.~~~~----.....__\\\\
         ===================\\\\|//====================
                            `---`
        -----------------------------------------------""");

        Scanner scan = new Scanner(System.in);
        // Choice used for switch case below
        int choice;
        // used for sub-menu options
        int option;

        // Instanciate and start thread
        Thread dueDateProcessor = new Thread(new DueDateProcessor(library));
        dueDateProcessor.start();

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

                    // Check In
                    if (option == 1) {
                        library.checkIn();
                    }
                    // Check Out
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

                    // View all books
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
                        System.out.println("\n--- Viewing Notification Log ---\n");
                        if (library.libraryNotifications.isEmpty()) {
                            System.out.println("No new notifactions to display...");
                        } else {

                            for (String notif : library.libraryNotifications) {

                                System.out.println(notif);
                            }
                        }

                        System.out.println("\n--- End of List ---\n");

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

        // Close thread on exit
        dueDateProcessor.interrupt();

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

    /**
     * This thread runs on startup and is responsible for setting books as 
     * "overdue", and sending notifications to members.
     */
    static class DueDateProcessor implements Runnable {

        private final Library library;
        private final int checkOutPeriod;
        private final int dayLateFee;

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
                            
                            /**
                             * Generate notification string if book is overdue and
                             * add it to libraryNotifications for viewing.
                             * Note that if the notification is a duplicate, it's not
                             * added.
                             */
                            if (book.isOverDue) {
                                
                                String notif = LocalDate.now() + " - Book \"" 
                                        + book.title + "\" borrowed by member \"" 
                                        + book.borrowedByMember.name + "\" is Over Due."
                                        + " An email notifying them has been sent...";
                                
                                if (!library.libraryNotifications.contains(notif)) {
                                    library.libraryNotifications.add(notif);
                                }

                            }

                        }
                        member.feesOwed = totalOwed;
                    }
                    // Thread sleeps after each iterarion for 60 sec
                    Thread.sleep(60000);
                }
            } catch (InterruptedException e) {
                System.out.println(
                        "DueDateProcessor has encountered an interruption. (" 
                                + e.getMessage() + ")");
            }
        }
    }
}
