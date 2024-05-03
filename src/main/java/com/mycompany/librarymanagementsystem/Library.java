package com.mycompany.librarymanagementsystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author brdde
 */
public class Library {

    public List<Book> libraryBooks = new ArrayList<>();
    public List<Member> libraryMembers = new ArrayList<>();
    public int checkOutPeriod = 7;

    Scanner scan = new Scanner(System.in);

    /**
     * Method to add new books to {@link #libraryBooks} list. Before adding the
     * new book to the library, it compares the new book's ISBN number with all
     * other books in libraryBooks to ensure it is not a duplicate.
     *
     * @param newBook - Accepts a book object as a parameter.
     */
    public void addBook(Book newBook) {

        boolean alreadyAdded = false;
        /**
         * Loop to check if a book with an identical ISBN already exists in the
         * library.
         */
        for (int i = 0; i < libraryBooks.size(); i++) {
            if (newBook.ISBN == libraryBooks.get(i).ISBN) {
                alreadyAdded = true;
                break;
            }
        }

        /**
         * If no match is found, the book will be added to the library, else a
         * message is printed stating that the book already exists.
         */
        if (alreadyAdded == false) {
            libraryBooks.add(newBook);
            System.out.println(
                    "\n--- Book Successfully Added to Library ---\n"
            );

            // Assertion for testing
            assert libraryBooks.get(libraryBooks.size() - 1) == newBook : "Assertion Error: NewBook was not added.";

        } else {
            System.out.println(
                    "\nA book with ISBN #" + newBook.ISBN
                    + " already exists in library. "
                    + "\nThe new book was not added to the library.\n");
        }

    }

    /**
     * This method is used to display multiple book's info in a table format. It
     * can be used to either display all books in the library by passing it the
     * {@link #libraryBooks} list as a parameter, or it can display search
     * results by passing the searchResults list from {@link #searchBooks()}.
     *
     * @param results - accepts a List of Books as a parameter.
     */
    public void viewBooks(List<Book> results) {

        /**
         * Display appropriate heading for table.
         */
        if (results == libraryBooks) {
            System.out.println("\n--- Viewing All Library Books ---\n");
        } else {
            System.out.println("\n--- Viewing Search Results ---\n");
        }

        if (!results.isEmpty()) {
            /**
             * Using "printf" to format the string to create a table row. In the
             * format we specify we want the text to be left-aligned with "-"
             * followed by a digit representing the width, and finally "s" to
             * indicate we're formatting a string.
             */
            System.out.printf(
                    "%-16s %-25s %-40s %-10s \n",
                    "ISBN", "Author", "Title", "Status"
            );
            System.out.println(
                    "-----------------------------------------------"
                    + "------------------------------------------------"
            );

            /**
             * Loop to print a formatted string containing book info for each
             * Book in our parameter list object.
             */
            for (int i = 0; i < results.size(); i++) {

                Book book = results.get(i);
                long ISBN = book.ISBN;
                String title = book.title;
                String author = book.author;
                String isAvailible = book.isAvailable
                        ? "Availible"
                        : "Checked out (Member: " + book.borrowedByMember.email + ", Due date: " + book.dueDate + ")";

                System.out.printf(
                        "%-16s %-25s %-40s %-10s \n",
                        ISBN, author, title, isAvailible
                );
            }
            System.out.println(
                    "-----------------------------------------------"
                    + "------------------------------------------------\n"
            );

        } else {
            System.out.println("No Results Found.\n");
        }
        System.out.println("--- End of List ---\n");
    }

