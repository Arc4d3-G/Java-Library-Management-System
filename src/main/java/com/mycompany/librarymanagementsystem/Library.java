package com.mycompany.librarymanagementsystem;

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
    
    Scanner scan = new Scanner(System.in);
    
    /**
     * Method to add new books to {@link #libraryBooks} list. Before adding the 
     * new book to the library, it compares the new book's ISBN number with all 
     * other books in libraryBooks to ensure it is not a duplicate. 
     * 
     * @param newBook - Accepts a book object as a parameter.
     */
    public void addBook(Book newBook){
        
        boolean alreadyAdded = false;
        /**
         * Loop through to check if a book with an identical ISBN already exists
         * in the library. 
         */
        for( int i = 0; i < libraryBooks.size(); i++) {
            if (newBook.ISBN == libraryBooks.get(i).ISBN){
                alreadyAdded = true;
                break;
            }
        }
        
        /**
         * If a match is not found, the book will be added to the library, else 
         * a message is printed stating that the book already exists.
         */
        if (alreadyAdded == false){
            libraryBooks.add(newBook);
            System.out.println(
                            "\n--- Book Successfully Added to Library ---\n"
                    );
        } else {
            System.out.println(
                    "\nA book with ISBN #" + newBook.ISBN 
                    + " already exists in library. "
                    + "\nThe new book was not added to the library.\n");
        }

    }
    
    /**
     * This method is used to display multiple book's info in a table format.
     * It can be used to either display all books in the library by passing
     * it the {@link #libraryBooks} list as a parameter, or it can display 
     * search results by passing the searchResults list 
     * from {@link #searchBooks()}.
     * 
     * @param results - accepts a List of Books as a parameter.
     */
    public void viewBooks(List<Book> results){
        
        /**
         * Display appropriate heading for table.
         */
        if (results == libraryBooks){
            System.out.println("\n--- Viewing All Library Books ---\n");
        } else {
            System.out.println("\n--- Viewing Search Results ---\n");
        }
        
        if (!results.isEmpty()) {
            /**
             * Using "printf" to format the string to create a table row.
             * In the format we specify we want the text to be left-aligned 
             * with "-" followed by a digit representing the width, and finally
             * "s" to indicate we're formatting a string.
             */
            System.out.printf(
                    "%-16s %-25s %-40s %-10s \n", 
                    "ISBN", "Author", "Title", "Available?"
            );
            System.out.println(
                    "-----------------------------------------------"
                    + "------------------------------------------------"
            );

            /**
             * For loop to print a formatted string containing book info for
             * each Book in our parameter list object.
             */
            for( int i = 0; i < results.size(); i++){
                
                long ISBN = results.get(i).ISBN;
                String title = results.get(i).title;
                String author = results.get(i).author;
                String isAvailible = results.get(i).isAvailible 
                        ? "Yes" 
                        : "No";
                
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
    public void addMember(Member newMember){
        
        boolean alreadyAdded = false;
        /**
         * Loop through libraryMembers to check if a member with an identical 
         * email already exists.
         */
        for( int i = 0; i < libraryMembers.size(); i++) {
            if (newMember.email.equalsIgnoreCase(libraryMembers.get(i).email)){
                alreadyAdded = true;
                break;
            }
        }
        
        /**
         * If a match is not found, the member will be added to library, else 
         * a message is printed stating that the member already exists.
         */
        if (alreadyAdded == false){
            libraryMembers.add(newMember);
            System.out.println(
                            "\n--- Member Successfully Registered ---\n"
                    );
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
    public void viewMembers(List<Member> results){
        
        /**
         * Display appropriate heading for table.
         */
        if (results == libraryMembers){
            System.out.println("\n--- Viewing All Library Members ---\n");
        } else {
            System.out.println("\n--- Viewing Search Results ---\n");
        }
        
        if (!results.isEmpty()) {
            /**
             * Using "printf" to format the string to create a table row.
             * In the format we specify we want the text to be left-aligned 
             * with "-" followed by a digit representing the width, and finally
             * "s" to indicate we're formatting a string.
             */   
            System.out.println(
                    "-----------------------------------------------"
                    + "------------------------------------------------"
            );
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
            for( int i = 0; i < results.size(); i++){
                Member member = results.get(i);
                boolean isBorrowing = !member.borrowedBooks.isEmpty();

                System.out.printf("%-30s %-30s %-40s\n", 
                    member.name, member.email, isBorrowing ? member.borrowedBooks.get(0).title : "NONE");
                
                if(member.borrowedBooks.size() > 1){
                    for (int j = 1; j < member.borrowedBooks.size(); j++){
                    System.out.printf(
                            "%-30s %-30s %-40s\n"," ", " ",
                            member.borrowedBooks.get(j).title);
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
     * Method used to initiate a book search. The user is prompted to specify 
     * wether the search is by author or title, which is then validated, and
     * then appropriate switch case is used to prompt the user for the search 
     * keyword/s. Once validated the keyword is used to see if any of the 
     * library books match. The matching books are added to a searchResults list
     * which is then returned by the method for {@link #viewMembers} to use.
     * 
     * @return searchResults - A list object containing all matching books.
     */
    public List<Book> searchBooks(){
        
        // New object to be populated and returned by method.
        List<Book> searchResults = new ArrayList<>();
        int choice;
        System.out.println("""
                               --- Search for Books ---
                           
                               Would you like to search by Author or Title?
                               
                               1. Title
                               2. Author
                               """);
        
        // Input validation loop
        while(true) {

            System.out.print("Option: ");
            
            try {
                choice = Integer.parseInt(scan.nextLine());
                if(choice > 2 || choice < 0){
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
        switch (choice){
                
                case 1: // Search by title
                    String searchTitle;
                    
                    while(true) {
            
                        System.out.print("Enter Book Title: ");
                        
                        // Input validation loop
                        try {
                            String input = scan.nextLine();

                            if(input.length() > 0){
                                searchTitle = input;
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
                     * Loop through each book in libraryBooks and check if 
                     * searchTitle equals the book's title. We use equalsIgnoreCase
                     * to ensure the search is case insensitive. Matches are added
                     * to the searchResults list.
                     */
                    for ( int i = 0; i < libraryBooks.size(); i++){
                        if (libraryBooks.get(i).title.equalsIgnoreCase(searchTitle)){
                            searchResults.add(libraryBooks.get(i));
                        }
                    }
                    
                    break;
                    
                case 2: // Search by author
                    String searchAuthor;
                    
                    // Input validation loop
                    while(true) {
            
                        System.out.print("Enter Book Author: ");
                                    
                        try {
                            String input = scan.nextLine();

                            if(input.length() > 0){
                                searchAuthor = input;
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
                     * Similar to case 1 loop, but matches by author instead.
                     */
                    for ( int i = 0; i < libraryBooks.size(); i++){
                        if (libraryBooks.get(i).author.equalsIgnoreCase(searchAuthor)){
                            searchResults.add(libraryBooks.get(i));
                        }
                    }
                    break;   
            }
        return searchResults;
    }
    
    /**
     * Method used to initiate a member search. The user is prompted to specify 
     * wether the search is by name or email, which is then validated, and
     * then appropriate switch case is used to prompt the user for the search 
     * keyword/s. Once validated the keyword is used to see if any of the 
     * library members match. The matching members are added to a searchResults list
     * which is then returned by the method for {@link #viewMembers} to use.
     * 
     * @return searchResults - A list object containing all matching members.
     */
    public List<Member> searchMembers(){
        
        // New object to be populated and returned by method.
        List<Member> searchResults = new ArrayList<>();
        int choice;
        System.out.println("""
                               --- Search for Members ---
                           
                               Would you like to search by Name or Email?
                               
                               1. Name
                               2. Email
                               """);
        
        // Input validation loop
        while(true) {

            System.out.print("Option: ");
            
            try {
                choice = Integer.parseInt(scan.nextLine());
                if(choice > 2 || choice < 0){
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
        switch (choice){
                
                case 1: // Search by Name
                    String searchName;
                    
                    // Input validation loop
                    while(true) {
            
                        System.out.print("Enter Member Name: ");
                                    
                        try {
                            String input = scan.nextLine();

                            if(input.length() > 0){
                                searchName = input;
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
                    
                    /**
                     * Loop through each member in libraryMembers and check if 
                     * searchName equals the member's name. We use equalsIgnoreCase
                     * to ensure the search is case insensitive. Matches are added
                     * to the searchResults list.
                     */
                    for ( int i = 0; i < libraryMembers.size(); i++){
                        if (libraryMembers.get(i).name.equalsIgnoreCase(searchName)){
                            searchResults.add(libraryMembers.get(i));
                        }
                    }
                    
                    break;
                    
                case 2: // Search by author
                    String searchEmail;
                    

                    // Input validation using regex. 
                    while(true) {
            
                        System.out.print("Enter Member Email: ");
                                    
                        try {
                            String input = scan.nextLine();
                            String regex = "^(.+)@(.+)$";
                            Pattern pattern = Pattern.compile(regex);
                            Matcher matcher = pattern.matcher(input);
                            if(matcher.matches()){
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
                    
                    /**
                     * Similar to case 1 loop, but matches by email instead.
                     */
                    for ( int i = 0; i < libraryMembers.size(); i++){
                        if (libraryMembers.get(i).email.equalsIgnoreCase(searchEmail)){
                            searchResults.add(libraryMembers.get(i));
                        }
                    }
                    break;   
            }
        return searchResults;    
    }
    
    /**
     * Method used for checking-out a book. It first prompts for a registered
     * user to associate the checked out book with, checks if the user is indeed
     * registered, prompts for the book's ISBN, checks if it exists in the library,
     * checks if it is available for checkout, and finally adds the book to the
     * member's borrowedBooks list and sets the book's isAvailable to false.
     */
    public void checkOut(){
        
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
        while(true) {
            
            System.out.print("Enter Member Email: ");

            try {
                String input = scan.nextLine();
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(matcher.matches()){
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
        for ( int i = 0; i < libraryMembers.size(); i++){
            
            Member member = libraryMembers.get(i);
            if (member.email.equalsIgnoreCase(searchEmail)){
                isMember = true;
                matchedMember = member;
                break;
            }
        }
        
        // If member is registed, proceeds with checkout, else checkout is aborted
        if (isMember){
            System.out.println("""
                             
                             Member Match Found!
                             Please provide the ISBN number of the book being checked out""");
            
            // Prompt for book ISBN and validate input
            while(true) {

                System.out.print("Book ISBN: ");

                try {
                    String input = scan.nextLine();
                    long inputAsLong = Long.parseLong(input);
                    int length = input.length();

                    if (length >= 10 && length <= 13){
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
            for ( int i = 0; i < libraryBooks.size(); i++){
                Book book = libraryBooks.get(i);
                if (book.ISBN == searchISBN){
                    matchedBook = book;
                    isBook = true;
                }
            }
            
            /**
             * Only if the book is registered & is available for checkout will
             * the book be checked out. This is done by setting isAvailable to
             * false, and adding the book the the member's borrowedBooks list.
             */
            if (isBook && matchedBook.isAvailible){
                
                matchedBook.isAvailible = false;
                matchedMember.borrowedBooks.add(matchedBook);
                
                System.out.println("\nBook \"" + matchedBook.title 
                        + "\" has successfully been checked out by Member \"" 
                        + matchedMember.name + "\"."
                        + "\n---- Checkout Complete ----\n");
                
            // if the book exists but isn't available, checkout is aborted.    
            } else if (isBook && matchedBook.isAvailible == false) {
                
                System.out.println("\nBook \"" + matchedBook.title + 
                        "\" is not availible for Check Out. "
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
     * availability to true and removes the book from the member's 
     * borrowedBooks list.
     */
    public void checkIn(){
        
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
        while(true) {
            
            System.out.print("Enter Member Email: ");

            try {
                String input = scan.nextLine();
                String regex = "^(.+)@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(matcher.matches()){
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
        for ( int i = 0; i < libraryMembers.size(); i++){
            
            Member member = libraryMembers.get(i);
            if (member.email.equalsIgnoreCase(searchEmail)){
                isMember = true;
                matchedMember = member;
                break;
            }
        }
        
        // If member is registed, proceeds with checkout, else checkout is aborted
        if (isMember){
            System.out.println("""
                             
                             Member Match Found!
                             Please provide the ISBN number of the book being checked in""");
            
            // Prompt for book ISBN and validate input
            while(true) {

                System.out.print("Book ISBN: ");

                try {
                    String input = scan.nextLine();
                    long inputAsLong = Long.parseLong(input);
                    int length = input.length();

                    if (length >= 10 && length <= 13){
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
            for ( int i = 0; i < libraryBooks.size(); i++){
                Book book = libraryBooks.get(i);
                if (book.ISBN == searchISBN){
                    matchedBook = book;
                    isBook = true;
                }
            }
            
            boolean isBorrowed = matchedMember.borrowedBooks.contains(matchedBook);
            
            /**
             * Only if the book is registered and was checked out by the member 
             * will the book be checked out. This is done by setting isAvailable to
             * true, and removing the book the the member's borrowedBooks list.
             */
            if (isBook && isBorrowed){
                
                matchedBook.isAvailible = true;
                matchedMember.borrowedBooks.remove(matchedBook);
                
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
