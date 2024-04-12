/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarymanagementsystem;

import java.util.Scanner;

/**
 *
 * @author brdde
 */
public class LibraryManagementSystem {

    public static void main(String[] args) {
        
        
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
        int choice;
        
        Library library = new Library();
        
        do {
            showMenu();
            
            // ! validate
            while(true){
                
                System.out.print("Option: ");
                
                try {
                    choice = Integer.parseInt(scan.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Choice. Please input a valid digit (0-8)");
                }
            }
            
            
            switch (choice) {
                case 1:
                    Book newBook = new Book();
                    library.addBook(newBook);
                    System.out.println("\n--- Book Successfully Added to Library ---\n");
                    break;
                    
                case 2:
                    library.viewAllBooks();
                    break;
                    
                case 3:
                    // Search For Book
                    break;
                    
                case 4:
                    Member newMember = new Member();
                    library.addMember(newMember);
                    System.out.println("\n--- Member Successfully Registered ---\n");
                    break;
                    
                case 5:
                    library.viewAllMembers();
                    break;
                    
                case 6:
                    // Check Out Book
                    break;
                    
                case 7:
                    // Check In Book
                    break;
                    
                default:
                    
                    
                    
            }
        } while (choice != 0);
    }
    
    public static void showMenu(){
        
        System.out.println("""
                           What Would You Like To Do?
                           -----------------------------------------------
                           Press 0 to Exit 
                           Press 1 to Add New Book 
                           Press 2 to View All Books 
                           Press 3 to Search For Book 
                           Press 4 to Register New Member 
                           Press 5 to View Members 
                           Press 6 to Check Out Book 
                           Press 7 to Check In Book 
                           -----------------------------------------------""");
    }
}