    /**
     * Method to add new members to {@link #libraryMembers} list. Before adding
     * the new Member to the library, it compares the new member's Email with
     * all other member emails in libraryMembers to ensure its not a duplicate.
     *
     * @param newMember - Accepts a book object as a parameter.
     */
    public void addMember(Member newMember) {

        boolean alreadyAdded = false;
        /**
         * Loop through libraryMembers to check if a member with an identical
         * email already exists.
         */
        for (int i = 0; i < libraryMembers.size(); i++) {
            if (newMember.email.equalsIgnoreCase(libraryMembers.get(i).email)) {
                alreadyAdded = true;
                break;
            }
        }

        /**
         * If a match is not found, the member will be added to library, else a
         * message is printed stating that the member already exists.
         */
        if (alreadyAdded == false) {
            libraryMembers.add(newMember);
            System.out.println(
                    "\n--- Member Successfully Registered ---\n"
            );

            // Assertion for testing
            assert libraryMembers.get(libraryMembers.size() - 1) == newMember : "Assertion Error: NewMember was not added.";

        } else {
            System.out.println(
                    "\nA member with Email Address \"" + newMember.email
                    + "\" already exists. "
                    + "\nThe new Member was not registered.\n");
        }

    }

    /**
     * This method is used to display multiple member's info in a table format.
     * It can be used to either display all members by passing it the
     * {@link #libraryMembers} list as a parameter, or it can display search
     * results by passing the searchResults list from {@link #searchMembers()}.
     *
     * @param results - accepts a List of Members as a parameter.
     */
    public void viewMembers(List<Member> results) {

        /**
         * Display appropriate heading for table.
         */
        if (results == libraryMembers) {
            System.out.println("\n--- Viewing All Library Members ---\n");
        } else {
            System.out.println("\n--- Viewing Search Results ---\n");
        }

        if (!results.isEmpty()) {
            /**
             * Using "printf" to format the string to create a table row. In the
             * format we specify we want the text to be left-aligned with "-"
             * followed by a digit representing the width, and finally "s" to
             * indicate we're formatting a string.
             */

            System.out.printf(
                    "%-30s %-30s %-100s \n",
                    "NAME", "EMAIL", "CURRENT BOOKS CHECKED OUT"
            );
            System.out.println(
                    "-----------------------------------------------"
                    + "------------------------------------------------"
            );

            /**
             * For loop to print a formatted string containing member info for
             * each member in our parameter list object.
             */
            for (int i = 0; i < results.size(); i++) {
                Member member = results.get(i);

                boolean isBorrowing = !member.borrowedBooks.isEmpty();

                System.out.printf("%-30s %-30s %-40s\n",
                        member.name, member.email, isBorrowing
                                ? (member.borrowedBooks.get(0).title + " (Due " + member.borrowedBooks.get(0).dueDate + ")")
                                : "NONE");

                if (member.borrowedBooks.size() > 1) {
                    for (int j = 1; j < member.borrowedBooks.size(); j++) {
                        System.out.printf(
                                "%-30s %-30s %-40s\n", " ", " ",
                                member.borrowedBooks.get(j).title + " (Due " + member.borrowedBooks.get(j).dueDate + ")");
                    }
                }

                System.out.println(
                        "-----------------------------------------------"
                        + "------------------------------------------------"
                );
            }

        } else {
            System.out.println("\nNo Results Found.\n");
        }

        System.out.println("\n--- End of List ---\n");

    }

    /**
     * Method searches through libraryBooks for any elements containing the
     * search keyword. The parameter searchParams provides the book property to
     * search by, and the keyword provided by the user.
     *
     * @param searchParam - first element should be either "title" or "author".
     * Second element should be a the user provided keyword.
     * @return searchResults - List containing all matching Books. Can be empty
     * if no matches.
     */
    public List<Book> searchBooks(String[] searchParam) {

        // New object to be populated and returned by method.
        List<Book> searchResults = new ArrayList<>();

        switch (searchParam[0]) {
            case "title" -> {
                /**
                 * Loop through each book in libraryBooks and check if it's
                 * title contains the searchTitle string. We use .toLowerCase to
                 * ensure the search is case insensitive. Matches are added to
                 * the searchResults list.
                 */
                for (int i = 0; i < libraryBooks.size(); i++) {
                    if (libraryBooks.get(i).title.toLowerCase().contains(searchParam[1].toLowerCase())) {
                        searchResults.add(libraryBooks.get(i));
                    }
                }
            }

            case "author" -> {
                /**
                 * Similar to case 1 loop, but matches by author instead.
                 */
                for (int i = 0; i < libraryBooks.size(); i++) {
                    if (libraryBooks.get(i).author.toLowerCase().contains(searchParam[1].toLowerCase())) {
                        searchResults.add(libraryBooks.get(i));
                    }
                }
            }
        }
        return searchResults;
    }

