package com.mycompany.librarymanagementsystem;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author brdde
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {
        

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
        // Instanciate a new Library object.
        Library library = new Library();
        
        Book book1 = new Book(1111111111111L, "The Fellowship of the Ring", "J.R. Tolkien");
        Book book2 = new Book(222222222222L, "The Two Towers", "J.R. Tolkien");
        Book book3 = new Book(33333333333L, "The Return of the King", "J.R. Tolkien");
        Book book4 = new Book(4444444444L, "Moby Dick", "Herman Melville");
        Book book5 = new Book(5555555555555L, "1984", "George Orwell");
        Member member1 = new Member("Dewald Breed", "dewaldbreed@gmail.com");
        Member member2 = new Member("Leandri Breed", "leandribreed@gmail.com");
        Member member3 = new Member("Jade Hastings", "jadehastings@protonmail.com");
        Member member4 = new Member("Steve", "steve@stevemail.co.za");

        Book[] testingBooks = {book1, book2, book3, book4, book5};
        Member[] testingMembers = {member1, member2, member3, member4};
        
        library.libraryBooks.addAll(Arrays.asList(testingBooks));
        library.libraryMembers.addAll(Arrays.asList(testingMembers));

        /**
         * This loop continues to display the menu, prompting the user for an
         * option until "0" is input, at which point the loop exits and the
         * program ends.
         */
        do {
            // Print the navigation menu at the start of each loop through.
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

                case 1 -> { // add book
                    Book newBook = promptForNewBook();
                    library.addBook(newBook);
                }

                case 2 -> {//  add member
                    Member newMember = promptForNewMember();
                    library.addMember(newMember);
                }

                case 3 -> { // check in/out
                    
                    System.out.println("""
                                       Would you like to check In or Out?
                                       
                                       0. Back to Menu
                                       1. Check In
                                       2. Check Out
                                       
                                       """);
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

                case 4 -> { // View...
                    System.out.println("""
                                       What would you like to view?
                                       
                                       0. Back to Menu
                                       1. View All Books
                                       2. View All Members
                                       3. View Due Dates
                                       4. View Notification Log
                                       
                                       """);
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
                    
                    if (option == 1) {
                       library.viewBooks(library.libraryBooks);
                    }

                    if (option == 2) {
                        library.viewMembers(library.libraryMembers);
                    }
                    
                    if (option == 3) {
                        // vIEW OVERDUE BOOKS
                    }
                    
                    if (option == 4) {
                        // VIEW NOTIFICATION LOG
                    }
                    
                    
                }

                case 5 -> { // search
                    System.out.println("""
                                       What would you like to search?
                                       
                                       0. Back to Menu
                                       1. Search for Books
                                       2. Search for Members

                                       """);
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
                       List<Book> bookSearchResults = library.searchBooks(promptForBookSearch());
                        library.viewBooks(bookSearchResults); 
                    }

                    if (option == 2) {
                        List<Member> memberSearchResults = library.searchMembers(promptForMemberSearch());
                        library.viewMembers(memberSearchResults);
                    }
                }
            } 
        } while(choice != 0);
    }

    /**
     * Method prints the navigation menu.
     */
    public static void showMenu() {

        System.out.println("""
                           What Would You Like To Do?      No New Notificatons
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
     * Prompts and validates user input to set the property values of a new Book
     * object. The values are then passed to the Book() constructor and the new
     * Book is returned by this function.
     *
     * @return Book newBook - A newly constructed Book.
     */
    public static Book promptForNewBook() {

        Scanner scan = new Scanner(System.in);

        // Variables for book constructor
        long ISBN;
        String title;
        String author;

        System.out.println("""

            ---- Adding New Book ----

            Please provide the book's ISBN number, Title & Author.
            """);

        /**
         * Validation loop with error handling for book ISBN number. ISBN needs
         * to be 10-13 digits to be valid.
         */
        while (true) {

            System.out.print("ISBN Number (10-13 digits): ");

            try {
                String input = scan.nextLine();
                long inputAsLong = Long.parseLong(input);
                int length = input.length();

                if (length >= 10 && length <= 13 && inputAsLong > 0) {
                    ISBN = inputAsLong;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid ISBN. Please ensure the ISBN cosists"
                        + " of 10-13 digits without any letters/symbols."
                );
            }
        }

        /**
         * Validation loop with error handling for book Title number. Title is
         * valid so long as it's not empty.
         */
        while (true) {

            System.out.print("Enter Book Title: ");

            try {
                String input = scan.nextLine();

                if (input.trim().length() > 0) {
                    title = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Title. Please ensure the Title is not empty."
                );
            }
        }

        /**
         * Validation loop with error handling for book Author number. Author is
         * valid so long as it's not empty.
         */
        while (true) {

            System.out.print("Enter Book Author: ");

            try {
                String input = scan.nextLine();

                if (input.trim().length() > 0) {
                    author = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Author. Please ensure the Author is not empty."
                );
            }
        }

        Book newBook = new Book(ISBN, title, author);
        return newBook;
    }

    /**
     * Prompts and validates user input to set the property values of a new
     * Member object. The values are then passed to the Member() constructor and
     * the new Member is returned by this function.
     *
     * @return Member - new Member - A newly constructed Member
     */
    public static Member promptForNewMember() {

        Scanner scan = new Scanner(System.in);

        // Variables for book constructor
        String name;
        String email;
        System.out.println("""
                           
                           ---- Registering New Member ----
                           
                           Please provide the Member's Name & Email Address.
                           """);

        /**
         * Validation loop with error handling for Member Name. Name needs to be
         * less than 50 characters and cannot be empty.
         */
        while (true) {

            System.out.print("Enter Name: ");

            try {
                String input = scan.nextLine();

                if (input.length() > 0 && input.length() < 51) {
                    name = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Name. Please ensure the Name is not"
                        + " empty and is no more than 50 characters long.");
            }
        }

        /**
         * Validation loop with error handling for Member Email. Email needs to
         * be match the regex pattern to be valid.
         */
        while (true) {

            System.out.print("Enter Email Address: ");

            try {
                String input = scan.nextLine();

                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    email = input;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Email. Please ensure the input is a"
                        + " valid email address.");
            }
        }

        Member newMember = new Member(name, email);
        return newMember;
    }

    /**
     * Prompts and validates user input for a book search. First we determine if
     * the search is by book title or author, and then we use a switch case to
     * prompt for the search keyword. Finally, both the search option and
     * keyword are placed in an array which is returned for searchBooks() to
     * use.
     *
     * @return searchParam[] - First element indicates if search is by title or
     * author, second element is the keyword provided by the user.
     */
    public static String[] promptForBookSearch() {

        Scanner scan = new Scanner(System.in);
        String[] searchParam = new String[2];
        int choice;

        System.out.println("""
                               --- Search for Books ---
                           
                               Would you like to search by Author or Title?
                               
                               1. Title
                               2. Author
                               """);

        // Input validation loop
        while (true) {

            System.out.print("Option: ");

            try {
                choice = Integer.parseInt(scan.nextLine());
                if (choice > 2 || choice <= 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(
                        "Invalid Choice. Please input a valid digit (1 or 2)."
                );
            }
        }

        // Switch case which prompts for the appropriate search and validates input
        switch (choice) {

            case 1 -> {
                // Search by title
                searchParam[0] = "title";

                while (true) {

                    System.out.print("Enter Book Title: ");

                    // Input validation loop
                    try {
                        String input = scan.nextLine();

                        if (input.length() > 0) {
                            searchParam[1] = input;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Title. Please ensure the Title is not empty."
                        );
                    }
                }
            }

            case 2 -> {
                // Search by author
                searchParam[0] = "author";

                // Input validation loop
                while (true) {

                    System.out.print("Enter Book Author: ");

                    try {
                        String input = scan.nextLine();

                        if (input.length() > 0) {
                            searchParam[1] = input;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Author. Please ensure the Author is not empty."
                        );
                    }
                }
            }

        }

        return searchParam;
    }

    /**
     * Similar to promptForBookSearch(), but prompts for member search key
     * instead.
     *
     * @return searchParam[] - First element indicates if search is by name or
     * email, second element is the keyword provided by the user.
     */
    public static String[] promptForMemberSearch() {

        Scanner scan = new Scanner(System.in);
        String[] searchParam = new String[2];
        int choice;

        System.out.println("""
                               --- Search for Members ---
                           
                               Would you like to search by Name or Email?
                               
                               1. Name
                               2. Email
                               """);

        // Input validation loop
        while (true) {

            System.out.print("Option: ");

            try {
                choice = Integer.parseInt(scan.nextLine());
                if (choice > 2 || choice <= 0) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println(
                        "Invalid Choice. Please input a valid digit (1 or 2)."
                );
            }
        }

        // Switch case which prompts for the appropriate search and validates input
        switch (choice) {

            case 1 -> {
                // Search by Name
                searchParam[0] = "name";

                // Input validation loop
                while (true) {

                    System.out.print("Enter Member Name: ");

                    try {
                        String input = scan.nextLine();

                        if (input.length() > 0) {
                            searchParam[1] = input;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Name. Please ensure the Name is not empty."
                        );
                    }
                }
            }

            case 2 -> {
                // Search by email
                searchParam[0] = "email";

                // Input validation using regex. 
                while (true) {

                    System.out.print("Enter Member Email: ");

                    try {
                        String input = scan.nextLine();
                        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
                        Matcher matcher = pattern.matcher(input);
                        if (matcher.matches()) {
                            String searchEmail = input;
                            searchParam[1] = searchEmail;
                            break;
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Invalid Email. Please ensure the email is a"
                                + " valid email address."
                        );
                    }
                }
            }
        }
        return searchParam;
    }
}
