package com.mycompany.librarymanagementsystem;

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
        // Instanciate a new Library object.
        Library library = new Library();

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
                    if (choice > 8 || choice < 0) {
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(
                            "Invalid Choice. Please input a valid digit (0 to 8)"
                    );
                }
            }
            

            /**
             * Switch statement with corresponding menu choice procedures. See
             * the Book, Member and Library classes for further documentation on
             * each method.
             */
            switch (choice) {
                /**
                 * Add new books to the library by calling promptForNewBook() to
                 * prompt the user for book info, validate it, and the pass it
                 * on to the Book() constructor. We then pass the new book to
                 * the addBook() method in our library.
                 */
                case 1 -> {
                    Book newBook = promptForNewBook();
                    library.addBook(newBook);
                    assert library.libraryBooks.get(0) == newBook 
                            : "Assertion Error: NewBook was not added.";
                }

                // View all books by passing libraryBooks to viewBooks() method.
                case 2 ->
                    library.viewBooks(library.libraryBooks);

                /**
                 * Search for books by calling searchBooks() and passing the
                 * returned List to viewBooks().
                 */
                case 3 -> {
                    List<Book> bookSearchResults = library.searchBooks();
                    library.viewBooks(bookSearchResults);
                }

                /**
                 * Add new books to the library by calling promptForNewMember() 
                 * to prompt the user for Member info, validate it, and the pass it
                 * on to the Member() constructor. We then pass the new Member to
                 * the addMember() method in our library.
                 */
                case 4 -> {
                    Member newMember = promptForNewMember();
                    library.addMember(newMember);
                    assert library.libraryMembers.get(0) == newMember 
                            : "Assertion Error: newMember was not added.";
                }

                // View all Members by passing libraryMembers to viewMembers().
                case 5 ->
                    library.viewMembers(library.libraryMembers);

                /**
                 * Search for Members by calling searchMembers() and passing the
                 * returned List to viewBooks().
                 */
                case 6 -> {
                    List<Member> memberSearchResults = library.searchMembers();
                    library.viewMembers(memberSearchResults);
                }

                //Checkout books by calling checkOut() method.
                case 7 ->
                    library.checkOut();
                    

                //Checkin books by calling checkIn() method.
                case 8 ->
                    library.checkIn();
            }

        } while (choice != 0);// Program exit condition
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
                           Press 2 to View All Books 
                           Press 3 to Search For Book 
                           Press 4 to Register New Member 
                           Press 5 to View All Members 
                           Press 6 to Search for Member
                           Press 7 to Check Out Book 
                           Press 8 to Check In Book 
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
        boolean isAvailable;

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

        /**
         * Validation loop with error handling for book isAvailible prop. User
         * needs to input "Y"/"N" to set isAbailible to true/false.
         */
        while (true) {

            System.out.print(
                    "Is the Book currently availible for checkout? Y/N: "
            );

            try {
                String input = scan.nextLine();

                if ((input.toUpperCase()).equals("Y")) {
                    isAvailable = true;
                    break;
                } else if ((input.toUpperCase()).equals("N")) {
                    isAvailable = false;
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(
                        "Invalid Input. Please enter either \"Y\" or \"N\"."
                );
            }
        }
        Book newBook = new Book(ISBN, title, author, isAvailable);
        return newBook;
    }

    /**
     * Prompts and validates user input to set the property values of a new Member
     * object. The values are then passed to the Member() constructor and the new
     * Member is returned by this function.
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

}