    /**
     * Similar to searchBooks(), but for searching members instead.
     *
     * @param searchParam - first element should be either "name" or "email".
     * Second element should be a the user provided keyword.
     * @return searchResults - List containing all matching members. Can be
     * empty if no matches.
     */
    public List<Member> searchMembers(String[] searchParam) {

        // New object to be populated and returned by method.
        List<Member> searchResults = new ArrayList<>();

        switch (searchParam[0]) {
            case "name" -> {
                /**
                 * Loop through each member in libraryMembers and check if its
                 * name contains the searchName string. We use .toLowerCase() to
                 * ensure the search is case insensitive. Matches are added to
                 * the searchResults list.
                 */
                for (int i = 0; i < libraryMembers.size(); i++) {
                    if (libraryMembers.get(i).name.toLowerCase().contains(searchParam[1].toLowerCase())) {
                        searchResults.add(libraryMembers.get(i));
                    }
                }
            }

            case "email" -> {
                /**
                 * Similar to case 1 loop, but matches by email instead.
                 */
                for (int i = 0; i < libraryMembers.size(); i++) {
                    if (libraryMembers.get(i).email.toLowerCase().contains(searchParam[1].toLowerCase())) {
                        searchResults.add(libraryMembers.get(i));
                    }
                }
            }
        }

        return searchResults;
    }

