/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarymanagementsystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brdde
 */
public class Library {
    
    List<Book> libraryBooks = new ArrayList<>();
    
    List<Member> libraryMembers = new ArrayList<>();
    
    public void addBook(Book newBook){
        
        libraryBooks.add(newBook);
//        System.out.println(libraryBooks.get(0).title);
    }
    
    public void viewAllBooks(){
        
        System.out.println("\n--- Viewing All Library Books ---\n");
        
        if (!libraryBooks.isEmpty()) {
            /**
             * Using "printf" to format the string to create a table row.
             * In the format we specify we want the text to be left-aligned with "-"
             * followed by a digit representing the width, and finally "s" to indicate
             * we're formatting a string.
             */
            System.out.printf("%-16s %-25s %-40s %-10s \n", "ISBN", "Author", 
                    "Title", "Available?");
            System.out.println("---------------------------------------------------"
                    + "--------------------------------------------");

            /**
             * For loop to print a formatted string containing book info for each
             * Book in our libraryBooks.
             */
            for( int i = 0; i < libraryBooks.size(); i++){
                
                long ISBN = libraryBooks.get(i).ISBN;
                String title = libraryBooks.get(i).title;
                String author = libraryBooks.get(i).author;
                String isAvailible = libraryBooks.get(i).isAvailible 
                        ? "Yes" 
                        : "No";
                
                System.out.printf("%-16s %-25s %-40s %-10s \n", 
                        ISBN, author, title, isAvailible);
            }
            System.out.println("---------------------------------------------------"
                    + "--------------------------------------------\n");
            
        } else {
            System.out.println("Library is currently empty. "
                    + "Add new books to view them here.\n");
        }
        
        System.out.println("--- End of List ---\n");
 
    }
    
    public void addMember(Member newMember){
        
        libraryMembers.add(newMember);

    }
    
    public void viewAllMembers(){
        
        System.out.println("\n--- Viewing All Library Members ---\n");
        
        if (!libraryMembers.isEmpty()) {
                        
            System.out.printf("%-16s %-25s %-5s \n",
                    "Name", "Email", "# of Books Checked Out" );
            System.out.println("-----------------------------------------------"
                    + "------------------------------------------------");
        
            for( int i = 0; i < libraryMembers.size(); i++){
                
                String name = libraryMembers.get(i).name;
                String email = libraryMembers.get(i).email;
                int numOfCheckedOut = libraryMembers.get(i).borrowedBooks.size();
                
                System.out.printf("%-16s %-25s %-5s \n", 
                        name, email,numOfCheckedOut);
            }
            System.out.println("-----------------------------------------------"
                    + "------------------------------------------------\n");
            
        } else {
            System.out.println("Library has no registered members. "
                    + "Register new members view them here.\n");
        }
        
        System.out.println("--- End of List ---\n");

    }
    
    
}
