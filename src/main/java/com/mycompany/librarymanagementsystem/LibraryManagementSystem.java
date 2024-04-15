package com.mycompany.librarymanagementsystem;

import java.util.List;
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
            
            while(true){
                
                System.out.print("Option: ");
                
                try {
                    choice = Integer.parseInt(scan.nextLine());
                    if(choice > 8 || choice < 0){
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(
                            "Invalid Choice. Please input a valid digit (0 to 8)"
                    );
                }
            }
            
            
            switch (choice) {
                case 1: // Add new Book
                    Book newBook = new Book();
                    library.addBook(newBook);
                    break;
                    
                case 2: // View All Books
                    library.viewBooks(library.libraryBooks);
                    break;
                    
                case 3: // Search for Book
                    List<Book> bookSearchResults = library.searchBooks();
                    library.viewBooks(bookSearchResults);
                    break;
                    
                case 4: // Add new Member
                    Member newMember = new Member();
                    library.addMember(newMember);
                    break;
                    
                case 5: // View All Members
                    library.viewMembers(library.libraryMembers);
                    break;
                    
                case 6: // Search for Members
                    List<Member> memberSearchResults = library.searchMembers();
                    library.viewMembers(memberSearchResults);
                    break;
                    
                case 7: // Checkout Book
                    library.checkOut();
                    break;
                    
                case 8: // Checkin Book
                    library.checkIn();
                    break;
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
                           Press 5 to View All Members 
                           Press 6 to Search for Member
                           Press 7 to Check Out Book 
                           Press 8 to Check In Book 
                           -----------------------------------------------""");
    }
}
