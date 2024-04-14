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
            System.out.printf(
                    "%-30s %-30s %-5s \n",
                    "Name", "Email", "# of Books Checked Out" 
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
                
                String name = results.get(i).name;
                String email = results.get(i).email;
                int numOfCheckedOut = results.get(i).borrowedBooks.size();
                
                System.out.printf("%-30s %-30s %-5s \n", 
                        name, email,numOfCheckedOut);
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
   
}