    /**
     * Method used for checking-out a book. It first prompts for a registered
     * user to associate the checked out book with, checks if the user is indeed
     * registered, prompts for the book's ISBN, checks if it exists in the
     * library, checks if it is available for checkout, and finally adds the
     * book to the member's borrowedBooks list and sets the book's isAvailable
     * to false.
     */
    public void checkOut() {

        System.out.println("\n---- Checking Out Book ----\n");
        System.out.println(
                "Please provide the Email of the Member checking out "
                + "(must be registered member)."
        );

        // Variables used for validation and comparison
        String searchEmail;
        Member matchedMember = null;
        Book matchedBook = null;
        long searchISBN;
        boolean isMember = false;
        boolean isBook = false;

        // Prompt user for member email and validate using regex
        while (true) {

            System.out.print("Enter Member Email: ");

            try {
                String input = scan.nextLine();
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    searchEmail = input;
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

        // Check if member with input email exists in libraryMembers
        for (int i = 0; i < libraryMembers.size(); i++) {

            Member member = libraryMembers.get(i);
            if (member.email.equalsIgnoreCase(searchEmail)) {
                isMember = true;
                matchedMember = member;
                break;
            }
        }

        // If member is registed, proceeds with checkout, else checkout is aborted
        if (isMember) {
            System.out.println("""
                             
                             Member Match Found!
                             Please provide the ISBN number of the book being checked out""");

            // Prompt for book ISBN and validate input
            while (true) {

                System.out.print("Book ISBN: ");

                try {
                    String input = scan.nextLine();
                    long inputAsLong = Long.parseLong(input);
                    int length = input.length();

                    if (length >= 10 && length <= 13) {
                        searchISBN = inputAsLong;
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

            // Check if book with input ISBN exists in library
            for (int i = 0; i < libraryBooks.size(); i++) {
                Book book = libraryBooks.get(i);
                if (book.ISBN == searchISBN) {
                    matchedBook = book;
                    isBook = true;
                }
            }

            /**
             * Only if the book is registered & is available for checkout will
             * the book be checked out. This is done by setting isAvailable to
             * false, and adding the book the the member's borrowedBooks list.
             */
            if (isBook && matchedBook.isAvailable) {

                matchedBook.isAvailable = false;
                matchedBook.dueDate = LocalDate.now().plusDays(checkOutPeriod);
                matchedBook.borrowedByMember = matchedMember;
                matchedMember.borrowedBooks.add(matchedBook);

                // Assertions for testing
                assert matchedBook.isAvailable == false : "Assertion Error: Book's availability was not set to false";
                assert matchedMember.borrowedBooks.get(matchedMember.borrowedBooks.size() - 1) == matchedBook : "Assertion Error: Book was not added to Member's borrowedBooks";

                System.out.println("\nBook \"" + matchedBook.title
                        + "\" has successfully been checked out by Member \""
                        + matchedMember.name + "\"."
                        + "\n---- Checkout Complete ----\n");

                // if the book exists but isn't available, checkout is aborted.    
            } else if (isBook && matchedBook.isAvailable == false) {

                System.out.println("\nBook \"" + matchedBook.title
                        + "\" is not availible for Check Out. "
                        + "Checkout has been aborted.\n");

                // If book does not exists then checkout is aborted.
            } else {

                System.out.println("\nNo Book with ISBN \"" + searchISBN
                        + "\" was found. Checkout has been aborted.\n");
            }

        } else {
            System.out.println("\nNo Member with email \"" + searchEmail
                    + "\" was found. Checkout has been aborted.\n");
        }
    }

    /**
     * Method is similar to {@link #checkOut()}, but it set's the book's
     * availability to true and removes the book from the member's borrowedBooks
     * list.
     */
    public void checkIn() {

        System.out.println("\n---- Checking In Book ----\n");
        System.out.println(
                "Please provide the Email of the Member checking in "
                + "(must be registered member)."
        );

        // Variables used for validation and comparison
        String searchEmail;
        Member matchedMember = null;
        Book matchedBook = null;
        long searchISBN;
        boolean isMember = false;
        boolean isBook = false;

        // Prompt user for member email and validate using regex
        while (true) {

            System.out.print("Enter Member Email: ");

            try {
                String input = scan.nextLine();
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    searchEmail = input;
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

        // Check if member with input email exists in libraryMembers
        for (int i = 0; i < libraryMembers.size(); i++) {

            Member member = libraryMembers.get(i);
            if (member.email.equalsIgnoreCase(searchEmail)) {
                isMember = true;
                matchedMember = member;
                break;
            }
        }

        // If member is registed, proceeds with checkout, else checkout is aborted
        if (isMember) {
            System.out.println("""
                             
                             Member Match Found!
                             Please provide the ISBN number of the book being checked in""");

            // Prompt for book ISBN and validate input
            while (true) {

                System.out.print("Book ISBN: ");

                try {
                    String input = scan.nextLine();
                    long inputAsLong = Long.parseLong(input);
                    int length = input.length();

                    if (length >= 10 && length <= 13) {
                        searchISBN = inputAsLong;
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

            // Check if book with input ISBN exists in library
            for (int i = 0; i < libraryBooks.size(); i++) {
                Book book = libraryBooks.get(i);
                if (book.ISBN == searchISBN) {
                    matchedBook = book;
                    isBook = true;
                }
            }

            boolean isBorrowed = matchedMember.borrowedBooks.contains(matchedBook);

            /**
             * Only if the book is registered and was checked out by the member
             * will the book be checked in. This is done by setting isAvailable
             * to true, and removing the book from the member's borrowedBooks
             * list.
             */
            if (isBook && isBorrowed) {
                matchedMember.borrowedBooks.remove(matchedBook);
                matchedBook.isAvailable = true;
                matchedBook.borrowedByMember = null;
                matchedBook.dueDate = null;

                System.out.println("\nBook \"" + matchedBook.title
                        + "\" has successfully been checked in by Member \""
                        + matchedMember.name + "\"."
                        + "\n---- Checkin Complete ----\n");
                /**
                 * If the book exists but was not checked out by the member,
                 * check-in is aborted.
                 */
            } else if (isBook && !isBorrowed) {

                System.out.println("\nNo Book with ISBN \"" + searchISBN
                        + "\" was Checked out by member \""
                        + matchedMember.name + "\". Checkout has been aborted.\n"
                );
                // If book is not found in library, check-in is aborted    
            } else {
                System.out.println("\nNo Book with ISBN \"" + searchISBN
                        + "\" was found. Checkout has been aborted.\n");
            }
            // If member is not registered, check-in is aborted.   
        } else {
            System.out.println("\nNo Member with email \"" + searchEmail
                    + "\" was found. Checkout has been aborted.\n");
        }
    }
}
